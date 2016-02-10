package com.tripoin.web.view.base.container.component;

import java.util.ArrayList;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

public class SearchPanel {

	protected ArrayList<Component> searcPanelComponents;
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

	public ArrayList<Component> getSearcPanelComponents() {
		return searcPanelComponents;
	}

	public void setSearcPanelComponents(ArrayList<Component> searcPanelComponents) {
		this.searcPanelComponents = searcPanelComponents;
	}

}
