package com.tripoin.core.dao.filter;

import java.io.Serializable;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class FilterArgument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5692797598636188652L;
	
	private String field;
	
	private ECommonOperator condition;

	public FilterArgument() {}

	public FilterArgument(String field) {
		super();
		this.field = field;
	}

	public FilterArgument(String field, ECommonOperator condition) {
		super();
		this.field = field;
		this.condition = condition;
	}	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public ECommonOperator getCondition() {
		return condition;
	}

	public void setCondition(ECommonOperator condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "FilterArgument [field=" + field + ", condition=" + condition
				+ "]";
	}
	
}
