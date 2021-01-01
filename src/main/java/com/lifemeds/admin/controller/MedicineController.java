package com.lifemeds.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lifemeds.admin.model.Medicine;
import com.lifemeds.admin.model.Seller;
import com.lifemeds.admin.model.User;
import com.lifemeds.admin.model.Category;
import com.lifemeds.admin.repository.CategoryRepository;
import com.lifemeds.admin.repository.MedicineRepository;
import com.lifemeds.admin.repository.SellerRepository;
import com.lifemeds.admin.service.FileUploadService;

@Controller
@RequestMapping(value="/admin")
public class MedicineController {
	@Autowired
	private MedicineRepository medicineRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private SellerRepository sellerRepo;
	@Autowired 
	private FileUploadService fileuploadservice;
	@Value("${upload.folder}")
    private String uploadFolder;
	
	private User sessionUser;
	
	
	@RequestMapping(value="/createMedicine")
	public ModelAndView createMedicine(Medicine medicine, @RequestParam(required=false) Integer idCategory,
			@RequestParam(required=false) Integer idSeller, @RequestParam(required=false) Integer idMedicine,
			@RequestParam(required=false) MultipartFile newpicturePath, @RequestParam(required=false) String oldFileName,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		/*if posting form*/
		if(medicine.getMedicineName() != null) {
			System.out.println("imagefile"+newpicturePath);
			
			Category tmpCat = new Category();
			tmpCat.setIdCategory(idCategory);
			Seller tmpSeller = new Seller();
			tmpSeller.setIdSeller(idSeller);
			
			medicine.setCategory(tmpCat);
			medicine.setSeller(tmpSeller);
			medicine.setEnabled(true);	
			/*if a file is uploaded get its name*/
			if(newpicturePath.getOriginalFilename() != null) {
				medicine.setPicturePath(newpicturePath.getOriginalFilename());
			}
			/*if no file is uploaded (during edit), use the already uploaded file name only*/
			if(newpicturePath.isEmpty()) {
				medicine.setPicturePath(oldFileName);
			}
			Medicine savedMed = medicineRepo.save(medicine);
			/*if new file is uploaded*/
			if(!newpicturePath.isEmpty()) {
				fileuploadservice.uploadFile(newpicturePath, savedMed.getIdMedicine());
			}
			
		}
		List <Category> catList = categoryRepo.findAll();
		String categoryOptions  = "";
		for(Category cat:catList) {
			categoryOptions = categoryOptions.concat("<option value='"+cat.getIdCategory()+"'>"+cat.getCategoryName()+"</option>");
		}
		mv.addObject("categoryOptions", categoryOptions);
		
		List <Seller> sellerList = sellerRepo.findAll();
		String sellerOptions  = "";
		for(Seller seller:sellerList) {
			sellerOptions = sellerOptions.concat("<option value='"+seller.getIdSeller()+"'>"+seller.getSellerName()+"</option>");
		}
		mv.addObject("sellerOptions", sellerOptions);
		mv.addObject("title","Create New Medicine");
		if(session.getAttribute("user") != null) {
			sessionUser = (User) session.getAttribute("user");
			mv.addObject("welcomeMsg", sessionUser.getUserName());
		}
		mv.addObject("idMedicine",0);
		mv.setViewName("CreateMedicine");
		return mv;
	}
	
