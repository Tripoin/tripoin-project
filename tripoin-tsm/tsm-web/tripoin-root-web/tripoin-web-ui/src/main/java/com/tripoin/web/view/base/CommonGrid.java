package com.tripoin.web.view.base;

import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

public class CommonGrid extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1879211785825116306L;
	private static final Integer NOTIFICATION_TIME = 10000;
	private Integer positionPage;
	private Integer totalPage;
	protected Grid grid;
	protected MenuBarLeft menuBarLeft;
	protected MenuBarRight menuBarRight;
	
	public CommonGrid() {
	}

	public Grid getGrid() {
		return grid;
		
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
		this.addComponent(this.getGrid());
	}

	public Integer getPositionPage() {
		return positionPage;
	}

	public void setPositionPage(Integer positionPage) {
		this.positionPage = positionPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public static Integer getNotificationTime() {
		return NOTIFICATION_TIME;
	}

	public MenuBarLeft getMenuGrid() {
		return menuBarLeft;
		
	}

	public void setMenuGrid(MenuBarLeft menuGrid) {
		this.menuBarLeft = menuGrid;
		this.addComponent(this.getMenuGrid());
	}

	public MenuBarLeft getMenuBarLeft() {
		return menuBarLeft;
	}

	public void setMenuBarLeft(MenuBarLeft menuBarLeft) {
		this.menuBarLeft = menuBarLeft;
	}

	public MenuBarRight getMenuBarRight() {
		return menuBarRight;
		
	}

	public void setMenuBarRight(MenuBarRight menuBarRight) {
		this.menuBarRight = menuBarRight;
		
	}	
	

}
