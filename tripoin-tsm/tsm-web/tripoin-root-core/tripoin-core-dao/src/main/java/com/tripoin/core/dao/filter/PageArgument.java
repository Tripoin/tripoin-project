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
	
	private Integer firstPage;

	private Integer maxPage;
	
	private Integer maxResultPage;
	
	public PageArgument() {}
	
	public PageArgument(Integer firstPage) {
		super();
		this.firstPage = firstPage;
	}

	public PageArgument(Integer firstPage, Integer maxPage) {
		super();
		this.firstPage = firstPage;
		this.maxPage = maxPage;
	}

	public PageArgument(Integer firstPage, Integer maxPage, Integer maxResultPage) {
		super();
		this.firstPage = firstPage;
		this.maxPage = maxPage;
		this.maxResultPage = maxResultPage;
	}

	public Integer getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(Integer firstPage) {
		this.firstPage = firstPage;
	}

	public Integer getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}

	public Integer getMaxResultPage() {
		return maxResultPage;
	}

	public void setMaxResultPage(Integer maxResultPage) {
		this.maxResultPage = maxResultPage;
	}

	@Override
	public String toString() {
		return "PageArgument [firstPage=" + firstPage + ", maxPage=" + maxPage
				+ ", maxResultPage=" + maxResultPage + "]";
	}
	
}