	@RequestMapping(value="/listMedicine")
	public ModelAndView listMedicine(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List <Medicine> medicineList = new ArrayList <Medicine>();
		medicineList = medicineRepo.findAll();
		String gridData = "";
		for(Medicine medicine: medicineList) {
			gridData = gridData.concat("<tr>"+
					"<td width='20%'>"+ medicine.getMedicineName() +"</td>"+
					"<td width='10%'>"+ medicine.getMedicinePrice() +"</td>"+
					"<td width='15%'>"+ medicine.getCategory().getCategoryName() +"</td>"+
					"<td width='15%'>"+ medicine.getSeller().getSellerName() +"</td>"+
					"<td width='25%'>"+ medicine.getMedicineDescription() +"</td>"+
					"<td> <a href='/admin/updateStatus?id="+ medicine.getIdMedicine() +"'>"+ 
						(medicine.isEnabled() ? "<img src='../../icons/active.png'>" : "<img src='../../icons/inactive.png'>")+"</a>"+
					"&nbsp;&nbsp;<a href='/admin/editMedicine?id="+ medicine.getIdMedicine() +"'><img src='../../icons/edit.png'></a>"+
					"&nbsp;&nbsp;<a onClick='confirmDelete("+ medicine.getIdMedicine() +");'><img src='../../icons/delete.png'></a></td>"+
					"</tr>");
		}		
		mv.addObject("data",gridData);
		if(session.getAttribute("user") != null) {
			sessionUser = (User) session.getAttribute("user");
			mv.addObject("welcomeMsg", sessionUser.getUserName());
		}
		mv.setViewName("ListMedicine");
		return mv;
	}
	
	@RequestMapping(value="/editMedicine")
	public ModelAndView editMedicine(@RequestParam(name="id") Integer id, HttpSession session) {
		Medicine toEdit = medicineRepo.findById(id).orElse(new Medicine());
		ModelAndView mv = new ModelAndView();
		List <Category> catList = categoryRepo.findAll();
		String categoryOptions  = "";
		for(Category cat:catList) {
			categoryOptions = categoryOptions.concat("<option value='"+cat.getIdCategory()+"'");
			if(cat.getIdCategory() == toEdit.getCategory().getIdCategory()) {
				categoryOptions = categoryOptions.concat(" selected");
			}
			categoryOptions = categoryOptions.concat(">"+cat.getCategoryName()+"</option>");			
		}
		mv.addObject("categoryOptions", categoryOptions);
		
		List <Seller> sellerList = sellerRepo.findAll();
		String sellerOptions  = "";
		for(Seller seller:sellerList) {
			sellerOptions = sellerOptions.concat("<option value='"+seller.getIdSeller()+"'");
			if(seller.getIdSeller() == toEdit.getSeller().getIdSeller()) {
				sellerOptions = sellerOptions.concat(" selected");
			}
			sellerOptions = sellerOptions.concat(">"+seller.getSellerName()+"</option>");
		}
		mv.addObject("sellerOptions", sellerOptions);
		
		mv.addObject("idMedicine",toEdit.getIdMedicine());
		mv.addObject("medicineName",toEdit.getMedicineName());
		mv.addObject("medicinePrice",toEdit.getMedicinePrice());
		mv.addObject("medicineDescription",toEdit.getMedicineDescription());
		mv.addObject("newpicturePath", toEdit.getPicturePath());
		mv.addObject("title","Edit Medicine");
		if(session.getAttribute("user") != null) {
			sessionUser = (User) session.getAttribute("user");
			mv.addObject("welcomeMsg", sessionUser.getUserName());
		}
		mv.setViewName("CreateMedicine");
		return mv;
	}
	
	@RequestMapping(value="/deleteMedicine")
	public String deleteMedicine(@RequestParam(name="id") Integer id) throws IOException {
		if(id != 0) {
			Medicine toDelete = medicineRepo.findById(id).orElse(new Medicine());
			medicineRepo.delete(toDelete);
			
			/*free the file space*/			
			String filedesination = System.getProperty("user.home") + File.separator + uploadFolder + File.separator + id;
			FileUtils.deleteDirectory(new File(filedesination));
		}
		return "redirect:/admin/listMedicine";
	}
	
	@RequestMapping(value="updateStatus")
	public String updateStatus(@RequestParam(name="id") Integer id) {
		if(id != 0) {
			Medicine toUpdate = medicineRepo.findById(id).orElse(new Medicine());
			toUpdate.setEnabled((toUpdate.isEnabled()) ? false : true);
			medicineRepo.save(toUpdate);
		}		
		return "redirect:/admin/listMedicine";		
	}
}
