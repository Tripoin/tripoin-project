package com.tripoin.web.view.base;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TitleContainer extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 64090433787269011L;
	private Label title;
	
	public TitleContainer() {}
	
	public TitleContainer(Label title) {
		title.addStyleName("h1");
		addComponent(title);
	}

	public Label getTitle() {
		return title;
	}

	public void setTitle(Label title) {
		this.title = title;
	}

}
