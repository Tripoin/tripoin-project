package com.tripoin.core.dao.filter;

import java.io.Serializable;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class ValueArgument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5692797598636188652L;
	
	private String field;
	
	private Object value;

	public ValueArgument() {}

	public ValueArgument(String field) {
		super();
		this.field = field;
	}

	public ValueArgument(String field, Object value) {
		super();
		this.field = field;
		this.value = value;
	}	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ValueArgument [field=" + field + ", value=" + value + "]";
	}
	
}
