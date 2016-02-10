package com.tripoin.web.view.base;

import java.util.ArrayList;

import com.vaadin.ui.MenuBar;

public class MenuBarLeft extends MenuBar {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869134213758047890L;
	private ArrayList<MenuItem> menuItem;
	
	public MenuBarLeft() {}
	
	public ArrayList<MenuItem> getMenuItem() {
		return menuItem;
	}
	
	public void setMenuItem(ArrayList<MenuItem> menuItem) {
		this.menuItem = menuItem;
	}
	
}
