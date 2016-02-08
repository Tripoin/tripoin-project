package com.tripoin.web.view.base;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.View;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
/**
 * 
 * @author Fadhil Paramanindo
 *
 * @param <HEADER>
 */
public abstract class ATripoinPage extends VerticalLayout implements View,
		ClickListener, ITripoinPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -195915325891958375L;
	protected Logger logger = LoggerFactory.getLogger(getViewClass());
	private static final int NOTIFICATION_TIME = 7000;

	protected abstract Class<? extends ATripoinPage> getViewClass();

	protected boolean isFailure = true;

	protected CommonComponent commonComponent;
	protected GridContainer gridContainer;
	protected SearchContainer searchContainer;
	protected TitleContainer titleContainer;
	protected TripoinNotification notification;
	protected TripoinPageable tripoinPageable;
	

	public void initComponent() {
		commonComponent = new CommonComponent();
		initTitle();
		initSearch();
		initGrid();
		initNotification();
		constructBeanContainer();
		addComponent(commonComponent.mainLayout);
		// commonComponent.mainLayout.addComponent((Component) notification); //TODO: fixing notif
		addComponent(commonComponent.headerLayout);
		commonComponent.headerLayout.setMargin(false);
		commonComponent.headerLayout.setWidth("100%");

//		commonComponent.headerLayout.addComponent(commonComponent
//				.getTitleContainer());

		

		// setItemMenuGrid();

		/*
		 * tripoinPageable = new TripoinPageable(
		 * commonComponent.getGridContainer().menuBarRight,
		 * tripoinPageable.getTotalPage(), tripoinPageable.getPositionPage()) {
		 * 
		 * @Override protected void constructInternalDataContainer() {
		 * constructBeanContainer(); } };
		 * 
		 * tripoinPageable.getPaging();
		 */

		setLayoutGrid();
		
		addComponent(commonComponent.mainLayout);

	}

	public void initEvent() {

	}

	private void initGrid() {
		gridContainer = new GridContainer() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 3403933495303729366L;

			@Override
			ArrayList<MenuItem> getMenuRightItem() {

				return getRightMenuItems();
			}

			@Override
			ArrayList<MenuItem> getMenuLeftItem() {
				return getLeftMenuItems();
			}

			
		};
		commonComponent.setGridContainer(gridContainer);
		gridContainer.addComponent(new Label("ini label grid"));
		commonComponent.mainLayout.addComponent(gridContainer);
		Grid grid=new Grid();
//		grid.setColumnOrder(setGridHeader());
		grid.setReadOnly(true);
        grid.setEditorEnabled(false);
        grid.setHeaderVisible(true);
        //grid.setFrozenColumnCount(2);
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.setSizeUndefined();
        grid.setWidth("100%");
        grid.addStyleName("small");
        
		CommonGrid commonGrid = new CommonGrid();
		commonGrid.setGrid(grid);
		commonGrid.addComponent(grid);
		
		commonGrid.setPositionPage(null);
		commonGrid.setTotalPage(null);
		gridContainer.setParam(commonGrid);
		gridContainer.addComponent(commonGrid);
		
		gridContainer.verticalLayout.addComponent((Component) gridContainer
				.getParam());
		commonComponent.getGridContainer().getParam().setWidth("100%");
	}



	private void initSearch() {
		searchContainer = new SearchContainer();
		SearchPanel searchPanel = new SearchPanel();
		searchPanel.setStyleName("tripoin-custom-form");
		searchPanel.setMargin(new MarginInfo(false, false, true, false));
		searchPanel.setSpacing(true);
		searchPanel.setWidth("100%");
		searchPanel.setOkButton(new Button(getOkButtonCaption()));
		searchPanel.setCancelButton(new Button(getCancelButtonCaption()));
		commonComponent.setSearchContainer(searchContainer);
		commonComponent.mainLayout.addComponent(searchContainer);
		if (getSearchPanelComponents() != null) {
			searchPanel.setSearcPanelComponents(getSearchPanelComponents());
			for (Component component: searchPanel.getSearcPanelComponents()) {
				component.addStyleName("small");
				component.setWidth("50%");
				searchPanel.verticalLayout.addComponent(component);
			}
		}
		
		searchContainer.setParam(searchPanel);
		
		commonComponent.getSearchContainer()
				.setStyleName("tripoin-custom-form");
		commonComponent.getSearchContainer().getParam()
				.setMargin(new MarginInfo(false, false, true, false));
		commonComponent.getSearchContainer().getParam().setSpacing(true);
		commonComponent.getSearchContainer().setWidth("100%");
	}

	private void initNotification() {
		notification = new TripoinNotification(getCaptionNotification(),
				getDescriptionNotification());
		notification.setStyleName("system closable");
		notification.setPosition(Position.BOTTOM_CENTER);
		notification.setDelayMsec(NOTIFICATION_TIME);

	}

	void initTitle() {
		titleContainer = new TitleContainer();
		Label title = new Label(getPageTitle());
		title.addStyleName("h1");
		titleContainer.setTitle(title);
		commonComponent.setTitleContainer(titleContainer);
		commonComponent.getTitleContainer().setMargin(false);
		commonComponent.getTitleContainer().addStyleName("light");
		commonComponent.mainLayout.addComponent(titleContainer);

	}
	

	
	protected abstract ArrayList<Component> getSearchPanelComponents();
	
	protected abstract String getPageTitle();

	protected abstract String getCaptionNotification();

	protected abstract String getDescriptionNotification();

	protected abstract ArrayList<MenuItem> getRightMenuItems();

	protected abstract ArrayList<MenuItem> getLeftMenuItems();

	protected abstract void constructBeanContainer();

	protected abstract void setLayoutGrid();
	
	protected abstract Object[] setGridHeader();
	
	protected  String getOkButtonCaption(){
		return ITripoinConstantComponent.Button.SEARCH;
	}
	
	protected  String getCancelButtonCaption(){
		return ITripoinConstantComponent.Button.CANCEL;
	}
	


}
