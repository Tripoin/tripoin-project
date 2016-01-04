package com.tripoin.web.view.page.masterdata.employee;

import java.text.ParseException;
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
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
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

	private Label section = new Label("Personal Info");
    private final TextField employeeNameTextField = new TextField("Name");
    private final TextField nikTextField = new TextField("NIK");
    private final ComboBox occupationComboBox = new ComboBox("Occupation");
    private final ComboBox parentEmployeeComboBox = new ComboBox("Head");
    private final TextField usernameTextField = new TextField("Username");
    private final TextField birthPlaceTextField = new TextField();
    private final DateField birthDateDateField = new DateField();
    private final OptionGroup genderOptionGroup = new OptionGroup("Gender");
    private final TextField phoneTextField = new TextField("Mobile Phone");
    private final TextField telpTextField = new TextField("Home Phone");
    private final TextField emailTextField = new TextField("Email");
    private final TextArea addressTextArea = new TextArea("Address");
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
        form.addComponent(section);
        section.addStyleName("h3");
        section.addStyleName("colored");

        form.addComponent(employeeNameTextField);
        employeeNameTextField.setRequired(true);
        employeeNameTextField.setWidth("50%");
        employeeNameTextField.addStyleName("small");
        employeeNameTextField.focus();

        form.addComponent(nikTextField);
        nikTextField.setWidth("50%");
        nikTextField.setRequired(true);
        
        form.addComponent(occupationComboBox);
        occupationComboBox.setContainerDataSource(dataLoadStarted.getOccupationContainer());
        occupationComboBox.setItemCaptionMode(ItemCaptionMode.ITEM);
        occupationComboBox.addStyleName("small");
        occupationComboBox.setTextInputAllowed(false);
        occupationComboBox.setNullSelectionAllowed(false);
        occupationComboBox.setRequired(true);
        occupationComboBox.setWidth("50%");
        
        form.addComponent(parentEmployeeComboBox);
        parentEmployeeComboBox.setContainerDataSource(dataLoadStarted.getOccupationContainer());
        parentEmployeeComboBox.setItemCaptionMode(ItemCaptionMode.ITEM);
        parentEmployeeComboBox.addStyleName("small");
        parentEmployeeComboBox.setNullSelectionAllowed(false);
        parentEmployeeComboBox.setRequired(true);
        parentEmployeeComboBox.setWidth("50%");

        form.addComponent(usernameTextField);
        usernameTextField.setWidth("50%");
        usernameTextField.setRequired(true);

        HorizontalLayout placeDateOfBirth = new HorizontalLayout();
        form.addComponent(placeDateOfBirth);
        placeDateOfBirth.setCaption("Place, Date of Birth");
        birthPlaceTextField.setWidth("45%");
        birthPlaceTextField.setRequired(true);
        birthDateDateField.setRequired(true);
        placeDateOfBirth.addComponent(birthPlaceTextField);
        placeDateOfBirth.addComponent(birthDateDateField);

        form.addComponent(genderOptionGroup);
        genderOptionGroup.addItem(ParameterConstant.FEMALE);
        genderOptionGroup.addItem(ParameterConstant.MALE);
        genderOptionGroup.addStyleName("horizontal");
        genderOptionGroup.setRequired(true);

        section = new Label("Contact Info");
        form.addComponent(section);
        section.addStyleName("h3");
        section.addStyleName("colored");

        form.addComponent(phoneTextField);
        phoneTextField.setWidth("50%");
        phoneTextField.setRequired(true);

        form.addComponent(telpTextField);
        telpTextField.setWidth("50%");

        form.addComponent(emailTextField);
        emailTextField.setWidth("50%");
        emailTextField.setRequired(true);

        form.addComponent(addressTextArea);
        addressTextArea.setWidth("50%");
        addressTextArea.setRequired(true);
	}

	protected void initiateSessionData(){       
        if(VaadinSession.getCurrent().getSession().getAttribute(EWebSessionConstant.SESSION_EMPLOYEE_DATA.toString()) == null){
        	employeeData = new EmployeeData();
			employeeData.setStatus(1);
			occupationComboBox.setInputPrompt("Select Occupation");
			parentEmployeeComboBox.setInputPrompt("Select Head");
        	submit.setCaption(EWebUIConstant.BUTTON_SAVE.toString());
        }else{
        	employeeData = (EmployeeData)VaadinSession.getCurrent().getSession().getAttribute(EWebSessionConstant.SESSION_EMPLOYEE_DATA.toString());
        	VaadinSession.getCurrent().getSession().removeAttribute(EWebSessionConstant.SESSION_EMPLOYEE_DATA.toString());
        	employeeNameTextField.setValue(employeeData.getProfileData().getName());
        	nikTextField.setValue(employeeData.getNik());
        	occupationComboBox.select(employeeData.getOccupationData());        	
        	//TODO parentEmployeeComboBox.select(employeeData.getEmployeeDataParent().getProfileData().getName());
        	usernameTextField.setValue(employeeData.getProfileData().getUserData().getUsername());
        	birthPlaceTextField.setValue(employeeData.getProfileData().getBirthplace());
        	try {
				birthDateDateField.setValue(ParameterConstant.FORMAT_DEFAULT.parse(employeeData.getProfileData().getBirthdate()));
			} catch (ReadOnlyException | ConversionException | ParseException e) {
				birthDateDateField.setValue(new Date());
			}
        	if(ParameterConstant.MALE.equalsIgnoreCase(employeeData.getProfileData().getGender()))
            	genderOptionGroup.select(ParameterConstant.MALE);
            else
            	genderOptionGroup.select(ParameterConstant.FEMALE);
        	phoneTextField.setValue(employeeData.getProfileData().getPhone());
        	telpTextField.setValue(employeeData.getProfileData().getTelp());
        	emailTextField.setValue(employeeData.getProfileData().getEmail());
        	addressTextArea.setValue(employeeData.getProfileData().getAddress());
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
