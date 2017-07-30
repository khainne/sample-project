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
import com.vistana.session.ApplicationSession;
import com.vistana.validator.UserInfoValidator;

@Service
public class NewUserServiceImpl implements com.vistana.service.NewUserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApplicationSession session;
	
	@Autowired
	private UserInfoValidator userInfoValidator;
	
	@Override
	public User createNewUser(String username, Date dob) {
		User user = new User();
		user.setUsername(username);
		user.setDob(dob);
		session.setUser(user);
		logger.info("A new user ({}) has been created with the birth date of {}", user.getUsername(), new SimpleDateFormat("yyyy-MM-dd").format(dob));
		return user;
	}

	@Override
	public User createNewUser(UserInfoDTO userInfoDTO) {
		return createNewUser(userInfoDTO.getUsername(), userInfoDTO.getDob());
	}

	
	@Override
	public User addSecurityQuestions(List<SecurityQuestionAnswer> securityQuestionAnswers) {
		session.getUser().setSecurityQuestionAnswers(securityQuestionAnswers);
		for (SecurityQuestionAnswer sqa : securityQuestionAnswers) {
			logger.info("New security question answered for user {}: {} --- {}", session.getUser().getUsername(), sqa.getSecurityQuestion().getQuestion(), sqa.getAnswer());
		}
		return session.getUser();
	}

	@Override
	public User addSecurityQuestions(SecurityQuestionsDTO securityQuestionsDTO) {
		return addSecurityQuestions(securityQuestionsDTO.getSecurityQuestionAnswers());
	}

	@Override
	public void validateNewUser(UserInfoDTO userInfoDTO, BindingResult result) {
		userInfoValidator.validate(userInfoDTO, result);
	}


}
