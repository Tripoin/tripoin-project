package com.tripoin.web.view.base;

import com.tripoin.web.view.base.container.GridContainer;
import com.tripoin.web.view.base.container.SearchContainer;
import com.tripoin.web.view.base.container.TitleContainer;

public class CommonComponent {
	
	protected TitleContainer titleContainer;
	protected SearchContainer searchContainer;
	protected GridContainer gridContainer;
	protected TripoinNotification tripoinNotification;

	public TitleContainer getTitleContainer() {
		return titleContainer;
	}

	public void setTitleContainer(TitleContainer titleContainer) {
		this.titleContainer = titleContainer;
	}

	public SearchContainer getSearchContainer() {
		return searchContainer;
	}

	public void setSearchContainer(SearchContainer searchContainer) {
		this.searchContainer = searchContainer;
	}

	public GridContainer getGridContainer() {
		return gridContainer;
	}

	public void setGridContainer(GridContainer gridContainer) {
		this.gridContainer = gridContainer;
	}

	public TripoinNotification getTripoinNotification() {
		return tripoinNotification;
	}

	public void setTripoinNotification(TripoinNotification tripoinNotification) {
		this.tripoinNotification = tripoinNotification;
	}

}
