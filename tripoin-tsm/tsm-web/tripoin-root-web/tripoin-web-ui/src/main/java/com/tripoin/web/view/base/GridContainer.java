package com.tripoin.web.view.base;

import org.springframework.beans.factory.annotation.Value;

import com.tripoin.web.view.exception.TripoinViewException;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;

public class GridContainer extends VerticalLayout implements ITripoinComponent<CommonGrid, GridContainer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8264084343906114747L;
	private CommonGrid commonGrid = new CommonGrid();
	private GridContainer gridContainer;
	private String msg;

	public GridContainer() {
		commonGrid.addStyleName("card");
		commonGrid.setWidth("100%");
		
		commonGrid.getMenuBarLayout().addStyleName("v-panel-caption");
		commonGrid.getMenuBarLayout().setWidth("100%");
		commonGrid.getMenuBarLeft().setAutoOpen(true);
		commonGrid.getMenuBarLeft().addStyleName("borderless");
		commonGrid.getMenuBarLeft().addStyleName("small");
		commonGrid.getMenuBarRight().setAutoOpen(true);
		commonGrid.getMenuBarRight().addStyleName("borderless");
		commonGrid.getMenuBarRight().addStyleName("small");

		commonGrid.getGrid().setReadOnly(true);
		commonGrid.getGrid().setEditorEnabled(false);
		commonGrid.getGrid().setHeaderVisible(true);
		commonGrid.getGrid().setSelectionMode(SelectionMode.MULTI);
		commonGrid.getGrid().setSizeUndefined();
		commonGrid.getGrid().setWidth("100%");
		commonGrid.getGrid().addStyleName("small");
		this.addComponent(commonGrid);
		this.setWidth("100%");
	}
	
	public String getMsg() {
		return msg;
	}

	@Value("${gridcontainer.getresult.error}")
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public void setParam(CommonGrid param) {
		this.commonGrid = param;
	}

	@Override
	public CommonGrid getParam() {
		return this.commonGrid;
	}

	@Override
	public void setResult(GridContainer result) {
		this.gridContainer = result;
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
