package com.vistana.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.vistana.domain.SecurityQuestionAnswer;
import com.vistana.domain.User;
import com.vistana.dto.SecurityQuestionsDTO;
import com.vistana.dto.UserInfoDTO;
import com.vistana.validator.UserInfoValidator;

@Service
public class NewUserServiceImpl implements com.vistana.service.NewUserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserInfoValidator userInfoValidator;
	
	@Override
	public User createNewUser(String username, Date dob) {
		User user = new User();
		user.setUsername(username);
		user.setDob(dob);
		logger.info("A new user ({}) has been created with the birth date of {}", user.getUsername(), new SimpleDateFormat("yyyy-MM-dd").format(dob));
		return user;
	}

	@Override
	public User createNewUser(UserInfoDTO userInfoDTO) {
		return createNewUser(userInfoDTO.getUsername(), userInfoDTO.getDob());
	}

	
	@Override
	public User addSecurityQuestions(User user, List<SecurityQuestionAnswer> securityQuestionAnswers) {
		
		user.setSecurityQuestionAnswers(securityQuestionAnswers);
		
		for (SecurityQuestionAnswer sqa : securityQuestionAnswers) {
			logger.info("New security question answered for user {}: {} --- {}", user.getUsername(), sqa.getSecurityQuestion().getQuestion(), sqa.getAnswer());
		}
		return user;
	}

	@Override
	public User addSecurityQuestions(User user, SecurityQuestionsDTO securityQuestionsDTO) {
		return addSecurityQuestions(user, securityQuestionsDTO.getSecurityQuestionAnswers());
	}

	@Override
	public void validateNewUser(UserInfoDTO userInfoDTO, BindingResult result) {
		userInfoValidator.validate(userInfoDTO, result);
	}


}
