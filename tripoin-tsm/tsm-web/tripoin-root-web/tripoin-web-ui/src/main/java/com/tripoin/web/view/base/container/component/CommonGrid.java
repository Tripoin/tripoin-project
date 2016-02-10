package com.tripoin.web.view.base.container.component;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;

public class CommonGrid extends CssLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1879211785825116306L;
	protected final Grid grid = new Grid();
	protected final HorizontalLayout menuBarLayout = new HorizontalLayout();
	private MenuBar menuBarLeft = new MenuBar();
	private MenuBar menuBarRight = new MenuBar();

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

	public MenuBar getMenuBarRight() {
		return menuBarRight;
	}

	public void setMenuBarRight(MenuBar menuBarRight) {
		this.menuBarRight = menuBarRight;
	}

	public MenuBar getMenuBarLeft() {
		return menuBarLeft;
	}

	public void setMenuBarLeft(MenuBar menuBarLeft) {
		this.menuBarLeft = menuBarLeft;
	}

}
