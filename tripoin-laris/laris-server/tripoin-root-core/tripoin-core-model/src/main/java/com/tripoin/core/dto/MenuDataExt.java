package com.tripoin.core.dto;

import com.tripoin.core.pojo.Menu;
import com.tripoin.dto.app.MenuData;

/**
* @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
*/
public class MenuDataExt extends MenuData {

	public MenuDataExt() {}

	public MenuDataExt(Menu menu) {
		setCode(menu.getCode());
		setLevel(menu.getLevel());
		setOrder(menu.getOrder());
		setTree(menu.getTree());
		setViewType(menu.getViewType());
		setName(menu.getName());
		if(menu.getMenuParent() != null)
			setDtoMenu(new MenuDataExt(menu.getMenuParent()));
	}
	
}
