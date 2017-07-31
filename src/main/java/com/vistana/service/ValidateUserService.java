package com.vistana.service;

import com.vistana.domain.User;
import com.vistana.dto.ValidateUserDTO;

public interface ValidateUserService {
	ValidateUserDTO getValidationQuestionForUser(User user);
	Boolean validateLogin(String username);
}
