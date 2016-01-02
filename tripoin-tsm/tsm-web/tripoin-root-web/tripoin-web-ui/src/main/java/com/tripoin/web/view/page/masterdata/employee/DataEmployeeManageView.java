package com.tripoin.web.view.page.masterdata.employee;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.EmployeeData;
import com.tripoin.util.ui.platform.IdentifierPlatform;
import com.tripoin.web.common.EWebSessionConstant;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IDataLoadStarted;
import com.tripoin.web.service.IEmployeeService;
import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.ABaseManageView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = DataEmployeeManageView.BEAN_NAME, cached = false)
public class DataEmployeeManageView extends ABaseManageView {

	private static final long serialVersionUID = -4592518571070450190L;
	public static final String BEAN_NAME = "dataEmployeeManageView";
	public static final String PAGE_NAME = "Data Employee";
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IDataLoadStarted dataLoadStarted;	

    private final TextField employeeNameTextField = new TextField("Name");
    private ComboBox occupationComboBox;
	private EmployeeData employeeData;

	@PostConstruct
	public void init() throws Exception {        
        super.init(PAGE_NAME); 

        cancel.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 7353418766196233887L;
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(DataEmployeeView.BEAN_NAME);
			}
		}); 
    }
	
	protected void setFormLayoutView(){
        form.addComponent(employeeNameTextField);
        employeeNameTextField.setRequired(true);
        employeeNameTextField.setWidth("45%");
        employeeNameTextField.addStyleName("small");
        employeeNameTextField.focus();
        occupationComboBox = new ComboBox("Occupation");
        form.addComponent(occupationComboBox);
        occupationComboBox.setContainerDataSource(dataLoadStarted.getOccupationContainer());
        occupationComboBox.setItemCaptionMode(ItemCaptionMode.ITEM);
        occupationComboBox.addStyleName("small");
        occupationComboBox.setTextInputAllowed(false);
        occupationComboBox.setNullSelectionAllowed(false);
        occupationComboBox.setWidth("45%");
	}

	protected void initiateSessionData(){       
        if(VaadinSession.getCurrent().getSession().getAttribute(EWebSessionConstant.SESSION_EMPLOYEE_DATA.toString()) == null){
        	employeeData = new EmployeeData();
			employeeData.setStatus(1);
        	submit.setCaption(EWebUIConstant.BUTTON_SAVE.toString());
        }else{
        	employeeData = (EmployeeData)VaadinSession.getCurrent().getSession().getAttribute(EWebSessionConstant.SESSION_EMPLOYEE_DATA.toString());
        	VaadinSession.getCurrent().getSession().removeAttribute(EWebSessionConstant.SESSION_EMPLOYEE_DATA.toString());
        	employeeNameTextField.setValue(employeeData.getProfileData().getName());
        	occupationComboBox.setNullSelectionItemId(employeeData.getOccupationData());
        	occupationComboBox.setValue(employeeData.getOccupationData());
        	submit.setCaption(EWebUIConstant.BUTTON_UPDATE.toString());
        }	
	}
	
	public Button getSubmit() {
		return submit;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if(employeeNameTextField.getValue() == null || "".equals(employeeNameTextField.getValue()) || employeeNameTextField.getValue().isEmpty()){
			employeeNameTextField.setComponentError(new UserError("Occupation Name not null!"));
	        notification.setDescription("Occupation Name not null!");
		}else{
			GeneralTransferObject generalTransferObject = new GeneralTransferObject();
			IdentifierPlatform identifierPlatform = new IdentifierPlatform(Page.getCurrent().getWebBrowser());
			/*employeeData.setName(employeeNameTextField.getValue());*/
			if(EWebUIConstant.BUTTON_SAVE.toString().equals(event.getButton().getCaption())){
				employeeData.setCode(employeeNameTextField.getValue().replace(" ", "").toUpperCase());
				employeeData.setCreatedIP(identifierPlatform.getIPAddress());
				employeeData.setCreatedTime(ParameterConstant.FORMAT_DEFAULT.format(new Date()));
				employeeData.setCreatedPlatform(identifierPlatform.getDevice().concat(" | ").concat(identifierPlatform.getOperatingSystem()).concat(" | ").concat(identifierPlatform.getBrowser()));
				generalTransferObject = employeeService.saveEmployee(employeeData);				
			}else{
				employeeData.setModifiedIP(identifierPlatform.getIPAddress());
				employeeData.setModifiedTime(ParameterConstant.FORMAT_DEFAULT.format(new Date()));
				employeeData.setModifiedPlatform(identifierPlatform.getDevice().concat(" | ").concat(identifierPlatform.getOperatingSystem()).concat(" | ").concat(identifierPlatform.getBrowser()));
				generalTransferObject = employeeService.updateEmployee(employeeData);
			}
			employeeData = null;
			if(generalTransferObject != null){
				if("1".equals(generalTransferObject.getResponseCode()))
					notification.setDescription("Save Occupation error, please try again later!");
				else if("2".equals(generalTransferObject.getResponseCode()))
					notification.setDescription("Occupation name already exist.");
				else{
					isFailure = false;
					UI.getCurrent().getNavigator().navigateTo(DataEmployeeView.BEAN_NAME);
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
