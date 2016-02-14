package com.tripoin.web.view.page.masterdata;

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
import com.tripoin.web.service.IOccupationService;
import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.ABaseManageView;
import com.tripoin.web.view.base.ITripoinConstantComponent;
import com.tripoin.web.view.page.masterdata.occupation.DataOccupationView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
* @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
*/
@Component
@Scope("prototype")
@VaadinView(value = ExamplePage.BEAN_NAME, cached = false)
public class ExamplePage extends ABaseManageView {

	private static final long serialVersionUID = -4592518571070450190L;
	public static final String BEAN_NAME = "examplePage";
	public static final String PAGE_NAME = "Data Occupation";
	
	@Autowired
	private IOccupationService occupationService;

    private final TextField occupationName = new TextField("Occupation Name");
    private final TextField occupationDescription = new TextField("Description");
	private OccupationData occupationData;

	@PostConstruct
	public void init() throws Exception {        
        super.init(PAGE_NAME); 

        cancel.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 7353418766196233887L;
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(DataOccupationView.BEAN_NAME);
			}
		}); 
    }
	
	protected void setFormLayoutView(){
        form.addComponent(occupationName);
        occupationName.setRequired(true);
        occupationName.setWidth("50%");
        occupationName.addStyleName("small");
        occupationName.focus();
        form.addComponent(occupationDescription);
        occupationDescription.setWidth("50%");
        occupationDescription.addStyleName("small");		
	}

	protected void initiateSessionData(){       
        if(VaadinSession.getCurrent().getSession().getAttribute(EWebSessionConstant.SESSION_GRID_DATA.toString()) == null){
        	occupationData = new OccupationData();
			occupationData.setStatus(1);
        	submit.setCaption(ITripoinConstantComponent.Button.SAVE);
        }else{
        	occupationData = (OccupationData)VaadinSession.getCurrent().getSession().getAttribute(EWebSessionConstant.SESSION_GRID_DATA.toString());
        	VaadinSession.getCurrent().getSession().removeAttribute(EWebSessionConstant.SESSION_GRID_DATA.toString());
        	occupationName.setValue(occupationData.getName());
        	occupationDescription.setValue(occupationData.getRemarks());
        	submit.setCaption(ITripoinConstantComponent.Button.UPDATE);
        }	
	}
	
	public Button getSubmit() {
		return submit;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if(occupationName.getValue() == null || occupationName.getValue().isEmpty()){
			occupationName.setComponentError(new UserError("Occupation Name not null!"));
	        notification.setDescription("Occupation Name not null!");
		}else{
			GeneralTransferObject generalTransferObject = new GeneralTransferObject();
			IdentifierPlatform identifierPlatform = new IdentifierPlatform(Page.getCurrent().getWebBrowser());
			occupationData.setName(occupationName.getValue());
			occupationData.setRemarks(occupationDescription.getValue());
			if(ITripoinConstantComponent.Button.SAVE.equals(event.getButton().getCaption())){
				occupationData.setCode(occupationName.getValue().replace(" ", "").toUpperCase());
				occupationData.setCreatedIP(identifierPlatform.getIPAddress());
				occupationData.setCreatedTime(ParameterConstant.FORMAT_DEFAULT.format(new Date()));
				occupationData.setCreatedPlatform(identifierPlatform.getDevice().concat(" | ").concat(identifierPlatform.getOperatingSystem()).concat(" | ").concat(identifierPlatform.getBrowser()));
				generalTransferObject = occupationService.saveOccupation(occupationData, VaadinServlet.getCurrent().getServletContext());				
			}else{
				occupationData.setModifiedIP(identifierPlatform.getIPAddress());
				occupationData.setModifiedTime(ParameterConstant.FORMAT_DEFAULT.format(new Date()));
				occupationData.setModifiedPlatform(identifierPlatform.getDevice().concat(" | ").concat(identifierPlatform.getOperatingSystem()).concat(" | ").concat(identifierPlatform.getBrowser()));
				generalTransferObject = occupationService.updateOccupation(occupationData, VaadinServlet.getCurrent().getServletContext());
			}
			occupationData = null;
			if(generalTransferObject != null){
				if("1".equals(generalTransferObject.getResponseCode()))
					notification.setDescription("Occupation error, please try again later!");
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

    @Override
    public void enter(ViewChangeEvent event) {
    	
    }
    
}
