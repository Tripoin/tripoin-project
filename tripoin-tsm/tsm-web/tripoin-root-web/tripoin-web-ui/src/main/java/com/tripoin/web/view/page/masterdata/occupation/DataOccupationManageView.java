package com.tripoin.web.view.page.masterdata.occupation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.core.dto.OccupationTransferObject.EnumFieldOccupation;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IOccupationService;
import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.base.ATripoinForm;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.TextField;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = DataOccupationManageView.BEAN_NAME, cached = false)
public class DataOccupationManageView extends ATripoinForm<OccupationData> {

	private static final long serialVersionUID = -4592518571070450190L;
	public static final String BEAN_NAME = "dataOccupationManageView";
	
	@Autowired
	private IOccupationService occupationService;

	@Override
	protected List<com.vaadin.ui.Component> designFormComponents(OccupationData dataGrid) {
		List<com.vaadin.ui.Component> component = new ArrayList<com.vaadin.ui.Component>();
		TextField occupationNameTextField = new TextField("Occupation Name");
		occupationNameTextField.setValue(dataGrid.getName());
		occupationNameTextField.setId(EnumFieldOccupation.NAME_OCCUPATION.toString());
		occupationNameTextField.setStyleName("small");
		occupationNameTextField.setWidth("60%");
		occupationNameTextField.setRequired(true);
		occupationNameTextField.focus();
		component.add(occupationNameTextField);
		TextField occupationDescriptionTextField = new TextField("Occupation Description");
		occupationDescriptionTextField.setValue(dataGrid.getRemarks());
		occupationDescriptionTextField.setId(EnumFieldOccupation.DESCRIPTION_OCCUPATION.toString());
		occupationDescriptionTextField.setStyleName("small");
		occupationDescriptionTextField.setWidth("60%");
		component.add(occupationDescriptionTextField);
		return component;
	}

	@Override
	protected Map<String, ErrorMessage> validateErrorComponents(Map<String, Object> formPanelDatas, GeneralTransferObject generalTransferObject) {
		Map<String, ErrorMessage> errorComponents = new HashMap<String, ErrorMessage>();
		if(formPanelDatas.containsKey(EnumFieldOccupation.NAME_OCCUPATION.toString())){
			if(formPanelDatas.get(EnumFieldOccupation.NAME_OCCUPATION.toString()) == null || formPanelDatas.get(EnumFieldOccupation.NAME_OCCUPATION.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldOccupation.NAME_OCCUPATION.toString(), new UserError("Occupation Name not null!"));
			}
		}
		if(generalTransferObject != null){
			if("1".equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error Update", "Occupation error, please try again later!");
				errorComponents.put(EWebUIConstant.EXCEPTION.toString(), new UserError("Occupation error, please try again later!"));
			}else if("2".equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error Update", "Occupation name already exist.");
				errorComponents.put(EnumFieldOccupation.NAME_OCCUPATION.toString(), new UserError("Occupation name already exist."));
			}
		}
		return errorComponents;
	}

	@Override
	protected GeneralTransferObject doOkButtonEvent(Map<String, Object> formPanelDatas, OccupationData dataOriginalGrid) {
		return null;
	}

	@Override
	protected GeneralTransferObject doReOkButtonEvent(Map<String, Object> formPanelDatas, OccupationData dataOriginalGrid) {
		formPanelDatas.put(EnumFieldOccupation.CODE_OCCUPATION.toString(), dataOriginalGrid.getCode());
		OccupationTransferObject dataTransferObject = new OccupationTransferObject();
		dataTransferObject.setFindOccupationData(formPanelDatas);
		GeneralTransferObject generalTransferObject = occupationService.updateOccupation(dataTransferObject, VaadinServlet.getCurrent().getServletContext());
		return generalTransferObject;
	}

	@Override
	protected boolean isEditReOkButtonCaption() {
		return true;
	}

	@Override
	protected void doCancelEvent() {
		super.doCancelEvent();
	}

	@Override
	protected String getPageTitle() {
		return "Data Occupation";
	}

	@Override
	protected String afterButtonClickNavigate() {
		return DataOccupationView.BEAN_NAME;
	}

	@Override
	protected Class<? extends ATripoinForm<OccupationData>> getViewClass() {
		return this.getClass();
	}

}