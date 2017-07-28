package com.vistana.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
import com.vistana.session.ApplicationSession;

@Controller
public class SignupController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApplicationSession session;
	
	@Autowired
	private NewUserService newUserService;
	
	@Autowired
	private SecurityQuestionService securityQuestionService;
	
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
		User user = newUserService.createNewUser(userForm);
		logger.info(user.getUsername());
		return new ModelAndView("redirect:/sign-up/security-questions");
	}
	
	@RequestMapping("/sign-up/security-questions")
    public ModelAndView showSecurityQuestions(Map<String, Object> map) {
		map.put("securityQuestions", SecurityQuestion.values());
		map.put("securityQuestionsForm", new SecurityQuestionsDTO());
		return new ModelAndView("signup/securityQuestions", map);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/sign-up/security-questions")
	public ModelAndView processSecurityQuestions(@ModelAttribute("securityQuestionsForm") SecurityQuestionsDTO securityQuestionsForm, BindingResult result, Map<String, Object> map) {
		map.put("securityQuestions", SecurityQuestion.values());
		newUserService.addSecurityQuestions(securityQuestionsForm);
		return new ModelAndView("signup/securityQuestions", map);
	}
	
}
