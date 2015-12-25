package com.tripoin.web.view.page.masterdata.occupation;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.util.ui.platform.IdentifierPlatform;
import com.tripoin.web.common.EWebSessionConstant;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IOccupationService;
import com.tripoin.web.servlet.VaadinView;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = DataOccupationManageView.BEAN_NAME, cached = false)
public class DataOccupationManageView extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = -4592518571070450190L;
	public static final String BEAN_NAME = "dataOccupationManageView";
	
	@Autowired
	private IOccupationService occupationService;

    private final TextField occupationName = new TextField("Occupation Name");
    private final TextField occupationDescription = new TextField("Description");
    private Button submit;
    private Button cancel;
    private Notification notification = new Notification("");
    private boolean isFailure = true;
	private OccupationData occupationData;

	@PostConstruct
	public void init() throws Exception {        
        setMargin(true);
        addStyleName("tripoin-custom-screen");
        HorizontalLayout row = new HorizontalLayout();
        addComponent(row);
        row.setMargin(false);
        row.setWidth("100%");
        final FormLayout formTitle = new FormLayout();       
        row.addComponent(formTitle);
        formTitle.setMargin(false);
        formTitle.addStyleName("light");        
        Label title = new Label("Data Occupation");
        formTitle.addComponent(title); 
        title.addStyleName("h1");

        final FormLayout form = new FormLayout();
        addComponent(form);
        form.setStyleName("tripoin-custom-form");
        form.setMargin(false);        
        Label section = new Label(" ");
        form.addComponent(section);   
        section.addStyleName("h3");
        section.addStyleName("colored");
        section.setWidth("80%");  

        form.addComponent(occupationName);
        occupationName.setRequired(true);
        occupationName.setWidth("45%");
        occupationName.addStyleName("small");
        occupationName.focus();        

        form.addComponent(occupationDescription);
        occupationDescription.setWidth("45%");
        occupationDescription.addStyleName("small");

        HorizontalLayout footer = new HorizontalLayout();
        form.addComponent(footer);
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
        cancel.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 7353418766196233887L;
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(DataOccupationView.BEAN_NAME);
			}
		});        
        if(VaadinSession.getCurrent().getSession().getAttribute(EWebSessionConstant.SESSION_OCUPATION_DATA.toString()) == null){
        	occupationData = new OccupationData();
			occupationData.setStatus(1);
        	submit.setCaption(EWebUIConstant.BUTTON_SAVE.toString());
        }else{
        	occupationData = (OccupationData)VaadinSession.getCurrent().getSession().getAttribute(EWebSessionConstant.SESSION_OCUPATION_DATA.toString());
        	VaadinSession.getCurrent().getSession().removeAttribute("occupationData");
        	occupationName.setValue(occupationData.getName());
        	occupationDescription.setValue(occupationData.getRemarks());
        	submit.setCaption(EWebUIConstant.BUTTON_UPDATE.toString());
        }
        notification.setCaption("Error Data Occupation");
		notification.setStyleName("system closable");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(10000);
    }

	public void setOccupationData(OccupationData occupationData) {
		this.occupationData = occupationData;
	}

    @Override
    public void enter(ViewChangeEvent event) {
    	
    }

	public Button getSubmit() {
		return submit;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if(occupationName.getValue() == null || "".equals(occupationName.getValue()) || occupationName.getValue().isEmpty()){
			occupationName.setComponentError(new UserError("Occupation Name not null!"));
	        notification.setDescription("Occupation Name not null!");
		}else{
			GeneralTransferObject generalTransferObject = new GeneralTransferObject();
			IdentifierPlatform identifierPlatform = new IdentifierPlatform(Page.getCurrent().getWebBrowser());
			occupationData.setName(occupationName.getValue());
			occupationData.setRemarks(occupationDescription.getValue());
			if(EWebUIConstant.BUTTON_SAVE.toString().equals(event.getButton().getCaption())){
				occupationData.setCode(occupationName.getValue().replace(" ", "").toUpperCase());
				occupationData.setCreatedIP(identifierPlatform.getIPAddress());
				occupationData.setCreatedTime(ParameterConstant.FORMAT_DEFAULT.format(new Date()));
				occupationData.setCreatedPlatform(identifierPlatform.getDevice().concat(" | ").concat(identifierPlatform.getOperatingSystem()).concat(" | ").concat(identifierPlatform.getBrowser()));
				generalTransferObject = occupationService.saveOccupation(occupationData);				
			}else{
				occupationData.setModifiedIP(identifierPlatform.getIPAddress());
				occupationData.setModifiedTime(ParameterConstant.FORMAT_DEFAULT.format(new Date()));
				occupationData.setModifiedPlatform(identifierPlatform.getDevice().concat(" | ").concat(identifierPlatform.getOperatingSystem()).concat(" | ").concat(identifierPlatform.getBrowser()));
				generalTransferObject = occupationService.updateOccupation(occupationData);
			}
			if(generalTransferObject != null){
				if("1".equals(generalTransferObject.getResponseCode()))
					notification.setDescription("Save Occupation error, please try again later!");
				else if("2".equals(generalTransferObject.getResponseCode()))
					notification.setDescription("Occupation name already exist.");
				else{
					isFailure = false;
					UI.getCurrent().getNavigator().navigateTo(DataOccupationView.BEAN_NAME);
				}
			}			
		}
		
		if(isFailure)
	        notification.show(Page.getCurrent());
	}
    
}
