package com.tripoin.core.dto;

import java.util.List;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class MenuTransferObject extends GeneralTransferObject {
	
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
