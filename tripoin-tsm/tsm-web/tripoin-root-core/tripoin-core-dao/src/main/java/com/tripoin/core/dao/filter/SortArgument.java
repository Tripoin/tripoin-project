package com.tripoin.core.dao.filter;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class SortArgument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5841873263824709623L;
	
	private String[] field;

	private Boolean isAsc;
	
	public SortArgument() {}

	public SortArgument(String[] field) {
		super();
		this.field = field;
	}

	public SortArgument(String[] field, boolean isAsc) {
		super();
		this.field = field;
		this.isAsc = isAsc;
	}

	public String[] getField() {
		return field;
	}

	public void setField(String[] field) {
		this.field = field;
	}

	public Boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	@Override
	public String toString() {
		return "SortArgument [field=" + Arrays.toString(field) + ", isAsc="
				+ isAsc + "]";
	}
	
}
