package com.tripoin.web.view.base.container.component;

import java.util.Map;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class FormPanel {

	@SuppressWarnings("rawtypes")
	protected Map<String, AbstractField> formPanelComponents;
	protected final HorizontalLayout footerSearch = new HorizontalLayout();
	protected final Button okButton = new Button();
	protected final Button cancelButton = new Button();

	public FormPanel() {
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
	public Map<String, AbstractField> getFormPanelComponents() {
		return formPanelComponents;
	}

	@SuppressWarnings("rawtypes")
	public void setFormPanelComponents(Map<String, AbstractField> formPanelComponents) {
		this.formPanelComponents = formPanelComponents;
	}

}
