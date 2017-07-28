package com.vistana.service.impl;

import java.util.Date;
import java.util.List;

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
		
		return user;
	}

	@Override
	public User createNewUser(UserInfoDTO userInfoDTO) {
		return createNewUser(userInfoDTO.getUsername(), userInfoDTO.getDob());
	}

	
	@Override
	public User addSecurityQuestions(List<SecurityQuestionAnswer> securityQuestionAnswers) {
		session.getUser().setSecurityQuestionAnswers(securityQuestionAnswers);
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
