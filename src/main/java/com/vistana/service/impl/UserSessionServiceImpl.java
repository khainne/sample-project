package com.vistana.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vistana.domain.SecurityQuestionAnswer;
import com.vistana.domain.User;
import com.vistana.exception.UserNotFoundException;
import com.vistana.service.UserSessionService;
import com.vistana.session.ApplicationSession;

@Service
public class UserSessionServiceImpl implements UserSessionService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApplicationSession session;
	
	@Override
	public User createNewUser(User user) {
		session.setNewUser(user);
		return user;
	}
	
	@Override
	public User addUserToList(User user) {
		session.getUsers().add(user);
		return user;
	}

	@Override
	public User addSecurityQuestionsToNewUser(List<SecurityQuestionAnswer> sqaList) {
		session.getNewUser().setSecurityQuestionAnswers(sqaList);
		return session.getNewUser();
	}

	@Override
	public void logoutUser() {
		session.setIsLoggedIn(false);
		session.setUser(null);
		session.setIsValidated(false);
	}

	@Override
	public User validateLoggedInUser(User user) {
		session.setNewUser(null);
		session.setIsValidated(true);
		session.setUser(user);
		return user;
	}

	@Override
	public User loginUser(User user) {
		session.setIsLoggedIn(true);
		return user;
	}

	@Override
	public User getNewUser() {
		return session.getNewUser();
	}

	@Override
	public boolean getIsLoggedIn() {
		return session.getIsLoggedIn();
	}

	@Override
	public boolean getIsValidated() {
		return session.getIsValidated();
	}

	@Override
	public User validateLogin(String username) throws UserNotFoundException {
		
		if(username == null || session == null || session.getUsers() == null) {
			throw new UserNotFoundException("user name can not be null");
		}
		
		for (User user : session.getUsers()) {
			if (username.trim().toLowerCase().equals(user.getUsername()) && user.isValid()) {
				validateLoggedInUser(user);
				logger.info("{} is a valid username!", username);
				return user;
			}
		}
		
		logger.error("{} is not valid username!", username);
		throw new UserNotFoundException("user not found");
	}

	@Override
	public User getValidatedUser() {
		return session.getUser();
	}




}
