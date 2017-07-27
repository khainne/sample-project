package com.vistana.domain;

import java.util.Date;
import java.util.List;

public class User {
	
	private String username;
	private Date dob;
	private List<SecurityQuestionAnswer> securityQuestionAnswers;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public List<SecurityQuestionAnswer> getSecurityQuestionAnswers() {
		return securityQuestionAnswers;
	}
	public void setSecurityQuestions(List<SecurityQuestionAnswer> securityQuestionAnswers) {
		this.securityQuestionAnswers = securityQuestionAnswers;
	}
	
}