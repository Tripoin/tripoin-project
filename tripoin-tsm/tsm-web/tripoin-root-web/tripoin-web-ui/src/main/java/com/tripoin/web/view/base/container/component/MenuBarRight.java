package com.tripoin.web.view.base.container.component;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.MenuBar;

public class MenuBarRight extends MenuBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4973675933159970845L;
	private List<MenuItem> menuItemParent;
	private ArrayList<MenuItem> menuItemChild;
	
	public MenuBarRight() {}

	public List<MenuItem> getMenuItemParent() {
		return menuItemParent;
	}

	public void setMenuItemParent(List<MenuItem> menuItemParent) {
		for(MenuItem menuItem : menuItemParent)
			this.addItem(menuItem.getText(), menuItem.getIcon(), menuItem.getCommand());
		this.menuItemParent = menuItemParent;
	}

	public ArrayList<MenuItem> getMenuItemChild() {
		return menuItemChild;
	}

	public void setMenuItemChild(ArrayList<MenuItem> menuItemChild) {
		this.menuItemChild = menuItemChild;
	}
	
}