package com.vistana.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.vistana.domain.User;
import com.vistana.dto.ValidateUserDTO;
import com.vistana.service.ValidateUserService;

@Service
public class ValidateUserServiceImpl implements ValidateUserService {

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

}
