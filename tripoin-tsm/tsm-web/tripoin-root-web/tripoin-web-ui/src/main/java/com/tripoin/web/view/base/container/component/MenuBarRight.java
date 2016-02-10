package com.tripoin.web.view.base.container.component;

import java.util.ArrayList;

import com.vaadin.ui.MenuBar;

public class MenuBarRight extends MenuBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4973675933159970845L;
	private ArrayList<MenuItem> menuItem;
	
	public MenuBarRight() {}

	public ArrayList<MenuItem> getMenuItem() {
		return menuItem;
	}
	
	public void setMenuItem(ArrayList<MenuItem> menuItem) {
		this.menuItem = menuItem;
	}
	
}
