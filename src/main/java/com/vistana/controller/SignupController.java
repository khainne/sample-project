package com.vistana.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.vistana.dto.SecurityQuestionsDTO;
import com.vistana.dto.UserInfoDTO;
import com.vistana.enumeration.SecurityQuestion;

@Controller
public class SignupController {

	@RequestMapping("/sign-up")
    public ModelAndView signup(Map<String, Object> map) {
		map.put("userForm", new UserInfoDTO());
		return new ModelAndView("signup/personalInformation", map);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/sign-up")
	public ModelAndView processOwnerInfo(@ModelAttribute("userForm") UserInfoDTO userForm, BindingResult result, Map<String, Object> map) {
		return new ModelAndView("redirect:/sign-up/security-questions");
	}
	
	@RequestMapping("/sign-up/security-questions")
    public ModelAndView securityQuestions(Map<String, Object> map) {
		map.put("securityQuestions", SecurityQuestion.values());
		map.put("securityQuestionsForm", new SecurityQuestionsDTO());
		return new ModelAndView("signup/securityQuestions", map);
    }
	
}
