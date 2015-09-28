package com.tripoin.core.dto;

import java.util.List;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class UserMenuTransferObject extends GeneralTransferObject {

	private List<UserData> userDatas;
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
