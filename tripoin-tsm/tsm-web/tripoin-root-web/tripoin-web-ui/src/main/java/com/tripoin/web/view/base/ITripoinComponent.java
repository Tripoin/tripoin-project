package com.tripoin.web.view.base;

public interface ITripoinComponent<PARAM,RESULT> {
	void setParam(PARAM p_param);
	PARAM getParam();
	void setResult(RESULT p_result);
	
	RESULT getResult()throws Exception;
	
}
