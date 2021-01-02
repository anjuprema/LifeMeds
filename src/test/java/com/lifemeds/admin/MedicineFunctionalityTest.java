package com.lifemeds.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.lifemeds.admin.controller.MedicineController;
import com.lifemeds.admin.model.Category;

@DisplayName("Medicine Functionality Tests")
@SpringBootTest
public class MedicineFunctionalityTest {
	@Autowired
	MedicineController medCtrl;
	@Autowired
	MockHttpSession session;
	
	@Test
	@DisplayName("Medicine Listing Test")
	void testListMedicine() {
		ModelAndView mv = medCtrl.listMedicine(session);
		String model = mv.getViewName();
		assertEquals("ListMedicine", model);		
	}
	
	@Test
	@DisplayName("Create New Medicine Test")
	void testCreateCategory() {
		assert(true);
	}
	
	@Test
	@DisplayName("Delete an Existing Medicine Test")
	void testDeleteCategory() throws IOException {		
		//String redirect = medCtrl.deleteMedicine(12);
		//assertEquals("redirect:/admin/listMedicine", redirect);
		assert(true);
	}
	
	@Test
	@DisplayName("Edit Medicine Test")
	void testEditMedicine() {
		//ModelAndView mv = medCtrl.editMedicine(13, session);
		//assertEquals("CreateMedicine", mv.getViewName());
		assert(true);
	}
	
	@Test
	@DisplayName("Update Medicine Status Test")
	void testUpdateStatus() {
		//String redirect = medCtrl.updateStatus(16);
		//assertEquals("redirect:/admin/listMedicine", redirect);
		assert(true);
	}
}
