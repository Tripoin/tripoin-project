package com.tripoin.web.view.base;

import com.tripoin.web.view.base.container.GridContainer;
import com.tripoin.web.view.base.container.AFormContainer;
import com.tripoin.web.view.base.container.ASearchContainer;
import com.tripoin.web.view.base.container.TitleContainer;

public class CommonComponent {
	
	protected TitleContainer titleContainer;
	protected ASearchContainer searchContainer;
	protected GridContainer gridContainer;
	protected AFormContainer formContainer;

	public TitleContainer getTitleContainer() {
		return titleContainer;
	}

	public void setTitleContainer(TitleContainer titleContainer) {
		this.titleContainer = titleContainer;
	}

	public ASearchContainer getSearchContainer() {
		return searchContainer;
	}

	public void setSearchContainer(ASearchContainer searchContainer) {
		this.searchContainer = searchContainer;
	}

	public GridContainer getGridContainer() {
		return gridContainer;
	}

	public void setGridContainer(GridContainer gridContainer) {
		this.gridContainer = gridContainer;
	}

	public AFormContainer getFormContainer() {
		return formContainer;
	}

	public void setFormContainer(AFormContainer formContainer) {
		this.formContainer = formContainer;
	}

}
