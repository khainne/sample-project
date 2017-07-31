package com.vistana.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.vistana.domain.User;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicationSession {
	private User user;
	private boolean isLoggedIn;
	private boolean isValidated;
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
	
}