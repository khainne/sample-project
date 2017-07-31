package com.vistana.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.vistana.domain.SecurityQuestionAnswer;
import com.vistana.domain.User;
import com.vistana.dto.SecurityQuestionsDTO;
import com.vistana.enumeration.SecurityQuestion;
import com.vistana.service.SecurityQuestionService;
import com.vistana.validator.SecurityQuestionValidator;

@Service
public class SecurityQuestionServiceImpl implements SecurityQuestionService {

	private static final int NUMBER_OF_QUESTIONS = 3;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SecurityQuestionValidator securityQuestionValidator;
	
	@Override
	public int getNumberOfQuestions() {
		return NUMBER_OF_QUESTIONS;
	}

	@Override
	public void validateSecurityQuestions(SecurityQuestionsDTO securityQuestionsDTO, BindingResult result) {
		securityQuestionValidator.validate(securityQuestionsDTO, result);
	}

	@Override
	public Boolean validateSecurityQuestion(SecurityQuestion question, String answer, User user) {
		
		for (SecurityQuestionAnswer sqa : user.getSecurityQuestionAnswers()) {
			if(sqa.getSecurityQuestion().equals(question)) {
				if(answer.trim().toLowerCase().equals(sqa.getAnswer().trim().toLowerCase())) {
					logger.info("{} has answered their security question correctly", user.getUsername());
					return true;
				} else {
					logger.info("{} has failed to answer their security question: {}... The correct answer is: {}... The answer provided was: {}", user.getUsername(), sqa.getSecurityQuestion().getQuestion(), sqa.getAnswer(), answer);
				}
			}
		}
		
		return false;
	}

}
