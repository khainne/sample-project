package com.vistana.dto;

import java.util.List;

import com.vistana.domain.SecurityQuestionAnswer;

public class SecurityQuestionsDTO {
	private List<SecurityQuestionAnswer> securityQuestionAnswers;

	public List<SecurityQuestionAnswer> getSecurityQuestionAnswers() {
		return securityQuestionAnswers;
	}

	public void setSecurityQuestionAnswers(List<SecurityQuestionAnswer> securityQuestionAnswers) {
		this.securityQuestionAnswers = securityQuestionAnswers;
	}
}
