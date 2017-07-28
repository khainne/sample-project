package com.vistana.service.impl;

import org.springframework.stereotype.Service;

import com.vistana.service.SecurityQuestionService;

@Service
public class SecurityQuestionServiceImpl implements SecurityQuestionService {

	private static final int NUMBER_OF_QUESTIONS = 3;
	
	@Override
	public int getNumberOfQuestions() {
		return NUMBER_OF_QUESTIONS;
	}

}
