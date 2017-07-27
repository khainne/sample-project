package com.vistana.enumeration;

public enum SecurityQuestion {
	
		HOUSE_NUMBER("What was the house number and street name you lived in as a child?"),
		PHONE_NUMBER("What were the last four digits of your childhood telephone number?"),
		FIRST_JOB("In what town or city was your first full time job?"),
		SPOUSE_TOWN("In what town or city did you meet your spouse/partner?"),
		OLDEST_CHILD("What is the middle name of your oldest child?"),
		MAIDEN_NAME("What is your grandmother's (on your mother's side) maiden name?"),
		PARENTS_MEET("In what town or city did your mother and father meet?")
		;
	
	private final String question;
	
    SecurityQuestion(String question) {
        this.question = question;
    }

	public String getQuestion() {
		return question;
	}
}
