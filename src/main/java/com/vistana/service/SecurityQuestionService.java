package com.vistana.service;

import org.springframework.validation.BindingResult;

import com.vistana.domain.User;
import com.vistana.dto.SecurityQuestionsDTO;
import com.vistana.enumeration.SecurityQuestion;

public interface SecurityQuestionService {
	int getNumberOfQuestions();
	void validateSecurityQuestions(SecurityQuestionsDTO securityQuestionsDTO, BindingResult result);
	Boolean validateSecurityQuestion(SecurityQuestion question, String answer, User user);
}
