package com.tripoin.web.view.base;

import java.util.ArrayList;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class SearchPanel extends FormLayout{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8935628837403559152L;
	protected FormLayout verticalLayout = new FormLayout();
	protected HorizontalLayout horizontalLayout=new HorizontalLayout();
	protected ArrayList<Component> searcPanelComponents;
	protected Button okButton;
	protected Button cancelButton;
	
	public SearchPanel() {
		this.horizontalLayout.setSpacing(true);
		this.addComponent(verticalLayout);
		this.addComponent(horizontalLayout);
	}
	

	public Button getOkButton() {
		return okButton;
	}
	public void setOkButton(Button okButton) {
		this.okButton = okButton;
		okButton.addStyleName("primary");
		okButton.addStyleName("small");
		okButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		this.horizontalLayout.addComponent(getOkButton());
	}
	public Button getCancelButton() {
		return cancelButton;
	}
	public void setCancelButton(Button cancelButton) {
		this.cancelButton = cancelButton;
		cancelButton.addStyleName("small");
		cancelButton.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
		this.horizontalLayout.addComponent(cancelButton);
	}

	public ArrayList<Component> getSearcPanelComponents() {
		return searcPanelComponents;
	}

	public void setSearcPanelComponents(ArrayList<Component> searcPanelComponents) {
		this.searcPanelComponents = searcPanelComponents;
	}
	
	
	
	
}
