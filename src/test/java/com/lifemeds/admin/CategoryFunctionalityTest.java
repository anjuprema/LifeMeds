package com.lifemeds.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.lifemeds.admin.controller.CategoryController;
import com.lifemeds.admin.model.Category;

@DisplayName("Category Functionality Tests")
@SpringBootTest
public class CategoryFunctionalityTest {
	@Autowired
	CategoryController catCtrl;
	@Autowired
	MockHttpSession session;
	
	
	@Test
	@DisplayName("Category Listing Test")
	void testListProduct() {
		ModelAndView mv = catCtrl.listCategory(session);
		String model = mv.getViewName();
		assertEquals("ListCategory", model);		
	}
	
	@Test
	@DisplayName("Create New Category Test")
	void testCreateCategory() {
		Category newCat = new Category();
		newCat.setCategoryName("FirstAid Kit");
		ModelAndView mv = catCtrl.createCategory(newCat, session);
		String model = mv.getViewName();
		assertEquals("CreateCategory", model);
	}
	
	@Test
	@DisplayName("Delete an Existing Category Test")
	void testDeleteCategory() {		
		String redirect = catCtrl.deleteCategory(12);
		assertEquals("redirect:/admin/listCategory", redirect);
	}
	
	@Test
	@DisplayName("Edit Category Test")
	void testEditCategory() {
		ModelAndView mv = catCtrl.editCategory(13, session);
		assertEquals("CreateCategory", mv.getViewName());		
	}
}
