package com.vistana.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.vistana.dto.SecurityQuestionsDTO;
import com.vistana.service.SecurityQuestionService;
import com.vistana.validator.SecurityQuestionValidator;

@Service
public class SecurityQuestionServiceImpl implements SecurityQuestionService {

	private static final int NUMBER_OF_QUESTIONS = 3;
	
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

}
