package com.vistana.validator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.vistana.domain.SecurityQuestionAnswer;
import com.vistana.dto.SecurityQuestionsDTO;

@Component
public class SecurityQuestionValidator implements Validator {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SecurityQuestionsDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.info("Starting validation of security questions");
		SecurityQuestionsDTO securityQuestions = (SecurityQuestionsDTO) target;
		
		int i = 0;
		for(SecurityQuestionAnswer sqa : securityQuestions.getSecurityQuestionAnswers()) {
			if(sqa.getSecurityQuestion() == null) {
				errors.rejectValue("securityQuestionAnswers[" + i + "].securityQuestion", "error.security.question.invalid");
				logger.error("Security Quesiton {}: can not be empty", Integer.toString(i));
			}
			if(StringUtils.isBlank(sqa.getAnswer())) {
				errors.rejectValue("securityQuestionAnswers[" + i + "].answer", "error.security.answer.invalid");
				logger.error("Security Answer {}: can not be empty", Integer.toString(i));
			}
			
			for (int j = i + 1; j < securityQuestions.getSecurityQuestionAnswers().size() - 1; j++) {
				if (sqa.getSecurityQuestion().equals(securityQuestions.getSecurityQuestionAnswers().get(j).getSecurityQuestion())) {
					errors.rejectValue("securityQuestionAnswers[" + j + "].securityQuestion", "error.security.question.duplicate");
					securityQuestions.getSecurityQuestionAnswers().get(j).setSecurityQuestion(null);
					logger.error("Security Question {}: is a dupe!", Integer.toString(j));
				}
			}
			
			i++;
		}
		

	}

}
