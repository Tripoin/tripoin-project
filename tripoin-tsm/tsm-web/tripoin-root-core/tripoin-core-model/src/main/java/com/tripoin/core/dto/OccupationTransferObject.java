package com.tripoin.core.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OccupationTransferObject")
public class OccupationTransferObject extends GeneralTransferObject {
	
	@XmlElement(name = "OccupationDatas", namespace = "")
	private List<OccupationData> occupationDatas;
	
	@XmlElement(name = "TotalRow", namespace = "")
	private Integer totalRow;
	
	@XmlElement(name = "FirstPage", namespace = "")
	private Integer firstPage;
	
	@XmlElement(name = "MaxPage", namespace = "")
	private Integer maxPage;

	public List<OccupationData> getOccupationDatas() {
		return occupationDatas;
	}

	public void setOccupationDatas(List<OccupationData> occupationDatas) {
		this.occupationDatas = occupationDatas;
	}

	public Integer getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
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

	@Override
	public String toString() {
		return "OccupationTransferObject [occupationDatas=" + occupationDatas
				+ ", totalRow=" + totalRow + ", firstPage=" + firstPage
				+ ", maxPage=" + maxPage + "]";
	}
	
}
