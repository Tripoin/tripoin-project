package com.tripoin.core.dao.filter;

import java.io.Serializable;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class PageArgument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5841873263824709623L;
	
	private Integer minRow;

	private Integer maxRow;
	
	public PageArgument() {}
	
	public PageArgument(Integer minRow) {
		super();
		this.minRow = minRow;
	}

	public PageArgument(Integer minRow, Integer maxRow) {
		super();
		this.minRow = minRow;
		this.maxRow = maxRow;
	}

	public Integer getMinRow() {
		return minRow;
	}

	public void setMinRow(Integer minRow) {
		this.minRow = minRow;
	}

	public Integer getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(Integer maxRow) {
		this.maxRow = maxRow;
	}

	@Override
	public String toString() {
		return "PageArgument [minRow=" + minRow + ", maxRow=" + maxRow + "]";
	}
	
}
