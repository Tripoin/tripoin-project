package com.tripoin.web.view.base.container;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

public abstract class ATripoinNotification {

	private Notification notification;
	private String caption;
	private String description;

	public ATripoinNotification() {
		notification = new Notification("");
		init();
	}

	public ATripoinNotification(String caption) {
		notification = new Notification(caption);
		this.caption = caption;
		init();
	}

	public ATripoinNotification(String caption, String description) {
		notification = new Notification(caption, description);
		this.caption = caption;
		this.description = description;
		init();
	}
	
	private void init(){
		notification.setStyleName("system closable");
		notification.setPosition(Position.BOTTOM_CENTER);
		notification.setDelayMsec(delayMiliSecond());
	}
	
	public void show(String caption, String description) {
		this.caption = caption;
		this.description = description;
		notification.setCaption(this.caption);
		notification.setDescription(this.description);
		notification.show(Page.getCurrent());
	}
	
	protected abstract int delayMiliSecond();

}
