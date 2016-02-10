package com.tripoin.web.view.base;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

public class TripoinNotification extends Notification {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7036584635068630755L;
	private String caption;
	private String description;
	private int delayMsec;

	public TripoinNotification(String caption, String description) {
		super(caption, description);
		this.caption = caption;
		this.description = description;
	}
	
	public void show(String caption, String description) {
		this.setCaption(this.caption);
		this.setDescription(this.description);
		this.show(Page.getCurrent());
	}
	
	public String getCaption() {
		return caption;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
		this.setCaption(this.caption);
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
		this.setDescription(this.description);
	}

	public int getDelayMsec() {
		return delayMsec;
	}

	public void setDelayMsec(int delayMsec) {
		this.delayMsec = delayMsec;
		this.setDelayMsec(delayMsec);
	}

}
