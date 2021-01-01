package com.lifemeds.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lifemeds.admin.model.User;
import com.lifemeds.admin.repository.UserRepository;

@Controller
@RequestMapping(value="/admin")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	private User sessionUser;
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("user") != null) {
			session.invalidate();			
		}
		return "redirect:/admin/";
	}
	
	@RequestMapping(value="/changePassword")
	public ModelAndView changePassword(HttpSession session) {
		ModelAndView mv = new ModelAndView();		
		if(session.getAttribute("user") != null) {
			User loggedInUser = (User) session.getAttribute("user");			
			mv.addObject("userId", loggedInUser.getUserId());
			mv.addObject("welcomeMsg", loggedInUser.getUserName());
			mv.setViewName("ChangePassword");			
		}		
		return mv;
	} 
	
	@RequestMapping(value="/updatePassword")
	public ModelAndView updatePassword(HttpSession session, 
			@RequestParam(required=false) String oldPassword, @RequestParam(required=false) String newPassword, 
			@RequestParam(required=false) String confirmPassword, @RequestParam(required=false) Integer userId) {
		ModelAndView mv = new ModelAndView();		
		if(session.getAttribute("user") != null) {
			User loggedInUser = (User) session.getAttribute("user");
			/*if post, update password*/
			if(userId != null) {
				/*check oldPassword & alreadyExisting matches*/
				if(!oldPassword.equals(loggedInUser.getUserPassword())) {
					mv.addObject("errMsg","Enter correct password");
					mv.addObject("userId",loggedInUser.getUserId());
					mv.setViewName("ChangePassword");
				}else if(!newPassword.equals(confirmPassword)) {
					/*new password & confirm password does not match*/
					mv.addObject("errMsg","New & Confirm password should be the same");
					mv.addObject("userId",loggedInUser.getUserId());
					mv.setViewName("ChangePassword");
				}else {
					/*if all good update password*/
					loggedInUser.setUserPassword(newPassword);
					//System.out.println(loggedInUser);
					userRepo.save(loggedInUser);
					session.invalidate();
					mv.addObject("errMsg","Password has been updated. Please logout and log-in again");
					mv.setViewName("ChangePassword");
				}				
			}
		}
		if(session.getAttribute("user") != null) {
			sessionUser = (User) session.getAttribute("user");
			mv.addObject("welcomeMsg", sessionUser.getUserName());
		} 
		return mv; 
	}

}
