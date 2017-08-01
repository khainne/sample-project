package com.vistana.service;

import java.util.List;

import com.vistana.domain.SecurityQuestionAnswer;
import com.vistana.domain.User;
import com.vistana.exception.UserNotFoundException;

public interface UserSessionService {
	User createNewUser(User user);
	User addUserToList(User user);
	User addSecurityQuestionsToNewUser(List<SecurityQuestionAnswer> sqaList);
	void logoutUser();
	User validateLoggedInUser(User user);
	User loginUser(User user);
	User getNewUser();
	User getValidatedUser();
	boolean getIsLoggedIn();
	boolean getIsValidated();
	User validateLogin(String username) throws UserNotFoundException;
}
