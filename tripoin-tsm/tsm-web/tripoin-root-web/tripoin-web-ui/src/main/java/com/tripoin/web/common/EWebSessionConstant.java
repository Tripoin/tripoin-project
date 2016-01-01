package com.tripoin.web.common;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public enum EWebSessionConstant {

	SESSION_OCUPATION_DATA("occupationData"),
	SESSION_OCUPATION_DATA_SEARCH("occupationDataSearch"),
	SESSION_OCUPATION_POSITION_PAGE("occupationPositionPage"),
	SESSION_EMPLOYEE_DATA("employeeData"),
	SESSION_EMPLOYEE_DATA_SEARCH("employeeDataSearch"),
	SESSION_EMPLOYEE_POSITION_PAGE("employeePositionPage");

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
