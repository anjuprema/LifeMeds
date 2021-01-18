package com.lifemeds.admin.controller;

import javax.servlet.http.HttpServletRequest;
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
public class HomeController {
	
	@Autowired
	private UserRepository userRepo;

	
	@RequestMapping(value={"/",""})
	public ModelAndView showLoginForm(User user, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(user.getUserName() != null && user.getUserPassword() != null && session.getAttribute("user") == null) {
			User doesExist = this.userRepo.findByUserNameAndIsAdmin(user.getUserName(), true);
			if(doesExist != null) {
				/*if login uname & password matches, allow login and keep session*/
				if(doesExist.getUserPassword().equals(user.getUserPassword())) {
					session.setAttribute("user", doesExist);
					mv.addObject("welcomeMsg",doesExist.getUserName());
					mv.setViewName("Dashboard");
				}else {
					System.out.println("invalid login credential");
					mv.addObject("errorMsg","UserName & Password doesnot match..");
					mv.setViewName("AdminLogin");
					//return mv;
				}
			} else {
				mv.addObject("errorMsg","Invalid Login Credentials..");
				mv.setViewName("AdminLogin");
				//return mv;
			}
		}else {
			if(session.getAttribute("user") == null) {
				mv.setViewName("AdminLogin");
			}else {
				
				User sessionInfo = (User) session.getAttribute("user");
				//System.out.println(sessionInfo.getUserName());
				mv.addObject("welcomeMsg",sessionInfo.getUserName());
				mv.setViewName("Dashboard");
			}
			//return mv;		
		}
		return mv;
	}
	
		
}
