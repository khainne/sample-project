package com.vistana.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vistana.session.ApplicationSession;

@Controller
public class UserDashboardController {

	@Autowired
	private ApplicationSession session;
	
	@RequestMapping("/user-dashboard")
    public ModelAndView showUserDashboard(Map<String, Object> map) {
		if(!session.getIsLoggedIn()) {
			return new ModelAndView("redirect:/");
		} 
		map.put("session", session);
		return new ModelAndView("dashboard", map);
    }
	
	@RequestMapping("/logout")
    public ModelAndView processLogout(Map<String, Object> map) {
		session.setIsLoggedIn(false);
		session.setUser(null);
		session.setIsValidated(false);
		return new ModelAndView("redirect:/?logged-out=true");
    }
}
