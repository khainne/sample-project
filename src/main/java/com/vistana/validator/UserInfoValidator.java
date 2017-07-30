package com.vistana.validator;



import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import com.vistana.dto.UserInfoDTO;

@Component
public class UserInfoValidator implements Validator {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserInfoDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.info("Starting validation of user info form submission");
		UserInfoDTO userInfo = (UserInfoDTO) target;
		
		if(StringUtils.isBlank(userInfo.getUsername()) || !usernameIsValid(userInfo.getUsername())){
		    errors.rejectValue("username", "error.user.username.invalid");
		    logger.error("Username: is invalid");
		}
		
		//check to see if there's already a type mismatch on this field
		if(errors.getFieldErrorCount("dob") == 0) {
			Calendar cal = Calendar.getInstance();
			try {
			    cal.setTime(userInfo.getDob());
				if (!dobIsValid(userInfo.getDob())) {
					errors.rejectValue("dob", "error.user.dob.invalid");
					logger.error("Date of Birth: does not fit business rules, 13+ and less than 120");
				}
			}
			catch (Exception e) {
				errors.rejectValue("dob", "error.user.dob.invalid");
				logger.error("Date of Birth: invalid date format");
			}
		} else {
			logger.error("Date of Birth: Existing errors on date of birth, possiblde type mismatch");
		}
		
	}

	private Boolean dobIsValid(Date dob) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.YEAR, -13);
		Date maxDob = now.getTime();
		now = Calendar.getInstance();
		now.add(Calendar.YEAR, -120);
		Date minDob = now.getTime();
		
		return dob.after(minDob) && dob.before(maxDob);
	}
	
	//Only allow alphanumeric values and length must be between 5 and 12 characters
	private Boolean usernameIsValid(String username) {
		Boolean isValid = false;
		String alphaNumericPattern= "^[a-zA-Z0-9]*$";
	    if (username.length() >= 5 && username.length() <= 12 && username.matches(alphaNumericPattern)) {
			isValid = true;
		}
		return isValid;
	}

}
