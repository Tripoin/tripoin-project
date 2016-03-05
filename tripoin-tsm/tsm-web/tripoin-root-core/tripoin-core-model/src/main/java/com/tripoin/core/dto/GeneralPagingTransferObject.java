package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GeneralPagingTransferObject")
public class GeneralPagingTransferObject extends GeneralTransferObject {
	
	@XmlElement(name = "PageName", namespace = "")
	private String pageName;
	
	@XmlElement(name = "PositionPage", namespace = "")
	private Integer positionPage;
	
	@XmlElement(name = "TotalPage", namespace = "")
	private Integer totalPage;
	
	@XmlElement(name = "RowPerPage", namespace = "")
	private Integer rowPerPage;

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
