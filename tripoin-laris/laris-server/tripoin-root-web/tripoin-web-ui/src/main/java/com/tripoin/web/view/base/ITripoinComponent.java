package com.tripoin.web.view.base;

public interface ITripoinComponent<PARAM, RESULT> {
	
	public void setParam(PARAM param);
	
	public PARAM getParam();
	
	public void setResult(RESULT result);
	
	public RESULT getResult() throws Exception;
	
}
