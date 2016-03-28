package com.tripoin.core.common;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public enum APIConstant {

	INSTAGRAM("INSTAGRAM"),
	CLIENT_ID("client_id"),
	CLIENT_SECRET("client_secret"),
	GRANT_TYPE("grant_type"),
	AUTHORIZATION_CODE("authorization_code"),
	REDIRECT_URI("redirect_uri"),
	CODE("code"),
	SCOPE("scope"),
	BASIC("basic"),
	PUBLIC_CONTENT("public_content"),
	FOLLOWER_LIST("follower_list"),
	COMMENTS("comments"),
	RELATIONSHIPS("relationships"),
	LIKES("likes"),
	
	FACEBOOK("FACEBOOK");

	private String operator;
	private String operand;
	
	private APIConstant(String operator){
		this.operator = operator ;
	}
	
	private APIConstant(String operator, String operand){
		this.operator = operator;
		this.operand = operand;
	}
	
	public String getOperator() {
		return operator;
	}

	public String getOperand() {
		return operand;
	}

	@Override
	public String toString() {
		return operator;
	}
	
}
