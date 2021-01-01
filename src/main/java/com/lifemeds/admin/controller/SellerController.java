package com.lifemeds.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lifemeds.admin.model.Category;
import com.lifemeds.admin.model.Seller;
import com.lifemeds.admin.model.User;
import com.lifemeds.admin.repository.SellerRepository;

@Controller
@RequestMapping(value="/admin")
public class SellerController {
	
	@Autowired
	private SellerRepository sellerRepo;
	
	private User sessionUser;
	
	@RequestMapping(value="/createSeller")
	public ModelAndView createSeller(Seller seller, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Map <String, String> sellerData = new HashMap <String,String>();
		sellerData.put("idSeller", "0");
		sellerData.put("sellerName", null);
		/*form submitted; create category or edit new*/
		if(seller.getSellerName() != null) {
			if(seller.getIdSeller() != 0) {
				/*edit*/
				List <Seller> isExist = sellerRepo.findBysellerName(seller.getSellerName());
				if(isExist.isEmpty()) {
					sellerRepo.save(seller);	
				}else {
					mv.addObject("errMsg", "Seller Name already exists.");					
				}				
			}else {
				/*create new*/
				List <Seller> isExist = sellerRepo.findBysellerName(seller.getSellerName());
				if(isExist.isEmpty()) {
					sellerRepo.save(seller);	
				}else {
					mv.addObject("errMsg", "Seller Name already exists.");					
				}
			}
			mv.addAllObjects(sellerData);
		}else {
			mv.addAllObjects(sellerData);
		}
		/*create new form*/	
		mv.addObject("title", "Create Seller");
		if(session.getAttribute("user") != null) {
			sessionUser = (User) session.getAttribute("user");
			mv.addObject("welcomeMsg", sessionUser.getUserName());
		}
		mv.setViewName("CreateSeller");
		return mv;		
	}
	
	@RequestMapping(value="/listSeller")
	public ModelAndView listSeller(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List <Seller> sellerList = new ArrayList <Seller>();
		sellerList = sellerRepo.findAll();
		String gridData = "";
		for(Seller seller: sellerList) {
			gridData = gridData.concat("<tr>"+
					"<td width='60%'>"+ seller.getSellerName() +"</td>"+
					"<td><a href='/admin/editSeller?id="+ seller.getIdSeller() +"'><img src='../../icons/edit.png'></a>"+
					"&nbsp;&nbsp;<a onClick='confirmDelete("+ seller.getIdSeller() +");'><img src='../../icons/delete.png'></a></td>"+
					"</tr>");
		}		
		mv.addObject("data",gridData);
		if(session.getAttribute("user") != null) {
			sessionUser = (User) session.getAttribute("user");
			mv.addObject("welcomeMsg", sessionUser.getUserName());
		}
		mv.setViewName("ListSeller");
		return mv;
	}
	
	@RequestMapping(value="/editSeller")
	public ModelAndView editSeller(@RequestParam(name="id") Integer id, HttpSession session) {
		Seller toEdit = sellerRepo.findById(id).orElse(new Seller());
		Map <String, String> sellerData = new HashMap <String,String>();
		sellerData.put("idSeller", String.valueOf(toEdit.getIdSeller()));
		sellerData.put("sellerName", toEdit.getSellerName());
		ModelAndView mv = new ModelAndView();
		if(toEdit.getIdSeller() != 0) {
			mv.addAllObjects(sellerData);
		}
		mv.addObject("title", "Edit Seller");
		if(session.getAttribute("user") != null) {
			sessionUser = (User) session.getAttribute("user");
			mv.addObject("welcomeMsg", sessionUser.getUserName());
		}
		mv.setViewName("CreateSeller");
		return mv;
	} 
	
	@RequestMapping(value="/deleteSeller")
	public String deleteSeller(@RequestParam(name="id") Integer id) {
		ModelAndView mv = new ModelAndView();
		if(id != 0) {
			Seller toDelete = sellerRepo.findById(id).orElse(new Seller());
			sellerRepo.delete(toDelete);
		}
		return "redirect:/admin/listSeller"; 
	}
}
