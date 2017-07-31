package com.vistana.service.impl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vistana.domain.User;
import com.vistana.dto.ValidateUserDTO;
import com.vistana.service.ValidateUserService;
import com.vistana.session.ApplicationSession;

@Service
public class ValidateUserServiceImpl implements ValidateUserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApplicationSession session;
	
	@Override
	public ValidateUserDTO getValidationQuestionForUser(User user) {
		ValidateUserDTO question = new ValidateUserDTO();
		
		//select a random interger
		Random randomIndex = new Random(); 
		int value = randomIndex.nextInt(user.getSecurityQuestionAnswers().size()); 
		
		//pull that question corresponding to random index
		question.setQuestion(user.getSecurityQuestionAnswers().get(value).getSecurityQuestion());
		
		return question;
	}

	@Override
	public Boolean validateLogin(String username) {
		Boolean userExists = false;
		if(session.getUser() != null && session.getUser().isValid() && username.trim().toLowerCase().equals(session.getUser().getUsername())) {
			userExists = true;
			session.setIsValidated(true);
			logger.info("{} is a valid username!", username);
		} else {
			logger.error("{} is not valid username!", username);
		}
		return userExists;
	}

}
