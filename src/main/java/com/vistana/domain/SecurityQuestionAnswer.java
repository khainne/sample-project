package com.vistana.domain;

import com.vistana.enumeration.SecurityQuestion;

public class SecurityQuestionAnswer {
	
	private SecurityQuestion securityQuestion;
	private String answer;
	
	public SecurityQuestion getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(SecurityQuestion securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer.trim().toLowerCase();
	}
}
