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
import com.vistana.exception.UserNotFoundException;
import com.vistana.service.SecurityQuestionService;
import com.vistana.service.UserSessionService;
import com.vistana.service.ValidateUserService;

@Controller
public class ValidationController {
	
	@Autowired
	private ValidateUserService validateUserService;
	
	@Autowired
	private SecurityQuestionService securityQuestionService;
	
	@Autowired
	private UserSessionService userSessionService;
	
	@RequestMapping(method=RequestMethod.POST, value="/validate-username")
	@ResponseBody
	public Boolean processValidateUsername(@ModelAttribute("username") String username) {
		try {
			userSessionService.logoutUser();
			userSessionService.validateLogin(username);
			return true;
		} catch (UserNotFoundException e) {
			return false;
		}
	}
	
	@RequestMapping("/validate")
    public ModelAndView showValidateUser(Map<String, Object> map) {
		if(userSessionService.getValidatedUser() != null && userSessionService.getValidatedUser().isValid() && !userSessionService.getIsLoggedIn()) {
			map.put("validateUserForm", validateUserService.getValidationQuestionForUser(userSessionService.getValidatedUser()));
			return new ModelAndView("validate", map);
		} else {
			return new ModelAndView("redirect:/");
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/validate")
	public ModelAndView processValidateUser(@ModelAttribute("validateUserForm") ValidateUserDTO validateUserForm, BindingResult result, Map<String, Object> map) {
		if(userSessionService.getValidatedUser() != null && userSessionService.getValidatedUser().isValid() && !userSessionService.getIsLoggedIn()) {
			if (securityQuestionService.validateSecurityQuestion(validateUserForm.getQuestion(), validateUserForm.getAnswer(), userSessionService.getValidatedUser())) {
				userSessionService.loginUser(userSessionService.getValidatedUser());
				return new ModelAndView("redirect:/user-dashboard");
			} else {
				result.rejectValue("answer", "error.validate.answer.invalid");
				return new ModelAndView("validate", map);
			}
		} else {
			return new ModelAndView("redirect:/");
		}
	}
}
