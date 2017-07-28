package com.vistana.service;

import java.util.Date;
import java.util.List;

import com.vistana.domain.SecurityQuestionAnswer;
import com.vistana.domain.User;
import com.vistana.dto.SecurityQuestionsDTO;
import com.vistana.dto.UserInfoDTO;

public interface NewUserService {
	User createNewUser(String username, Date dob);
	User createNewUser(UserInfoDTO userInfoDTO);
	User addSecurityQuestions(List<SecurityQuestionAnswer> securityQuestionAnswers);
	User addSecurityQuestions(SecurityQuestionsDTO securityQuestionsDTO);
}
