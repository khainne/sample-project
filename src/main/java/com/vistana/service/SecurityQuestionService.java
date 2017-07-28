package com.vistana.service;

import org.springframework.validation.BindingResult;

import com.vistana.dto.SecurityQuestionsDTO;

public interface SecurityQuestionService {
	int getNumberOfQuestions();
	void validateSecurityQuestions(SecurityQuestionsDTO securityQuestionsDTO, BindingResult result);
}
