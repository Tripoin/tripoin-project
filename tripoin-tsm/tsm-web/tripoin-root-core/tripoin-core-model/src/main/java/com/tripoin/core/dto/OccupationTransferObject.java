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

	public List<OccupationData> getOccupationDatas() {
		return occupationDatas;
	}

	public void setOccupationDatas(List<OccupationData> occupationDatas) {
		this.occupationDatas = occupationDatas;
	}

	@Override
	public String toString() {
		return "OccupationTransferObject [occupationDatas=" + occupationDatas
				+ "]";
	}
	
}
