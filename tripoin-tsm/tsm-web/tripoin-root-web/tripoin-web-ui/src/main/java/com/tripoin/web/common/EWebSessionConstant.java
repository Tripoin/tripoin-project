package com.tripoin.web.common;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public enum EWebSessionConstant {

	SESSION_OCUPATION_DATA("occupationData"),
	SESSION_OCUPATION_DATA_SEARCH("occupationDataSearch"),
	SESSION_OCUPATION_POSITION_PAGE("occupationPositionPage");

	private String operator;
	private Integer operatorInteger;	
	
	private EWebSessionConstant(String operator){
		this.operator = operator ;
	}	
	
	private EWebSessionConstant(Integer operatorInteger){
		this.operatorInteger = operatorInteger ;
	}
	
	public String getString() {
		return operator;
	}

	public Integer getInt() {
		return operatorInteger;
	}

	@Override
	public String toString() {
		if(operator == null)
			return operatorInteger.toString();
		return operator;
	}
	
}
