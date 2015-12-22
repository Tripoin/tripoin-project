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
	protected String pageName;
	
	@XmlElement(name = "MinRow", namespace = "")
	private Integer minRow;
	
	@XmlElement(name = "MaxRow", namespace = "")
	private Integer maxRow;
	
	@XmlElement(name = "TotalRow", namespace = "")
	protected Integer totalRow;

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

	public Integer getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

	@Override
	public String toString() {
		return "GeneralPagingTransferObject [pageName=" + pageName
				+ ", minRow=" + minRow + ", maxRow=" + maxRow + ", totalRow="
				+ totalRow + "]";
	}

}
