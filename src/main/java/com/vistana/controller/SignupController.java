package com.vistana.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vistana.domain.User;
import com.vistana.dto.SecurityQuestionsDTO;
import com.vistana.dto.UserInfoDTO;
import com.vistana.enumeration.SecurityQuestion;
import com.vistana.service.NewUserService;
import com.vistana.service.SecurityQuestionService;
import com.vistana.service.UserSessionService;

@Controller
public class SignupController {

	@Autowired
	private NewUserService newUserService;
	
	@Autowired
	private SecurityQuestionService securityQuestionService;
	
	@Autowired
	private UserSessionService userSessionService;
	
	@ModelAttribute("numberOfQuestions")
	public int getOffering() {
	   return securityQuestionService.getNumberOfQuestions();
	}
	
	
	@RequestMapping("/sign-up")
    public ModelAndView showOwnerInfo(Map<String, Object> map) {
		map.put("userForm", new UserInfoDTO());
		return new ModelAndView("signup/personalInformation", map);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/sign-up")
	public ModelAndView processOwnerInfo(@ModelAttribute("userForm") UserInfoDTO userForm, BindingResult result, Map<String, Object> map) {
		
		newUserService.validateNewUser(userForm, result);
		
		if(result.hasErrors()) {
			return new ModelAndView("signup/personalInformation", map);
		} else {
			User newUser = newUserService.createNewUser(userForm);
			userSessionService.createNewUser(newUser);
			
			return new ModelAndView("redirect:/sign-up/security-questions");
		}
		
		
	}
	
	@RequestMapping("/sign-up/security-questions")
    public ModelAndView showSecurityQuestions(Map<String, Object> map) {
		//quick check to see if they completed the first step
		if(userSessionService.getNewUser() == null) {
			return new ModelAndView("redirect:/sign-up/");
		}
		
		map.put("securityQuestions", SecurityQuestion.values());
		map.put("securityQuestionsForm", new SecurityQuestionsDTO());
		return new ModelAndView("signup/securityQuestions", map);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/sign-up/security-questions")
	public ModelAndView processSecurityQuestions(@ModelAttribute("securityQuestionsForm") SecurityQuestionsDTO securityQuestionsForm, BindingResult result, Map<String, Object> map) {
		
		if(userSessionService.getNewUser() == null) {
			return new ModelAndView("redirect:/sign-up/");
		}
		
		securityQuestionService.validateSecurityQuestions(securityQuestionsForm, result);
		
		if(result.hasErrors()) {
			map.put("securityQuestions", SecurityQuestion.values());
			return new ModelAndView("signup/securityQuestions", map);
		} else {
			
			User newUser = userSessionService.getNewUser();
			newUserService.addSecurityQuestions(newUser, securityQuestionsForm);
			userSessionService.addUserToList(newUser);
			
			return new ModelAndView("redirect:/");
		}
	}

	@InitBinder
    public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
	
}
