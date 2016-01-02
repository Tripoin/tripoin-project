package com.tripoin.web.common;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public enum EWebSessionConstant {

	SESSION_OCUPATION_DATA("occupationData"),
	SESSION_EMPLOYEE_DATA("employeeData");

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
