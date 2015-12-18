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
	
	@XmlElement(name = "FirstRow", namespace = "")
	private Integer firstRow;
	
	@XmlElement(name = "LastRow", namespace = "")
	private Integer lastRow;

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

	public Integer getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(Integer firstRow) {
		this.firstRow = firstRow;
	}

	public Integer getLastRow() {
		return lastRow;
	}

	public void setLastRow(Integer lastRow) {
		this.lastRow = lastRow;
	}

	@Override
	public String toString() {
		return "OccupationTransferObject [occupationDatas=" + occupationDatas
				+ ", totalRow=" + totalRow + ", firstRow=" + firstRow
				+ ", lastRow=" + lastRow + "]";
	}
	
}
