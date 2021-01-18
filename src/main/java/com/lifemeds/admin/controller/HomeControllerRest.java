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
		//response.addHeader("Access-Control-Allow-Origin", "*");
		return sellerRepo.findAll();
	}
	
	@GetMapping("/listMedicine")
	public List<Medicine> listMedicine(HttpServletResponse response, @RequestParam(required=false) Integer categoryId,
			@RequestParam(required=false) Integer sellerId, @RequestParam(required=false) String title){
		//response.addHeader("Access-Control-Allow-Origin", "*");
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
		} else {		
			return medicineRepo.findByisEnabled(true);
		}			
	}
	
	@GetMapping("/medicineDetail")
	public Medicine getMedicineDetail(HttpServletResponse response, @RequestParam Integer medicineId) {
		//response.addHeader("Access-Control-Allow-Origin", "*");
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
		System.out.println(data);
		JSONObject userInfo = new JSONObject();
		JSONObject medicineList = new JSONObject();
		JSONObject medicineCount = new JSONObject();
		JSONObject paymentInfo = new JSONObject();
		
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(data);

        JSONArray userList = (JSONArray) obj;
        userInfo = (JSONObject) ((JSONObject) userList.get(0)).get("userInfo");
		System.out.println(userInfo);
		
//		JSONArray medicineArray = (JSONArray) obj;
//		medicineList = (JSONObject) ((JSONObject) medicineArray.get(0)).get("medicineList");
//		System.out.println(medicineList);
//		
//		JSONArray countArray = (JSONArray) obj;
//		medicineCount = (JSONObject) ((JSONObject) countArray.get(0)).get("medicineCount");
//		System.out.println(medicineCount);
		
		JSONArray paymentDetail = (JSONArray) obj;
		paymentInfo = (JSONObject) ((JSONObject) paymentDetail.get(0)).get("paymentInfo");
		System.out.println(paymentInfo);
		
//		ObjectMapper objectmapper = new ObjectMapper();
//		objectmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		//User user = objectmapper.readValue(data, User.class);
//		Medicine medlist = objectmapper.readValue((String) userList.get(0), Medicine.class);
//		System.out.println(medlist);
		
//		System.out.println(data);
//		ObjectMapper objectmapper = new ObjectMapper();
//		objectmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		User user = objectmapper.readValue(data, User.class);
//		System.out.println("user");
//		System.out.println(user);
//		objectmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		List <Medicine> med = new ArrayList<Medicine>();
//		med = (List<Medicine>) objectmapper.readValue(data, Medicine.class);
//		System.out.println("medicine");
//		System.out.println(med);
		
//		Purchase test = new Purchase();
//		User user = new User();
//		user.setUserId(2);
//		test.setUser(user);
//		test.setAmountPayed(44.00);
//		test.setDeliveryAddress("Sreevilas, Udayagiri");
//		purchaseRepo.save(test);
//		
//		Medicine med = new Medicine();
//		med.setIdMedicine(26);
//		ProductPurchase pp = new ProductPurchase();
//		pp.setPurchase(test);
//		pp.setMedicine(med);
//		pp.setItemCount(2);
//		pp.setProductPrice(44.00);
//		productPurchaseRepo.save(pp);
		return "200";
	}
	
	@GetMapping("/getSettings")
	public String getSettings(HttpServletResponse response) {	
		//response.addHeader("Access-Control-Allow-Origin", "*");
		return "";
//		String str = "{\"fileDestination\":\""+ 
//							System.getProperty("user.home") + File.separator + uploadFolder + File.separator +"\",\"fileSeperator\":\""+ File.separator +"\"}";
//		str = str.replace("\\", "\\\\");
//		return str;
	}

}
