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
public class OccupationTransferObject extends ABaseDataTransferObject<OccupationData> {
	
	@XmlElement(name = "OccupationDatas", namespace = "")
	private List<OccupationData> occupationDatas;

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

	@Override
	public String toString() {
		return "OccupationTransferObject [occupationDatas=" + occupationDatas + "]";
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
