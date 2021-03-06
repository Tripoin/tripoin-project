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

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.AreaData;
import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeePrivateData;
import com.tripoin.core.dto.EmployeeTransferObject.EnumFieldEmployee;
import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IDataLoadStarted;
import com.tripoin.web.service.IEmployeeService;
import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.base.ATripoinForm;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
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

	private ComboBox parentEmployeeComboBox;
	private ComboBox areaComboBox;

	@Override
	protected List<com.vaadin.ui.Component> designFormComponents(final EmployeeData dataGrid) {
		List<com.vaadin.ui.Component> component = new ArrayList<com.vaadin.ui.Component>();
		Label sectionPersonal = new Label("Personal Info");
		component.add(sectionPersonal);
		sectionPersonal.addStyleName("h3");
		sectionPersonal.addStyleName("colored");
        
	    TextField usernameTextField = new TextField("Username");
		component.add(usernameTextField);
		usernameTextField.setId(EnumFieldEmployee.USERNAME_EMPLOYE.toString());
        usernameTextField.setWidth("50%");
        if(dataGrid == null)
        	usernameTextField.addStyleName("small");
        else
        	usernameTextField.addStyleName("borderless");
        
	    TextField employeeNameTextField = new TextField("Name");
		component.add(employeeNameTextField);
		employeeNameTextField.setId(EnumFieldEmployee.NAME_EMPLOYE.toString());
        employeeNameTextField.setRequired(true);
        employeeNameTextField.setWidth("50%");
        employeeNameTextField.addStyleName("small");
        
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
        
        areaComboBox = new ComboBox("Area");
        areaComboBox.setVisible(false);
		component.add(areaComboBox);
        BeanItemContainer<AreaData> areaData = dataLoadStarted.getAreaContainer(VaadinServlet.getCurrent().getServletContext());
        areaComboBox.setContainerDataSource(areaData);
        areaComboBox.setItemCaptionMode(ItemCaptionMode.ITEM);
		areaComboBox.setId(EnumFieldEmployee.AREA.toString());
		areaComboBox.addStyleName("small");
		areaComboBox.setWidth("50%");
		areaComboBox.setImmediate(true);
		areaComboBox.setRequired(true);
        
	    parentEmployeeComboBox = new ComboBox("Head");
		component.add(parentEmployeeComboBox);
		parentEmployeeComboBox.setId(EnumFieldEmployee.PARENT_EMPLOYE.toString());
        parentEmployeeComboBox.addStyleName("small");
        parentEmployeeComboBox.setWidth("50%");
        parentEmployeeComboBox.setImmediate(true);
        
        occupationComboBox.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = -536223779275547217L;
			@Override
			public void valueChange(ValueChangeEvent event) {
		        if(parentEmployeeComboBox.getContainerDataSource() != null)
		        	parentEmployeeComboBox.setContainerDataSource(null);
				OccupationData occupationData = (OccupationData)event.getProperty().getValue();
				if(RoleConstant.ROLE_SALESMAN.equals(occupationData.getCode())){
			        // TODO Area
			        areaComboBox.setVisible(false);
			        // TODO Head
					BeanItemContainer<EmployeePrivateData> employeeParentData = dataLoadStarted.employeeAreaSalesManagerContainer(VaadinServlet.getCurrent().getServletContext());
					if(dataGrid != null){
						if(!RoleConstant.ROLE_SALESMAN.equals(dataGrid.getOccupationData().getCode())){
							EmployeePrivateData selfEmployeeParent = new EmployeePrivateData();
							selfEmployeeParent.setNik(dataGrid.getNik());
							selfEmployeeParent.setName(dataGrid.getProfileData().getName());
							selfEmployeeParent.setOccupationCode(dataGrid.getOccupationData().getCode());
							employeeParentData.removeItem(selfEmployeeParent);
						}
					}
					parentEmployeeComboBox.setVisible(true);
					parentEmployeeComboBox.setContainerDataSource(employeeParentData);
			        parentEmployeeComboBox.setItemCaptionMode(ItemCaptionMode.ITEM);
			        parentEmployeeComboBox.setNullSelectionAllowed(false);
	            	parentEmployeeComboBox.setRequired(true);
			        if(employeeParentData != null && employeeParentData.size() > 0)
			        	parentEmployeeComboBox.select(employeeParentData.getItemIds().get(0));
			        employeeParentData = null;
				}else if(RoleConstant.ROLE_AREASALESMANAGER.equals(occupationData.getCode())){
			        // TODO Area
			        areaComboBox.setVisible(true);
			        if(dataGrid.getAreaData() != null)
			        	areaComboBox.select(dataGrid.getAreaData());
			        // TODO Head
					BeanItemContainer<EmployeePrivateData> employeeParentData = dataLoadStarted.employeeNationalSalesManagerContainer(VaadinServlet.getCurrent().getServletContext());
					if(dataGrid != null){
						if(!RoleConstant.ROLE_AREASALESMANAGER.equals(dataGrid.getOccupationData().getCode())){
							EmployeePrivateData selfEmployeeParent = new EmployeePrivateData();
							selfEmployeeParent.setNik(dataGrid.getNik());
							selfEmployeeParent.setName(dataGrid.getProfileData().getName());
							selfEmployeeParent.setOccupationCode(dataGrid.getOccupationData().getCode());
							employeeParentData.removeItem(selfEmployeeParent);
						}	
					}
					parentEmployeeComboBox.setVisible(true);
					parentEmployeeComboBox.setContainerDataSource(employeeParentData);
			        parentEmployeeComboBox.setItemCaptionMode(ItemCaptionMode.ITEM);
			        parentEmployeeComboBox.setNullSelectionAllowed(false);
	            	parentEmployeeComboBox.setRequired(true);
	            	if(employeeParentData != null && employeeParentData.size() > 0)
			        	parentEmployeeComboBox.select(employeeParentData.getItemIds().get(0));
			        employeeParentData = null;
				}else{
			        // TODO Area
			        areaComboBox.setVisible(false);
			        // TODO Head
			        parentEmployeeComboBox.setVisible(false);
	            	parentEmployeeComboBox.setRequired(false);
				}
			}
		});
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
        sectionAccountStatus.addStyleName("h3");
        sectionAccountStatus.addStyleName("colored");    
        
	    Slider enabledAccount = new Slider("Enabled Account");
		enabledAccount.setId(EnumFieldEmployee.ENABLE_EMPLOYE.toString());
    	enabledAccount.setWidth("50px");
    	enabledAccount.setResolution(0);
    	enabledAccount.setMin(0);
    	enabledAccount.setMax(1);

    	if(dataGrid != null){
            employeeNameTextField.focus();
    		component.add(sectionAccountStatus);
    		component.add(enabledAccount);
            employeeNameTextField.setValue(dataGrid.getProfileData().getName());
            nikTextField.setValue(dataGrid.getNik());
            occupationComboBox.setValue(dataGrid.getOccupationData());
            if(dataGrid.getEmployeeParentData() != null){
            	parentEmployeeComboBox.select(dataGrid.getEmployeeParentData());
            	parentEmployeeComboBox.setRequired(true);
            	parentEmployeeComboBox.setVisible(true);
            }else{
            	parentEmployeeComboBox.setRequired(false);
            	parentEmployeeComboBox.setVisible(false);
            }
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
    	}else{
    		usernameTextField.focus();
    		usernameTextField.setRequired(true);
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
		}else{
			errorComponents.put(EnumFieldEmployee.NAME_EMPLOYE.toString(), new UserError("Employee Name can not null!"));
		}
		if(formPanelDatas.containsKey(EnumFieldEmployee.NIK_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.NIK_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.NIK_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.NIK_EMPLOYE.toString(), new UserError("Employee NIK can not null!"));
			}
		}else{
			errorComponents.put(EnumFieldEmployee.NIK_EMPLOYE.toString(), new UserError("Employee NIK can not null!"));
		}
		if(formPanelDatas.containsKey(EnumFieldEmployee.OCCUPATION.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.OCCUPATION.toString()) == null || formPanelDatas.get(EnumFieldEmployee.OCCUPATION.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.OCCUPATION.toString(), new UserError("Employee Occupation can not null!"));
			}else{
				OccupationData occupationData = (OccupationData)formPanelDatas.get(EnumFieldEmployee.OCCUPATION.toString());
				if(RoleConstant.ROLE_AREASALESMANAGER.equals(occupationData.getCode()) && 
						(formPanelDatas.get(EnumFieldEmployee.AREA.toString()) == null || formPanelDatas.get(EnumFieldEmployee.AREA.toString()).toString().isEmpty())){
					errorComponents.put(EnumFieldEmployee.AREA.toString(), new UserError("Area can not null!"));
				}
				occupationData = null;
			}
		}else{
			if(formPanelDatas.containsKey(EnumFieldEmployee.OCCUPATION_CODE.toString())){
				if(formPanelDatas.get(EnumFieldEmployee.OCCUPATION_CODE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.OCCUPATION_CODE.toString()).toString().isEmpty()){
					errorComponents.put(EnumFieldEmployee.OCCUPATION.toString(), new UserError("Employee Occupation can not null!"));
				}else{
					if(RoleConstant.ROLE_AREASALESMANAGER.equals(formPanelDatas.get(EnumFieldEmployee.OCCUPATION.toString())) && 
							(formPanelDatas.get(EnumFieldEmployee.AREA.toString()) == null || formPanelDatas.get(EnumFieldEmployee.AREA.toString()).toString().isEmpty())){
						errorComponents.put(EnumFieldEmployee.AREA.toString(), new UserError("Area can not null!"));
					}
				}
			}else
				errorComponents.put(EnumFieldEmployee.OCCUPATION.toString(), new UserError("Employee Occupation can not null!"));
		}
		if(formPanelDatas.containsKey(EnumFieldEmployee.PARENT_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.PARENT_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.PARENT_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.PARENT_EMPLOYE.toString(), new UserError("Employee Parent can not null!"));
			}
		}else{
			if(formPanelDatas.containsKey(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString())){
				if(formPanelDatas.get(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString()).toString().isEmpty()){
					errorComponents.put(EnumFieldEmployee.PARENT_EMPLOYE.toString(), new UserError("Employee Parent can not null!"));
				}
			}else
				errorComponents.put(EnumFieldEmployee.PARENT_EMPLOYE.toString(), new UserError("Employee Parent can not null!"));
		}
		if(formPanelDatas.containsKey(EnumFieldEmployee.USERNAME_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.USERNAME_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.USERNAME_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.USERNAME_EMPLOYE.toString(), new UserError("Username can not null!"));
			}
		}else{
			errorComponents.put(EnumFieldEmployee.USERNAME_EMPLOYE.toString(), new UserError("Username can not null!"));
		}
		if(formPanelDatas.containsKey(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString(), new UserError("Employee Birthplace can not null!"));
			}
		}else{
			errorComponents.put(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString(), new UserError("Employee Birthplace can not null!"));
		}
		if(formPanelDatas.containsKey(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString(), new UserError("Employee Birthdate can not null!"));
			}
		}else{
			errorComponents.put(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString(), new UserError("Employee Birthdate can not null!"));
		}
		if(formPanelDatas.containsKey(EnumFieldEmployee.GENDER_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.GENDER_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.GENDER_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.GENDER_EMPLOYE.toString(), new UserError("Employee Gender can not null!"));
			}
		}else{
			errorComponents.put(EnumFieldEmployee.GENDER_EMPLOYE.toString(), new UserError("Employee Gender can not null!"));
		}
		if(formPanelDatas.containsKey(EnumFieldEmployee.PHONE_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.PHONE_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.PHONE_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.PHONE_EMPLOYE.toString(), new UserError("Mobile Phone can not null!"));
			}
		}else{
			errorComponents.put(EnumFieldEmployee.PHONE_EMPLOYE.toString(), new UserError("Mobile Phone can not null!"));
		}
		if(formPanelDatas.containsKey(EnumFieldEmployee.EMAIL_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.EMAIL_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.EMAIL_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.EMAIL_EMPLOYE.toString(), new UserError("Email can not null!"));
			}
		}else{
			errorComponents.put(EnumFieldEmployee.EMAIL_EMPLOYE.toString(), new UserError("Email can not null!"));
		}
		if(formPanelDatas.containsKey(EnumFieldEmployee.ADDRESS_EMPLOYE.toString())){
			if(formPanelDatas.get(EnumFieldEmployee.ADDRESS_EMPLOYE.toString()) == null || formPanelDatas.get(EnumFieldEmployee.ADDRESS_EMPLOYE.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldEmployee.ADDRESS_EMPLOYE.toString(), new UserError("Employee Address can not null!"));
			}
		}

		if(generalTransferObject != null){
			if(EResponseCode.RC_FAILURE.getResponseCode().equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error", "Employee error, please try again later!");
				errorComponents.put(EWebUIConstant.EXCEPTION.toString(), new UserError("Employee error, please try again later!"));
			}else if(EResponseCode.RC_USERNAME_EXISTS.getResponseCode().equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error", EResponseCode.RC_USERNAME_EXISTS.toString());
				errorComponents.put(EnumFieldEmployee.USERNAME_EMPLOYE.toString(), new UserError(EResponseCode.RC_USERNAME_EXISTS.toString()));
			}else if(EResponseCode.RC_NIK_EXISTS.getResponseCode().equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error", EResponseCode.RC_NIK_EXISTS.toString());
				errorComponents.put(EnumFieldEmployee.NIK_EMPLOYE.toString(), new UserError(EResponseCode.RC_NIK_EXISTS.toString()));
			}else if(EResponseCode.RC_PHONE_EXISTS.getResponseCode().equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error", EResponseCode.RC_PHONE_EXISTS.toString());
				errorComponents.put(EnumFieldEmployee.PHONE_EMPLOYE.toString(), new UserError(EResponseCode.RC_PHONE_EXISTS.toString()));
			}else if(EResponseCode.RC_EMAIL_EXISTS.getResponseCode().equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error", EResponseCode.RC_EMAIL_EXISTS.toString());
				errorComponents.put(EnumFieldEmployee.EMAIL_EMPLOYE.toString(), new UserError(EResponseCode.RC_EMAIL_EXISTS.toString()));
			}else if(EResponseCode.RC_GENDER_NOT_DEFINE.getResponseCode().equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error", EResponseCode.RC_GENDER_NOT_DEFINE.toString());
				errorComponents.put(EnumFieldEmployee.GENDER_EMPLOYE.toString(), new UserError(EResponseCode.RC_GENDER_NOT_DEFINE.toString()));
			}else if(EResponseCode.RC_AREA_NOTNULL.getResponseCode().equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error", EResponseCode.RC_AREA_NOTNULL.toString());
				errorComponents.put(EnumFieldEmployee.AREA.toString(), new UserError(EResponseCode.RC_AREA_NOTNULL.toString()));
			}else if(EResponseCode.RC_SALESMAN_REALLOCATE.getResponseCode().equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error", EResponseCode.RC_SALESMAN_REALLOCATE.toString());
				errorComponents.put(EnumFieldEmployee.OCCUPATION.toString(), new UserError(EResponseCode.RC_SALESMAN_REALLOCATE.toString()));
			}else if(EResponseCode.RC_EMPLOYEE_PARENT_NOTNULL.getResponseCode().equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error", EResponseCode.RC_EMPLOYEE_PARENT_NOTNULL.toString());
				errorComponents.put(EnumFieldEmployee.PARENT_EMPLOYE.toString(), new UserError(EResponseCode.RC_EMPLOYEE_PARENT_NOTNULL.toString()));
			}
		}
		return errorComponents;
	}

	@Override
	protected boolean isEditReOkButtonCaption() {
		return true;
	}

	@Override
	protected GeneralTransferObject doOkButtonEvent(HashMap<String, Object> formPanelDatas, EmployeeData dataOriginalGrid) {
		if(formPanelDatas.get(EnumFieldEmployee.PARENT_EMPLOYE.toString()) != null){
			EmployeePrivateData employeeDataParent = (EmployeePrivateData)formPanelDatas.get(EnumFieldEmployee.PARENT_EMPLOYE.toString());
			formPanelDatas.put(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString(), employeeDataParent.getNik());
		}
		formPanelDatas.remove(EnumFieldEmployee.PARENT_EMPLOYE.toString());	
		OccupationData occupationData = (OccupationData)formPanelDatas.get(EnumFieldEmployee.OCCUPATION.toString());
		formPanelDatas.put(EnumFieldEmployee.OCCUPATION_CODE.toString(), occupationData.getCode());
		formPanelDatas.remove(EnumFieldEmployee.OCCUPATION.toString());
		if(formPanelDatas.get(EnumFieldEmployee.AREA.toString()) != null){
			AreaData areaData = (AreaData)formPanelDatas.get(EnumFieldEmployee.AREA.toString());
			formPanelDatas.put(EnumFieldEmployee.AREA_CODE.toString(), areaData.getCode());
		}
		formPanelDatas.remove(EnumFieldEmployee.AREA.toString());
		GeneralPagingTransferObject generalPagingTransferObject = new GeneralPagingTransferObject();
		generalPagingTransferObject.setParameterData(formPanelDatas);
		GeneralTransferObject generalTransferObject = employeeService.saveEmployee(generalPagingTransferObject, VaadinServlet.getCurrent().getServletContext());
		return generalTransferObject;
	}

	@Override
	protected GeneralTransferObject doReOkButtonEvent(HashMap<String, Object> formPanelDatas, EmployeeData dataOriginalGrid) {
		if(dataOriginalGrid.getProfileData().getUserData().getUsername() != null)
			formPanelDatas.put(EnumFieldEmployee.USERNAME_EMPLOYE.toString(), dataOriginalGrid.getProfileData().getUserData().getUsername());
		if(formPanelDatas.get(EnumFieldEmployee.PARENT_EMPLOYE.toString()) != null){
			EmployeePrivateData employeeDataParent = (EmployeePrivateData)formPanelDatas.get(EnumFieldEmployee.PARENT_EMPLOYE.toString());
			formPanelDatas.put(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString(), employeeDataParent.getNik());
		}
		formPanelDatas.remove(EnumFieldEmployee.PARENT_EMPLOYE.toString());	
		OccupationData occupationData = (OccupationData)formPanelDatas.get(EnumFieldEmployee.OCCUPATION.toString());
		formPanelDatas.put(EnumFieldEmployee.OCCUPATION_CODE.toString(), occupationData.getCode());
		formPanelDatas.remove(EnumFieldEmployee.OCCUPATION.toString());
		if(formPanelDatas.get(EnumFieldEmployee.AREA.toString()) != null){
			AreaData areaData = (AreaData)formPanelDatas.get(EnumFieldEmployee.AREA.toString());
			formPanelDatas.put(EnumFieldEmployee.AREA_CODE.toString(), areaData.getCode());
		}
		formPanelDatas.remove(EnumFieldEmployee.AREA.toString());
		GeneralPagingTransferObject generalPagingTransferObject = new GeneralPagingTransferObject();
		generalPagingTransferObject.setParameterData(formPanelDatas);
		GeneralTransferObject generalTransferObject = employeeService.updateEmployee(generalPagingTransferObject, VaadinServlet.getCurrent().getServletContext());
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