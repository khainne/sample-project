package com.vistana.dto;

import com.vistana.enumeration.SecurityQuestion;

public class ValidateUserDTO {
	private SecurityQuestion question;
	private String answer;
	
	public SecurityQuestion getQuestion() {
		return question;
	}
	public void setQuestion(SecurityQuestion question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
