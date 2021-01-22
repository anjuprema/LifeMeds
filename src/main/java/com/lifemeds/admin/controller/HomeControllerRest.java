package com.lifemeds.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifemeds.admin.model.Category;
import com.lifemeds.admin.model.Medicine;
import com.lifemeds.admin.model.ProductPurchase;
import com.lifemeds.admin.model.Purchase;
import com.lifemeds.admin.model.Seller;
import com.lifemeds.admin.model.User;
import com.lifemeds.admin.repository.CategoryRepository;
import com.lifemeds.admin.repository.MedicineRepository;
import com.lifemeds.admin.repository.ProductPurchaseRepository;
import com.lifemeds.admin.repository.PurchaseRepository;
import com.lifemeds.admin.repository.SellerRepository;
import com.lifemeds.admin.repository.UserRepository;



@RestController
@CrossOrigin
@RequestMapping("/client")
public class HomeControllerRest {
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private SellerRepository sellerRepo;
	@Autowired
	private MedicineRepository medicineRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PurchaseRepository purchaseRepo;
	@Autowired
	private ProductPurchaseRepository productPurchaseRepo;
	@Value("${upload.folder}")
    private String uploadFolder;
	
	@GetMapping("/listCategory")
	public List<Category> listCategory(HttpServletResponse response) {
		//response.addHeader("Access-Control-Allow-Origin", "*");
		return categoryRepo.findAll();
	}
	
	@GetMapping("/listSeller")
	public List<Seller> listSeller(HttpServletResponse response){
		return sellerRepo.findAll();
	}
	
	@GetMapping("/listMedicine")
	public List<Medicine> listMedicine(HttpServletResponse response, @RequestParam(required=false) Integer categoryId,
			@RequestParam(required=false) Integer sellerId, @RequestParam(required=false) String title, @RequestParam(required=false) Boolean getAll){
		if(categoryId != null) {
			Category cat = new Category();
			cat.setIdCategory(categoryId);
			return medicineRepo.findByCategoryAndIsEnabled(cat, true);
		}else if(sellerId != null){
			Seller sell = new Seller();
			sell.setIdSeller(sellerId);
			return medicineRepo.findBySellerAndIsEnabled(sell, true);
		}else if(title != null){
			return medicineRepo.findByMedicineNameContainsAndIsEnabled(title, true);
		} else if(getAll!=null && getAll){
			return medicineRepo.findAll();
		}else {		
			return medicineRepo.findByisEnabled(true);
		}			
	}
	
	@GetMapping("/medicineDetail")
	public Medicine getMedicineDetail(HttpServletResponse response, @RequestParam Integer medicineId) {
		return medicineRepo.findById(medicineId).orElse(new Medicine());
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestBody String userdata, HttpServletResponse response) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectmapper = new ObjectMapper();
		objectmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		User newuser = objectmapper.readValue(userdata, User.class);  
		
		//check for duplicate user name
		User existing = userRepo.findByUserName(newuser.getUserName());
		if(existing != null) {
			return "500";
		}else {
			userRepo.save(newuser);			
			return "200";
		}		
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody String userdata, HttpServletResponse response) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectmapper = new ObjectMapper();
		objectmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		User user = objectmapper.readValue(userdata, User.class);  
		
		//check for duplicate user name
		User existing = userRepo.findByUserNameAndUserPasswordAndIsAdmin(user.getUserName(), user.getUserPassword(), false);
		if(existing != null) {
			return "200";
		}else {				
			return "500";
		}		
	}
	
	@PostMapping("/purchase")
	public String makePurchase(@RequestBody String data) throws ParseException, JsonMappingException, JsonProcessingException {
		JSONObject rootJSON = (JSONObject) new JSONParser().parse(data);
 				
		JSONArray medicineCount = (JSONArray) rootJSON.get("medicineCount");
				
		JSONObject userInfo = (JSONObject) rootJSON.get("userInfo");
		
		JSONObject paymentInfo = (JSONObject) rootJSON.get("paymentInfo");
		
		Purchase purchase = new Purchase();
		User loggedInUser = userRepo.findByUserName((String) userInfo.get("userName"));
		purchase.setUser(loggedInUser);
		purchase.setAmountPayed(Double.valueOf(String.valueOf(paymentInfo.get("amountPayed"))));
		purchase.setDeliveryAddress(String.valueOf(paymentInfo.get("deliveryAddress")));
		purchaseRepo.save(purchase);
		//System.out.println(purchase);
		
		JSONArray medicineList = (JSONArray) rootJSON.get("medicineList");
		for(Object medicine : medicineList.toArray()) {
			JSONObject medicineData = (JSONObject)medicine;
			
			Medicine eachMedicine = new Medicine();
			eachMedicine.setIdMedicine(Integer.valueOf(String.valueOf(medicineData.get("idMedicine"))));
			ProductPurchase pPurchase = new ProductPurchase();
			pPurchase.setPurchase(purchase);
			pPurchase.setMedicine(eachMedicine);
			pPurchase.setItemCount(Integer.valueOf(String.valueOf(medicineCount.toArray()[Integer.valueOf(String.valueOf(medicineData.get("idMedicine")))])));
			pPurchase.setProductPrice(Double.valueOf(String.valueOf(medicineData.get("medicinePrice"))));
			productPurchaseRepo.save(pPurchase);
			//System.out.println(pPurchase);
		}	
		
		return "200";
	}
	
	@GetMapping("/orderHistory")
	public List<Purchase> getOrderHistory(@RequestParam(required = false) String userName){
		User user = userRepo.findByUserName(userName);	
		return purchaseRepo.findByUserOrderByPurchaseDateDesc(user);
	}

}
