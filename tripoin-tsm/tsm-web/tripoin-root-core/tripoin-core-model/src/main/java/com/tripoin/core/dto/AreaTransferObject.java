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
@XmlRootElement(name = "AreaTransferObject")
public class AreaTransferObject extends GeneralPagingTransferObject<AreaData> {

	@XmlElement(name = "AreaDatas", namespace = "")
	private List<AreaData> areaDatas;

	@XmlElement(name = "FindAreaData", namespace = "")
	private Map<String, Object> findAreaData;

	@Override
	public List<AreaData> getDatas() {
		// TODO Auto-generated method stub
		return areaDatas;
	}

	public List<AreaData> getAreaDatas() {
		return areaDatas;
	}

	public void setAreaDatas(List<AreaData> areaDatas) {
		this.areaDatas = areaDatas;
	}

	public Map<String, Object> getFindAreaData() {
		return findAreaData;
	}

	public void setFindAreaData(Map<String, Object> findAreaData) {
		this.findAreaData = findAreaData;
	}

	@Override
	public String toString() {
		return "AreaTransferObject [areaDatas=" + areaDatas + ", findAreaData="
				+ findAreaData + "]";
	}

	public enum EnumFieldArea {
		NAME_AREA("name"), DESCRIPTION_AREA("description");

		private String operator;

		private EnumFieldArea(String operator) {
			this.operator = operator;
		}

		public String toString() {
			return operator;
		}
	}

}
