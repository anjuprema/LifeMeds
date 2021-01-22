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
import com.lifemeds.admin.model.User;
import com.lifemeds.admin.repository.CategoryRepository;

@Controller
@RequestMapping(value="/admin")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	private User sessionUser;
	
	@RequestMapping(value="/createCategory")
	public ModelAndView createCategory(Category category, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Map <String, String> catData = new HashMap <String,String>();
		catData.put("idCategory", "0");
		catData.put("categoryName", null);
		/*form submitted; create category or edit new*/
		if(category.getCategoryName() != null) {
			if(category.getIdCategory() != 0) {
				/*edit*/
				List <Category> isExist = categoryRepo.findBycategoryName(category.getCategoryName());
				if(isExist.isEmpty()) {
					categoryRepo.save(category);	
				}else {
					mv.addObject("errMsg", "Category Name already exists.");					
				}				
			}else {
				/*create new*/
				List <Category> isExist = categoryRepo.findBycategoryName(category.getCategoryName());
				if(isExist.isEmpty()) {
					categoryRepo.save(category);	
				}else {
					mv.addObject("errMsg", "Category Name already exists.");					
				}
			}
			mv.addAllObjects(catData);
		}else {
			mv.addAllObjects(catData);
		}
		/*create new form*/		
		mv.addObject("title", "Create New Category");
		if(session.getAttribute("user") != null) {
			sessionUser = (User) session.getAttribute("user");
			mv.addObject("welcomeMsg", sessionUser.getUserName());
		}
		mv.setViewName("CreateCategory");
		return mv;		
	}
	
	@RequestMapping(value="/listCategory")
	public ModelAndView listCategory(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ListCategory");
		return mv;
//		List <Category> catList = new ArrayList <Category>();
//		catList = categoryRepo.findAll();
//		String gridData = "";
//		for(Category cat: catList) {
//			gridData = gridData.concat("<tr>"+
//					"<td width='60%'>"+ cat.getCategoryName() +"</td>"+
//					"<td><a href='/admin/editCategory?id="+ cat.getIdCategory() +"'><img src='../../icons/edit.png'></a>"+
//					"&nbsp;&nbsp;<a onClick='confirmDelete("+ cat.getIdCategory() +");'><img src='../../icons/delete.png'></a></td>"+
//					"</tr>");
//		}
//		//href='/admin/deleteCategory?id="+ cat.getIdCategory() +"'
//		mv.addObject("data",gridData);
//		if(session.getAttribute("user") != null) {
//			sessionUser = (User) session.getAttribute("user");
//			mv.addObject("welcomeMsg", sessionUser.getUserName());
//		}
		//mv.setViewName("ListCategory");
		//return mv;
	}
	
	@RequestMapping(value="/editCategory")
	public ModelAndView editCategory(@RequestParam(name="id") Integer id, HttpSession session) {
		//System.out.println(id);
		Category toEdit = categoryRepo.findById(id).orElse(new Category());
		Map <String, String> catData = new HashMap <String,String>();
		catData.put("idCategory", String.valueOf(toEdit.getIdCategory()));
		catData.put("categoryName", toEdit.getCategoryName());
		ModelAndView mv = new ModelAndView();
		if(toEdit.getIdCategory() != 0) {
			mv.addAllObjects(catData);
		}
		mv.addObject("title", "Edit Category");
		mv.setViewName("CreateCategory");
		if(session.getAttribute("user") != null) {
			sessionUser = (User) session.getAttribute("user");
			mv.addObject("welcomeMsg", sessionUser.getUserName());
		}
		return mv;
	}
	
	@RequestMapping(value="/deleteCategory")
	public String deleteCategory(@RequestParam(name="id") Integer id) {
		//System.out.println(id);
		ModelAndView mv = new ModelAndView();
		if(id != 0) {
			Category toDelete = categoryRepo.findById(id).orElse(new Category());
			categoryRepo.delete(toDelete);
		}
		return "redirect:/admin/listCategory";
	}
	
}
