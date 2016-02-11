package com.tripoin.web.common;

/**
* @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
*/
public enum EReportUIConstant {
	
	REPORT_PDF(".pdf", "application/pdf"),
	REPORT_EXCEL(".xls", "application/vnd.ms-excel"),
	REPORT_CSV(".csv", "text/csv"),
	REPORT_TEXT(".txt", "text/plain");

	private String operator;
	private String operand;
	
	private EReportUIConstant(String operator){
		this.operator = operator ;
	}	
	
	private EReportUIConstant(String operator, String operand){
		this.operator = operator ;
		this.operand = operand;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public String getOperand(){
		return operand;
	}

	@Override
	public String toString() {
		return operator;
	}

}
