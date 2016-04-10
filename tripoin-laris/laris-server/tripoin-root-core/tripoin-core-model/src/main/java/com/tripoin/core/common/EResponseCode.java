package com.tripoin.core.common;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public enum EResponseCode {

	RC_SUCCESS("00", "Web Service Success"),
	RC_FAILURE("01", "Web Service Failure "),
	RC_USED("302", "Some data already being used"),
	
	RC_USERNAME_EXISTS("02", "Username already exists, please try others!"),
	RC_EMAIL_EXISTS("03", "Email already exists, please try others!"),
	RC_PHONE_EXISTS("04", "Phone No. already exists, please try others!"),
	RC_NIK_EXISTS("05", "NIK already exists, please try others!"),
	RC_OCCUPATION_EXISTS("06", "Occupation already exists, please try others!"),
	RC_AREA_EXISTS("07", "Area already exists, please try others!"),
	RC_USERNAME_NOT_EXISTS("08", "Username not exsits"),

	RC_GENDER_NOT_DEFINE("10", "Gender Not Define"),
	RC_SALESMAN_REALLOCATE("11", "Salesman Current must be reallocate!"),
	RC_EMPLOYEE_PARENT_NOTNULL("12", "Head not null"),
	RC_USERNAME_NIK_EMAIL_EXISTS("13", "Username / NIK / Email already exists, please try others!"),
	RC_AREA_NOTNULL("17", "Area cannot be null"),

	RC_TYPEFILE_NOTALLOW("70", "Type File not Allowed"),
	RC_MAX_FILE_SIZE("80", "Maximum file upload"),
	RC_FAIL_UPLOAD("90", "File upload failure "),
	RC_FAIL_PASSWORD("100", "Password not valid"),

	RC_EMAIL_NOTREGISTER("200", "Email is not registered"),
	RC_ACCOUNT_EXPIRED("201", "Account is expired"),
	RC_ACCOUNT_NOTACTIVE("202", "Account is no longer active"),
	RC_URL_EXPIRED("203", "Link has been expired"),
	RC_URL_NOTFOUND("204", "Link is not found");

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
