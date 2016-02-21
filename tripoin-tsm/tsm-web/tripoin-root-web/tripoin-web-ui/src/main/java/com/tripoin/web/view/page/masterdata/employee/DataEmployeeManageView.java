package com.tripoin.web.view.page.masterdata.employee;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.EmployeeTransferObject.EnumFieldEmployee;
import com.tripoin.core.dto.OccupationTransferObject.EnumFieldOccupation;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IDataLoadStarted;
import com.tripoin.web.service.IEmployeeService;
import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.base.ATripoinForm;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = DataEmployeeManageView.BEAN_NAME, cached = false)
public class DataEmployeeManageView extends ATripoinForm<EmployeeData> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2092779948493749569L;
	public static final String BEAN_NAME = "dataEmployeeManageView";
	
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IDataLoadStarted dataLoadStarted;	

	@Override
	protected List<com.vaadin.ui.Component> designFormComponents(EmployeeData dataGrid) {
		List<com.vaadin.ui.Component> component = new ArrayList<com.vaadin.ui.Component>();
		Label sectionPersonal = new Label("Personal Info");
		component.add(sectionPersonal);
		sectionPersonal.addStyleName("h3");
		sectionPersonal.addStyleName("colored");
        
	    TextField employeeNameTextField = new TextField("Name");
		component.add(employeeNameTextField);
		employeeNameTextField.setId(EnumFieldEmployee.NAME_EMPLOYE.toString());
        employeeNameTextField.setRequired(true);
        employeeNameTextField.setWidth("50%");
        employeeNameTextField.addStyleName("small");
        employeeNameTextField.focus();
        
	    TextField nikTextField = new TextField("NIK");
		component.add(nikTextField);
		nikTextField.setId(EnumFieldEmployee.NIK_EMPLOYE.toString());
        nikTextField.setWidth("50%");
        nikTextField.setRequired(true);
        
	    ComboBox occupationComboBox = new ComboBox("Occupation");
		component.add(occupationComboBox);
		occupationComboBox.setId(EnumFieldEmployee.OCCUPATION.toString());
        occupationComboBox.setContainerDataSource(dataLoadStarted.getOccupationContainer(VaadinServlet.getCurrent().getServletContext()));
        occupationComboBox.setItemCaptionMode(ItemCaptionMode.ITEM);
        occupationComboBox.addStyleName("small");
        occupationComboBox.setTextInputAllowed(false);
        occupationComboBox.setNullSelectionAllowed(false);
        occupationComboBox.setRequired(true);
        occupationComboBox.setWidth("50%");
        
	    ComboBox parentEmployeeComboBox = new ComboBox("Head");
		component.add(parentEmployeeComboBox);
		parentEmployeeComboBox.setId(EnumFieldEmployee.PARENT_EMPLOYE.toString());
		parentEmployeeComboBox.setContainerDataSource(dataLoadStarted.employeeNotSalesmanContainer(VaadinServlet.getCurrent().getServletContext()));
        parentEmployeeComboBox.setItemCaptionMode(ItemCaptionMode.ITEM);
        parentEmployeeComboBox.addStyleName("small");
        parentEmployeeComboBox.setNullSelectionAllowed(true);
        parentEmployeeComboBox.setWidth("50%");
        parentEmployeeComboBox.setImmediate(true);
        
	    TextField usernameTextField = new TextField("Username");
		component.add(usernameTextField);
		usernameTextField.setId(EnumFieldEmployee.USERNAME_EMPLOYE.toString());
        usernameTextField.setWidth("50%");
        usernameTextField.setRequired(true);
        
	    TextField birthPlaceTextField = new TextField();
	    DateField birthDateDateField = new DateField();
        CssLayout placeDateOfBirth = new CssLayout();
		component.add(placeDateOfBirth);
        placeDateOfBirth.setWidth("60%");
        placeDateOfBirth.setCaption("Place, Date of Birth");
        placeDateOfBirth.addComponent(birthPlaceTextField);
        birthPlaceTextField.setId(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString());
        birthPlaceTextField.setWidthUndefined();
        birthPlaceTextField.setRequired(true);
        placeDateOfBirth.addComponent(birthDateDateField);
        birthDateDateField.setId(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString());
        birthDateDateField.setWidthUndefined();
        birthDateDateField.setRequired(true);
        birthDateDateField.setValue(new Date());
        
	    OptionGroup genderOptionGroup = new OptionGroup("Gender");
		component.add(genderOptionGroup);
		genderOptionGroup.setId(EnumFieldEmployee.GENDER_EMPLOYE.toString());
        genderOptionGroup.addItem(ParameterConstant.FEMALE);
        genderOptionGroup.addItem(ParameterConstant.MALE);
        genderOptionGroup.addStyleName("horizontal");
        genderOptionGroup.setRequired(true);
        genderOptionGroup.select(ParameterConstant.MALE);

        Label sectionContact = new Label("Contact Info");
		component.add(sectionContact);
        sectionContact.addStyleName("h3");
        sectionContact.addStyleName("colored");

        TextField phoneTextField = new TextField("Mobile Phone");
		component.add(phoneTextField);
		phoneTextField.setId(EnumFieldEmployee.PHONE_EMPLOYE.toString());
        phoneTextField.setWidth("50%");
        phoneTextField.setRequired(true);
                
	    TextField telpTextField = new TextField("Home Phone");
		component.add(telpTextField);
		telpTextField.setId(EnumFieldEmployee.TELP_EMPLOYE.toString());
        telpTextField.setWidth("50%");
        
	    TextField emailTextField = new TextField("Email");
		component.add(emailTextField);
		emailTextField.setId(EnumFieldEmployee.EMAIL_EMPLOYE.toString());
        emailTextField.setWidth("50%");
        emailTextField.setRequired(true);
        
	    TextArea addressTextArea = new TextArea("Address");
		component.add(addressTextArea);
		addressTextArea.setId(EnumFieldEmployee.ADDRESS_EMPLOYE.toString());
        addressTextArea.setWidth("50%");
        addressTextArea.setRequired(true);

		Label sectionAccountStatus = new Label("Account Status");
		component.add(sectionAccountStatus);
        sectionAccountStatus.addStyleName("h3");
        sectionAccountStatus.addStyleName("colored");    
        
	    Slider enabledAccount = new Slider("Enabled Account");
		component.add(enabledAccount);
		enabledAccount.setId(EnumFieldEmployee.ENABLE_EMPLOYE.toString());
    	enabledAccount.setWidth("50px");
    	enabledAccount.setResolution(0);
    	enabledAccount.setMin(0);
    	enabledAccount.setMax(1);

    	if(dataGrid != null){
            employeeNameTextField.setValue(dataGrid.getProfileData().getName());
            nikTextField.setValue(dataGrid.getNik());
            occupationComboBox.setValue(dataGrid.getOccupationData());
            parentEmployeeComboBox.setValue(dataGrid.getEmployeeDataParent());
            usernameTextField.setValue(dataGrid.getProfileData().getUserData().getUsername());
            birthPlaceTextField.setValue(dataGrid.getProfileData().getBirthplace());
            try {
            	birthDateDateField.setValue(ParameterConstant.FORMAT_DEFAULT.parse(dataGrid.getProfileData().getBirthdate()));
    		} catch (ReadOnlyException e) {
    			e.printStackTrace();
    		} catch (ConversionException e) {
    			e.printStackTrace();
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
            genderOptionGroup.setValue(dataGrid.getProfileData().getGender());
            phoneTextField.setValue(dataGrid.getProfileData().getPhone());
            telpTextField.setValue(dataGrid.getProfileData().getTelp());
            emailTextField.setValue(dataGrid.getProfileData().getEmail());
            addressTextArea.setValue(dataGrid.getProfileData().getAddress());
            enabledAccount.setValue((double)dataGrid.getProfileData().getUserData().getEnabled());
    	}
    	
		return component;			
	}

	@Override
	protected Map<String, ErrorMessage> validateErrorComponents(Map<String, Object> formPanelDatas, GeneralTransferObject generalTransferObject) {
		Map<String, ErrorMessage> errorComponents = new HashMap<String, ErrorMessage>();
		if(formPanelDatas.containsKey(EnumFieldEmployee.NAME_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.NAME_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.NAME_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.NAME_EMPLOYE.toString(), new UserError("Employee Name can not null!"));
			}
		}else if(formPanelDatas.containsKey(EnumFieldEmployee.NIK_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.NIK_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.NIK_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.NIK_EMPLOYE.toString(), new UserError("Employee NIK can not null!"));
			}
		}else if(formPanelDatas.containsKey(EnumFieldEmployee.OCCUPATION.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.OCCUPATION.toString()) == null || formPanelDatas.get(EnumFieldEmployee.OCCUPATION.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.OCCUPATION.toString(), new UserError("Employee Occupation can not null!"));
			}
		}else if(formPanelDatas.containsKey(EnumFieldEmployee.PARENT_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.PARENT_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.PARENT_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.PARENT_EMPLOYE.toString(), new UserError("Employee Parent can not null!"));
			}
		}else if(formPanelDatas.containsKey(EnumFieldEmployee.USERNAME_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.USERNAME_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.USERNAME_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.USERNAME_EMPLOYE.toString(), new UserError("Username can not null!"));
			}
		}else if(formPanelDatas.containsKey(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString(), new UserError("Employee Birthplace can not null!"));
			}
		}else if(formPanelDatas.containsKey(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString(), new UserError("Employee Birthdate can not null!"));
			}
		}else if(formPanelDatas.containsKey(EnumFieldEmployee.GENDER_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.GENDER_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.GENDER_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.GENDER_EMPLOYE.toString(), new UserError("Employee Gender can not null!"));
			}
		}else if(formPanelDatas.containsKey(EnumFieldEmployee.PHONE_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.PHONE_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.PHONE_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.PHONE_EMPLOYE.toString(), new UserError("Mobile Phone can not null!"));
			}
		}else if(formPanelDatas.containsKey(EnumFieldEmployee.EMAIL_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.EMAIL_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.EMAIL_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.EMAIL_EMPLOYE.toString(), new UserError("Email can not null!"));
			}
		}else if(formPanelDatas.containsKey(EnumFieldEmployee.ADDRESS_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.ADDRESS_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.ADDRESS_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.ADDRESS_EMPLOYE.toString(), new UserError("Employee Address can not null!"));
			}
		}

		if(generalTransferObject != null){
			if("1".equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error Update", "Employee error, please try again later!");
				errorComponents.put(EWebUIConstant.EXCEPTION.toString(), new UserError("Employee error, please try again later!"));
			}else if("2".equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error Update", "Employee name already exist.");
				errorComponents.put(EnumFieldOccupation.NAME_OCCUPATION.toString(), new UserError("Employee name already exist."));
			}else if("3".equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error Update", "Employee name already exist.");
				errorComponents.put(EnumFieldOccupation.NAME_OCCUPATION.toString(), new UserError("Employee name already exist."));
			}else if("4".equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error Update", "Contact Email and Mobile Phone already exists.");
				errorComponents.put(EnumFieldOccupation.NAME_OCCUPATION.toString(), new UserError("Contact Email and Mobile Phone already exists."));
			}
		}
		return errorComponents;
	}

	@Override
	protected GeneralTransferObject doOkButtonEvent(Map<String, Object> formPanelDatas, EmployeeData dataOriginalGrid) {
		return null;
	}

	@Override
	protected GeneralTransferObject doReOkButtonEvent(Map<String, Object> formPanelDatas, EmployeeData dataOriginalGrid) {
		EmployeeData employeeDataParent = (EmployeeData)formPanelDatas.get(EnumFieldEmployee.PARENT_EMPLOYE.toString());
		formPanelDatas.put(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString(), employeeDataParent.getNik());
		OccupationData occupationData = (OccupationData)formPanelDatas.get(EnumFieldEmployee.OCCUPATION.toString());
		formPanelDatas.put(EnumFieldEmployee.OCCUPATION_CODE.toString(), occupationData.getCode());
		EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
		employeeTransferObject.setFindEmployeeData(formPanelDatas);
		GeneralTransferObject generalTransferObject = employeeService.updateEmployee(employeeTransferObject, VaadinServlet.getCurrent().getServletContext());
		return generalTransferObject;
	}

	@Override
	protected void doCancelEvent() {
		super.doCancelEvent();
	}

	@Override
	protected String getPageTitle() {
		return "Data Employee";
	}

	@Override
	protected String afterButtonClickNavigate() {
		return DataEmployeeView.BEAN_NAME;
	}

	@Override
	protected Class<? extends ATripoinForm<EmployeeData>> getViewClass() {
		return this.getClass();
	}
	
}