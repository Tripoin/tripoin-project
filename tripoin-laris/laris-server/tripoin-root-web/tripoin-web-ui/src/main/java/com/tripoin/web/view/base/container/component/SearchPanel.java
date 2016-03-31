package com.tripoin.web.view.base.container.component;

import java.util.Map;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class SearchPanel {

	@SuppressWarnings("rawtypes")
	protected Map<String, AbstractField> searchPanelComponents;
	protected final HorizontalLayout footerSearch = new HorizontalLayout();
	protected final Button okButton = new Button();
	protected final Button cancelButton = new Button();

	public SearchPanel() {
		this.getFooterSearch().addComponent(this.okButton);
		this.getFooterSearch().addComponent(this.cancelButton);
	}
	
	public HorizontalLayout getFooterSearch() {
		return footerSearch;
	}

	public Button getOkButton() {
		return okButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	@SuppressWarnings("rawtypes")
	public Map<String, AbstractField> getSearchPanelComponents() {
		return searchPanelComponents;
	}

	@SuppressWarnings("rawtypes")
	public void setSearchPanelComponents(Map<String, AbstractField> searchPanelComponents) {
		this.searchPanelComponents = searchPanelComponents;
	}

}
