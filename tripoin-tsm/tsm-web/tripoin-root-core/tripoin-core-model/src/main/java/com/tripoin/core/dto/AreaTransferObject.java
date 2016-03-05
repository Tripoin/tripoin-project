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
@XmlRootElement(name = "AreaTransferObject")
public class AreaTransferObject extends ABaseDataTransferObject<AreaData> {

	@XmlElement(name = "AreaDatas", namespace = "")
	private List<AreaData> areaDatas;

	@Override
	public List<AreaData> getDatas() {
		return areaDatas;
	}

	public List<AreaData> getAreaDatas() {
		return areaDatas;
	}

	public void setAreaDatas(List<AreaData> areaDatas) {
		this.areaDatas = areaDatas;
	}

	@Override
	public String toString() {
		return "AreaTransferObject [areaDatas=" + areaDatas + "]";
	}

	public enum EnumFieldArea {
		CODE_AREA("code"),
		NAME_AREA("name"),
		DESCRIPTION_AREA("remarks");

		private String operator;

		private EnumFieldArea(String operator) {
			this.operator = operator;
		}

		public String toString() {
			return operator;
		}
	}

}
