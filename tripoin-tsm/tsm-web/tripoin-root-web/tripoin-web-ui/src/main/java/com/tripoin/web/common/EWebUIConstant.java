package com.tripoin.web.common;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public enum EWebUIConstant {
	
	LOGIN_FAILED_TITLE("Login Failed"),
	LOGIN_FAILED_DESC("Please check username and password and try again."),
	LOGIN_USERNAME_NULL_DESC("Username empty."),
	LOGIN_PASSWORD_NULL_DESC("Password empty."),

    NOTIF_CAPTCHA_FAILURE_TITLE("Captcha Failure"),		
    NOTIF_CAPTCHA_NOTVALID_DESC("Captcha is not valid."),
	NOTIF_SUCCESS_FORGOT_PASSWORD_TITLE("Email Confirmation"),
	NOTIF_SUCCESS_FORGOT_PASSWORD_DESC("Confirmation change password has been sent, please check your email!\n"
        		+ "Keep me logged in."),
    NOTIF_FAILURE_FORGOT_PASSWORD_TITLE("Warning"),
    NOTIF_EMAIL_FAILURE_FORGOT_PASSWORD_TITLE("Email Failure"),
    NOTIF_EMAIL_FAILURE_VERIFY_FORGOT_PASSWORD_TITLE("Link Failure"),
    NOTIF_ACCOUNT_EXPIRED_FORGOT_PASSWORD_DESC("Account has been expired."),
    NOTIF_ACOUNT_ENABLED_FORGOT_PASSWORD_DESC("Account is no longer active."),	
    NOTIF_EMAIL_NULL_FORGOT_PASSWORD_DESC("Email is not registered."),		
    NOTIF_EMAIL_NOTVALID_FORGOT_PASSWORD_DESC("Email is not valid."),
    NOTIF_LINK_EXPIRED_FORGOT_PASSWORD_DESC("Link has been is expired."),
    NOTIF_LINK_NULL_FORGOT_PASSWORD_DESC("Link is not found."),

    REGEX_EMAIL("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"),
	REGEX_USERNAME("^[a-zA-Z0-9_.@-]{5,55}$"),
	REGEX_PASSWORD("((?=\\S+$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,20})"),
	REGEX_CONTAINS_LOWERUPERCASE("((?=\\S+$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})"),
	REGEX_CONTAINS_DIGIT("((?=\\S+$)(?=.*\\d).{6,20})"),
	REGEX_CONTAINS_WHITESPACE("((?=\\S+$).{6,20})"),
	REGEX_CONTAINS_MINMAX(".{6,20}"),
	REGEX_AUTHORIZATION("Basic |\\[|\\]"),
	
	AUTHORIZATION("Authorization"),
	
    COMING("Coming"), AVAILABLE("Available"), DISCONTINUED("Discontinued"),
	
	HOME_VIEW(""),
	NAVIGATE_NULL("#!"),
	
	TYPE_FILE_IMAGE("image"),
	
	ROW_PER_PAGE(20);

	private String operator;
	private Integer operatorInteger;	
	
	private EWebUIConstant(String operator){
		this.operator = operator ;
	}	
	
	private EWebUIConstant(Integer operatorInteger){
		this.operatorInteger = operatorInteger ;
	}
	
	public String getOperator() {
		return operator;
	}

	public Integer getOperatorInteger() {
		return operatorInteger;
	}

	@Override
	public String toString() {
		if(operator == null)
			return operatorInteger.toString();
		return operator;
	}
	
}
