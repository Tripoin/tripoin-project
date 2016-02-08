package com.tripoin.web.view.base;

import org.springframework.beans.factory.annotation.Value;

import com.tripoin.web.view.exception.TripoinViewException;
import com.vaadin.ui.FormLayout;

public class SearchContainer extends FormLayout implements ITripoinComponent<SearchPanel, SearchContainer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1575878452013068877L;
	private SearchPanel searchPanel;
	private SearchContainer searchContainer;
	
	private String msg;
	
	public String getMsg() {
		return msg;
	}
	@Value("${searchcontainer.getresult.error}")
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public void setParam(SearchPanel p_param) {
		this.searchPanel=p_param;
		addComponent(searchPanel);
	}

	@Override
	public SearchPanel getParam() {
		return searchPanel;
	}

	@Override
	public void setResult(SearchContainer p_result) {
		this.searchContainer=p_result;
		
	}

	@Override
	public SearchContainer getResult() throws Exception{
		if(this.searchContainer!=null){
			return searchContainer;
		}else{
			throw new TripoinViewException(msg);
		}
	}

}
