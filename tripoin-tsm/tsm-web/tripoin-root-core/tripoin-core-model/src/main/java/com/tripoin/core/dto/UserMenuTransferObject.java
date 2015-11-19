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
@XmlRootElement(name = "UserMenuTransferObject")
public class UserMenuTransferObject extends GeneralTransferObject {

	@XmlElement(name = "UserDatas", namespace = "")
	private List<UserData> userDatas;
	
	@XmlElement(name = "MenuDatas", namespace = "")
	private List<MenuData> menuDatas;

	public List<UserData> getUserDatas() {
		return userDatas;
	}

	public void setUserDatas(List<UserData> userDatas) {
		this.userDatas = userDatas;
	}

	public List<MenuData> getMenuDatas() {
		return menuDatas;
	}

	public void setMenuDatas(List<MenuData> menuDatas) {
		this.menuDatas = menuDatas;
	}

	@Override
	public String toString() {
		return "UserMenuTransferObject [userDatas=" + userDatas
				+ ", menuDatas=" + menuDatas + "]";
	}

}
