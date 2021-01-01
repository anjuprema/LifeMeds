package com.lifemeds.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.lifemeds.admin.controller.SellerController;
import com.lifemeds.admin.model.Seller;

@DisplayName("Category Functionality Tests")
@SpringBootTest
public class SellerFunctionalityTest {
	@Autowired
	SellerController sellerCtrl;
	@Autowired
	MockHttpSession session;
	
	@Test
	@DisplayName("Seller Listing Test")
	void testListSeller() {
		ModelAndView mv = sellerCtrl.listSeller(session);
		String model = mv.getViewName();
		assertEquals("ListSeller", model);		
	}
	
	@Test
	@DisplayName("Create New Seller Test")
	void testCreateSeller() {
		Seller newSeller = new Seller();
		newSeller.setSellerName("Sun Pharma");
		ModelAndView mv = sellerCtrl.createSeller(newSeller, session);
		String model = mv.getViewName();
		assertEquals("CreateSeller", model);
	}
	
	@Test
	@DisplayName("Delete an Existing Seller Test")
	void testDeleteSeller() {
		String redirect = sellerCtrl.deleteSeller(12);
		assertEquals("redirect:/admin/listSeller", redirect);
	}
	
	@Test
	@DisplayName("Edit Seller Test")
	void testEditSeller() {
		ModelAndView mv = sellerCtrl.editSeller(13, session);
		assertEquals("CreateSeller", mv.getViewName());		
	}	
	
}
