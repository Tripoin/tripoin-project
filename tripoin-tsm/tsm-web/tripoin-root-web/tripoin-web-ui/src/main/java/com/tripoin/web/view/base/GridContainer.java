package com.tripoin.web.view.base;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import com.tripoin.web.view.exception.TripoinViewException;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

public abstract class GridContainer  extends  VerticalLayout implements
		ITripoinComponent<CommonGrid, GridContainer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8264084343906114747L;
	protected VerticalLayout verticalLayout = new VerticalLayout();
	protected HorizontalLayout gridMenuLayout = new HorizontalLayout();
	protected MenuBarLeft menuBarLeft;
	protected MenuBarRight menuBarRight;
	private CommonGrid commonGrid;
	private GridContainer gridContainer;
	private String msg;

	@PostConstruct
	void init() {
		menuBarLeft = new MenuBarLeft();
		menuBarLeft.setMenuItem(getMenuLeftItem());
		menuBarLeft.setAutoOpen(true);
		menuBarLeft.addStyleName("borderless");
		menuBarLeft.addStyleName("small");

		menuBarRight = new MenuBarRight();
		menuBarRight.setMenuItem(getMenuRightItem());
		menuBarRight.addStyleName("borderless");
		menuBarRight.addStyleName("small");
		menuBarRight.setAutoOpen(true);
		commonGrid.setMenuBarLeft(menuBarLeft);
		
		commonGrid.setMenuBarRight(menuBarRight);
		
		this.commonGrid.getGrid().setReadOnly(true);
		this.commonGrid.getGrid().setEditorEnabled(false);
		this.commonGrid.getGrid().setHeaderVisible(true);
		this.commonGrid.getGrid().setFrozenColumnCount(2);
		this.commonGrid.getGrid().setSelectionMode(SelectionMode.MULTI);
		this.commonGrid.getGrid().setSizeUndefined();
		this.commonGrid.getGrid().setWidth("100%");
		this.commonGrid.getGrid().addStyleName("small");
		this.gridMenuLayout.addComponent((Component) commonGrid
				.getMenuBarLeft());
		this.gridMenuLayout.addComponent((Component) commonGrid
				.getMenuBarRight());
		this.gridMenuLayout.addStyleName("v-panel-caption");
		this.gridMenuLayout.setWidth("100%");
		this.verticalLayout.addComponent(gridMenuLayout);
		this.verticalLayout.addComponent(commonGrid);
		this.gridMenuLayout.setExpandRatio(menuBarLeft, 1);

	}

	abstract ArrayList<MenuItem> getMenuLeftItem();

	abstract ArrayList<MenuItem> getMenuRightItem();

	public String getMsg() {
		return msg;
	}

	@Value("${gridcontainer.getresult.error}")
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public void setParam(CommonGrid p_param) {
		this.commonGrid = p_param;
		this.verticalLayout.addComponent(commonGrid);
	}

	@Override
	public CommonGrid getParam() {
		return this.commonGrid;
	}

	@Override
	public void setResult(GridContainer p_result) {
		this.gridContainer = p_result;
	}

	@Override
	public GridContainer getResult() throws Exception {
		if (this.gridContainer != null) {
			return gridContainer;
		} else {
			throw new TripoinViewException(msg);
		}
	}
	
}
