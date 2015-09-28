package com.tripoin.web.common;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public enum EWebUIConstant {
	
	LOGIN_FAILED_TITLE("Login Failed"),
	LOGIN_FAILED_DESC("Please check username and password and try again."),
	LOGIN_USERNAME_NULL_DESC("Username empty."),
	LOGIN_PASSWORD_NULL_DESC("Password empty."),
	
	REGEX_USERNAME("^[a-z0-9_-]{5,15}$"),
	
    COMING("Coming"), AVAILABLE("Available"), DISCONTINUED("Discontinued"),
	
	HOME_VIEW(""),
	NAVIGATE_NULL("#!");
	
	private String operator;	
	
	private EWebUIConstant(String operator){
		this.operator = operator ;
	}
	
	@Override
	public String toString() {
		return operator;
	}
	
}
