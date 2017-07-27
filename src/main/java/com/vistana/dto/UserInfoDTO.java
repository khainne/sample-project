package com.vistana.dto;

import java.util.Date;

public class UserInfoDTO {
	private String username;
	private Date dob;
	
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
}
