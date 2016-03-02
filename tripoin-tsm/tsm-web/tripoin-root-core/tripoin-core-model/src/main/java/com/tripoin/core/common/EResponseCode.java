package com.tripoin.core.common;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public enum EResponseCode {

	RC_SUCCESS("00", "Web Service Success"),
	RC_FAILURE("01", "Web Service Failure "),
	RC_USERNAME_EXISTS("02", "Username already exists, please try others!"),
	RC_EMAIL_EXISTS("03", "Email already exists, please try others!"),
	RC_PHONE_EXISTS("04", "Mobile Phone already exists, please try others!"),
	RC_NIK_EXISTS("05", "NIK already exists, please try others!"),
	RC_OCCUPATION_EXISTS("06", "Occupation already exists, please try others!"),
	RC_AREA_EXISTS("07", "Area already exists, please try others!"),

	RC_TYPEFILE_NOTALLOW("70", "Type File not Allowed"),
	RC_MAX_FILE_SIZE("80", "Maximum file upload"),
	RC_FAIL_UPLOAD("90", "File upload failure "),
	
	RC_OCCUPATION_USED("16", "Occupation is being used"),
	RC_AREA_USED("17", "Area is being used");

	private String operator;
	private String operand;
	
	private EResponseCode(String operator){
		this.operator = operator ;
	}	
	
	private EResponseCode(String operator, String operand){
		this.operator = operator ;
		this.operand = operand;
	}
	
	public String getResponseCode() {
		return operator;
	}

	@Override
	public String toString() {
		return operand;
	}
	
}
