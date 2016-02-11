package com.tripoin.web.view.base;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

public abstract class ATripoinNotification extends Notification {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7036584635068630755L;
	private String caption;
	private String description;

	public ATripoinNotification(String caption, String description) {
		super(caption, description);
		this.caption = caption;
		this.description = description;
		setStyleName("system closable");
		setPosition(Position.BOTTOM_CENTER);
		setDelayMsec(delayMiliSecond());
	}
	
	public void show(String caption, String description) {
		this.setCaption(this.caption);
		this.setDescription(this.description);
		this.show(Page.getCurrent());
	}
	
	protected abstract int delayMiliSecond();

}
