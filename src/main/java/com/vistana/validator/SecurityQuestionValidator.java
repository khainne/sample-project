package com.vistana.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.vistana.domain.SecurityQuestionAnswer;
import com.vistana.dto.SecurityQuestionsDTO;

@Component
public class SecurityQuestionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SecurityQuestionsDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SecurityQuestionsDTO securityQuestions = (SecurityQuestionsDTO) target;
		
		int i = 0;
		for(SecurityQuestionAnswer sqa : securityQuestions.getSecurityQuestionAnswers()) {
			if(sqa.getSecurityQuestion() == null) {
				errors.rejectValue("securityQuestionAnswers[" + i + "].securityQuestion", "error.security.question.invalid");
			}
			if(StringUtils.isBlank(sqa.getAnswer())) {
				errors.rejectValue("securityQuestionAnswers[" + i + "].answer", "error.security.answer.invalid");
			}
			i++;
		}
		
	}

}
