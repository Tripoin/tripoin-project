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
@XmlRootElement(name = "MenuTransferObject")
public class MenuTransferObject extends GeneralTransferObject {
	
	@XmlElement(name = "MenuDatas", namespace = "")
	private List<MenuData> menuDatas;

	public List<MenuData> getMenuDatas() {
		return menuDatas;
	}

	public void setMenuDatas(List<MenuData> menuDatas) {
		this.menuDatas = menuDatas;
	}

	@Override
	public String toString() {
		return "MenuTransferObject [menuDatas=" + menuDatas + "]";
	}
	
}
