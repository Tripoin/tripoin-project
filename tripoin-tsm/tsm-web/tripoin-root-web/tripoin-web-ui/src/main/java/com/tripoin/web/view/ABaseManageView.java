package com.tripoin.web.view;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class ABaseManageView extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = -570015212316455087L;
	protected FormLayout form = new FormLayout();
    protected Button submit;
    protected Button cancel;
    protected Notification notification;
    protected static final Integer NOTIFICATION_TIME = 10000;
    protected boolean isFailure = true;

	public void init(String PAGE_NAME) throws Exception {
        setMargin(true);
        addStyleName("tripoin-custom-screen");
        HorizontalLayout headerLayout = new HorizontalLayout();
        addComponent(headerLayout);
        headerLayout.setMargin(false);
        headerLayout.setWidth("100%");
        final FormLayout formTitle = new FormLayout();       
        headerLayout.addComponent(formTitle);
        formTitle.setMargin(false);
        formTitle.addStyleName("light");        
        Label title = new Label(PAGE_NAME);
        formTitle.addComponent(title); 
        title.addStyleName("h1");

        FormLayout formHeaderLayout = new FormLayout();
        addComponent(formHeaderLayout);
        formHeaderLayout.setStyleName("tripoin-custom-form");
        formHeaderLayout.setMargin(false);        
        Label section = new Label(" ");
        formHeaderLayout.addComponent(section); 
        section.addStyleName("h3");
        section.addStyleName("colored");
        section.setWidth("80%");
        
        addComponent(form);
        form.setStyleName("tripoin-custom-form");
        form.setMargin(false); 
        setFormLayoutView();
        form.addComponent(footerForm());
        initiateSessionData();
        
        if(notification == null){
        	notification = new Notification("");        
	        notification.setCaption("Error Data Occupation");
			notification.setStyleName("system closable");
	        notification.setPosition(Position.BOTTOM_CENTER);
	        notification.setDelayMsec(NOTIFICATION_TIME);
        }
    }
	
	protected HorizontalLayout footerForm(){
        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        submit = new Button();
        footer.addComponent(submit);
        submit.addStyleName("primary");
        submit.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        submit.addClickListener(this);
        cancel = new Button("Cancel");
        footer.addComponent(cancel);
        return footer;
	}

	protected abstract void setFormLayoutView();

	protected abstract void initiateSessionData();
	
}
