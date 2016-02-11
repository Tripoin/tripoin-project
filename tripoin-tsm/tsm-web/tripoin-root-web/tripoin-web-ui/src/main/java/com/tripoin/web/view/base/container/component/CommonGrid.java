package com.tripoin.web.view.base.container.component;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;

public class CommonGrid extends CssLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1879211785825116306L;
	protected final Grid grid = new Grid();
	protected final HorizontalLayout menuBarLayout = new HorizontalLayout();
	private MenuBarLeft menuBarLeft = new MenuBarLeft();
	private MenuBarRight menuBarRight = new MenuBarRight();

	public CommonGrid() {
		this.addComponent(this.getMenuBarLayout());
		this.getMenuBarLayout().addComponent(this.menuBarLeft);
		this.getMenuBarLayout().setExpandRatio(this.menuBarLeft, 1);
		this.getMenuBarLayout().addComponent(this.menuBarRight);
		this.addComponent(this.getGrid());
	}

	public Grid getGrid() {
		return grid;
	}

	public HorizontalLayout getMenuBarLayout() {
		return menuBarLayout;
	}

	public MenuBarRight getMenuBarRight() {
		return menuBarRight;
	}

	public void setMenuBarRight(MenuBarRight menuBarRight) {
		this.menuBarRight = menuBarRight;
	}

	public MenuBarLeft getMenuBarLeft() {
		return menuBarLeft;
	}

	public void setMenuBarLeft(MenuBarLeft menuBarLeft) {
		this.menuBarLeft = menuBarLeft;
	}

}
