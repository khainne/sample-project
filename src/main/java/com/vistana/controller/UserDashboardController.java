package com.vistana.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vistana.service.UserSessionService;

@Controller
public class UserDashboardController {
	
	@Autowired
	private UserSessionService userSessionService;
	
	@RequestMapping("/user-dashboard")
    public ModelAndView showUserDashboard(Map<String, Object> map) {
		if(!userSessionService.getIsLoggedIn()) {
			return new ModelAndView("redirect:/");
		} 
		return new ModelAndView("dashboard", map);
    }
	
	@RequestMapping("/logout")
    public ModelAndView processLogout(Map<String, Object> map) {
		userSessionService.logoutUser();
		return new ModelAndView("redirect:/?logged-out=true");
    }
}
