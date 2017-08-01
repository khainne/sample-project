package com.vistana.service;

import java.util.Date;
import java.util.List;

import org.springframework.validation.BindingResult;

import com.vistana.domain.SecurityQuestionAnswer;
import com.vistana.domain.User;
import com.vistana.dto.SecurityQuestionsDTO;
import com.vistana.dto.UserInfoDTO;

public interface NewUserService {
	User createNewUser(String username, Date dob);
	User createNewUser(UserInfoDTO userInfoDTO);
	User addSecurityQuestions(User user, List<SecurityQuestionAnswer> securityQuestionAnswers);
	User addSecurityQuestions(User user, SecurityQuestionsDTO securityQuestionsDTO);
	void validateNewUser(UserInfoDTO userInfoDTO, BindingResult result);
}
