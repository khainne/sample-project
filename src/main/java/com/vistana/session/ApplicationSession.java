package com.vistana.session;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.vistana.domain.User;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicationSession {
	
	public ApplicationSession() {
		users = new ArrayList<User>();
	}
	
	//List of all users created
	private List<User> users;
	
	//Logged in user
	private User user;
	private boolean isLoggedIn;
	private boolean isValidated;
	
	//New user form
	private User newUser;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	
	public void setIsLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public boolean getIsValidated() {
		return isValidated;
	}
	
	public void setIsValidated(boolean isValidated) {
		this.isValidated = isValidated;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public User getNewUser() {
		return newUser;
	}
	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}
	
}
