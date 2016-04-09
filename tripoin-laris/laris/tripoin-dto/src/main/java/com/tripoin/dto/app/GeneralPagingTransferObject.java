package com.tripoin.dto.app;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class GeneralPagingTransferObject extends GeneralTransferObject {

	String pageName;
	Integer positionPage;
	Integer totalPage;
	Integer rowPerPage;

	public GeneralPagingTransferObject(){}

	public GeneralPagingTransferObject(String pageName) {
		this.pageName = pageName;
	}	

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public Integer getPositionPage() {
		return positionPage;
	}

	public void setPositionPage(Integer positionPage) {
		this.positionPage = positionPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getRowPerPage() {
		return rowPerPage;
	}

	public void setRowPerPage(Integer rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	@Override
	public String toString() {
		return "GeneralPagingTransferObject [pageName=" + pageName
				+ ", positionPage=" + positionPage + ", totalPage=" + totalPage
				+ ", rowPerPage=" + rowPerPage + "]";
	}

}
