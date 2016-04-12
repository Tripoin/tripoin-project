package com.tripoin.web.common;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public enum EWebSessionConstant {

	SESSION_GRID_DATA("sessionGridData"),
	SESSION_OCUPATION_DATA("occupationData"),
	SESSION_EMPLOYEE_DATA("employeeData"),

	SESSION_API_FACEBOOK_DATA("dtoResponseCallbackFacebook"),
	SESSION_API_INSTAGRAM_DATA("dtoResponseCallbackInstagram"),
	SESSION_API_TWITTER_DATA("dtoResponseCallbackTwitter"),
	SESSION_API_GPLUS_DATA("dtoResponseCallbackGooglePlus"),
	SESSION_API_LINE_DATA("dtoResponseCallbackLine");

	private String operator;	
	
	private EWebSessionConstant(String operator){
		this.operator = operator ;
	}
	
	public String getOperator() {
		return operator;
	}

	@Override
	public String toString() {
		return operator;
	}
	
}
