package com.tripoin.web.view.base;

import java.util.ArrayList;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

public class MenuBarLeft extends MenuBar{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4504987814595475874L;
	ArrayList<MenuItem> menuItem;
	
	public MenuBarLeft() {
	}
	
	public ArrayList<MenuItem> getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(ArrayList<MenuItem> menuItem) {
		this.menuItem = menuItem;
	}
	
	
}
