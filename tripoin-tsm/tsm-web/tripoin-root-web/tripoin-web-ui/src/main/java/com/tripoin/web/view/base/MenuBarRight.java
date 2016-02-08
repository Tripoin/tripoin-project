package com.tripoin.web.view.base;

import java.util.ArrayList;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

public class MenuBarRight extends MenuBar {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7220561043957618127L;
	ArrayList<MenuItem> menuItem;
	
	public MenuBarRight() {
	}
	
	public ArrayList<MenuItem> getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(ArrayList<MenuItem> menuItem) {
		this.menuItem = menuItem;
	}
	
	
}
