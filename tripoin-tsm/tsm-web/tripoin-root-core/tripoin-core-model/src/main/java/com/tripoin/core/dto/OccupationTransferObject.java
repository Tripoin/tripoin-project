package com.tripoin.core.dto;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OccupationTransferObject")
public class OccupationTransferObject extends GeneralPagingTransferObject<OccupationData> {
	
	@XmlElement(name = "OccupationDatas", namespace = "")
	private List<OccupationData> occupationDatas;
	
	@XmlElement(name = "FindOccupationData", namespace = "")
	private Map<String, Object> findOccupationData;

	@Override
	public List<OccupationData> getDatas() {
		return occupationDatas;
	}

	public List<OccupationData> getOccupationDatas() {
		return occupationDatas;
	}

	public void setOccupationDatas(List<OccupationData> occupationDatas) {
		this.occupationDatas = occupationDatas;
	}

	public Map<String, Object> getFindOccupationData() {
		return findOccupationData;
	}

	public void setFindOccupationData(Map<String, Object> findOccupationData) {
		this.findOccupationData = findOccupationData;
	}

	@Override
	public String toString() {
		return "OccupationTransferObject [occupationDatas=" + occupationDatas
				+ ", findOccupationData=" + findOccupationData + "]";
	}

	public enum EnumFieldOccupation {
		CODE_OCCUPATION("code"),
		NAME_OCCUPATION("name"),
		DESCRIPTION_OCCUPATION("remarks");
		
		private String operator;		
		private EnumFieldOccupation(String operator){this.operator=operator;}
		public String toString() {return operator;}
	}
	
}
