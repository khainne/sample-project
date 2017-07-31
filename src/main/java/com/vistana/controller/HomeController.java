package com.vistana.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vistana.session.ApplicationSession;

@Controller
public class HomeController {
	
	@Autowired
	private ApplicationSession session;
	
	@RequestMapping("/")
    public ModelAndView greeting(Map<String, Object> map) {
		map.put("session", session);
		return new ModelAndView("home", map);
    }
}