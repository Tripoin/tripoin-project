package com.tripoin.web.view.base;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;

public class TitleContainer extends FormLayout {

	private Label title;

	/**
	 * 
	 */
	private static final long serialVersionUID = 64090433787269011L;

	public Label getTitle() {
		return title;
	}

	public void setTitle(Label title) {
		this.title = title;
		addComponent(title);
	}

}
