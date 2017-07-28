package com.vistana.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.vistana.dto.ValidateUserDTO;
import com.vistana.service.SecurityQuestionService;
import com.vistana.service.ValidateUserService;
import com.vistana.session.ApplicationSession;

@Controller
public class ValidationController {
	
	@Autowired
	private ValidateUserService validateUserService;
	
	@Autowired
	private SecurityQuestionService securityQuestionService;
	
	@Autowired
	private ApplicationSession session;
	
	
	@RequestMapping(method=RequestMethod.POST, value="/validate-username")
	@ResponseBody
	public Boolean processValidateUsername(@ModelAttribute("username") String username) {
		
		Boolean userExists = false;
		if(session.getUser() != null && username.equals(session.getUser().getUsername())) {
			userExists = true;
			session.setIsValidated(true);
		}
		
		return userExists;
		
	}
	
	@RequestMapping("/validate")
    public ModelAndView showValidateUser(Map<String, Object> map) {
		if(session.getUser() != null && session.getUser().isValid()) {
			map.put("validateUserForm", validateUserService.getValidationQuestionForUser(session.getUser()));
			return new ModelAndView("validate", map);
		} else {
			return new ModelAndView("redirect:/");
		}
		
		
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/validate")
	public ModelAndView processValidateUser(@ModelAttribute("validateUserForm") ValidateUserDTO validateUserForm, BindingResult result, Map<String, Object> map) {
		
		if (securityQuestionService.validateSecurityQuestion(validateUserForm.getQuestion(), validateUserForm.getAnswer(), session.getUser())) {
			session.setIsLoggedIn(true);
			return new ModelAndView("redirect:/user-dashboard");
		} else {
			result.rejectValue("answer", "error.validate.answer.invalid");
			return new ModelAndView("validate", map);
		}
		
		
	}
}
