package com.tripoin.web.view.page.masterdata.area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.dto.AreaData;
import com.tripoin.core.dto.AreaTransferObject;
import com.tripoin.core.dto.AreaTransferObject.EnumFieldArea;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IAreaService;
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
@VaadinView(value = DataAreaManageView.BEAN_NAME, cached = false)
public class DataAreaManageView extends ATripoinForm<AreaData> {

	private static final long serialVersionUID = -4592518571070450190L;
	public static final String BEAN_NAME = "dataAreaManageView";
	
	@Autowired
	private IAreaService areaService;

	@Override
	protected List<com.vaadin.ui.Component> designFormComponents(AreaData dataGrid) {
		List<com.vaadin.ui.Component> component = new ArrayList<com.vaadin.ui.Component>();
		TextField areaNameTextField = new TextField("Area Name");
		areaNameTextField.setId(EnumFieldArea.NAME_AREA.toString());
		areaNameTextField.setStyleName("small");
		areaNameTextField.setWidth("60%");
		areaNameTextField.setRequired(true);
		areaNameTextField.focus();
		component.add(areaNameTextField);
		TextField areaDescriptionTextField = new TextField("Area Description");
		areaDescriptionTextField.setId(EnumFieldArea.DESCRIPTION_AREA.toString());
		areaDescriptionTextField.setStyleName("small");
		areaDescriptionTextField.setWidth("60%");
		if(dataGrid != null){
			areaNameTextField.setValue(dataGrid.getName());
			areaDescriptionTextField.setValue(dataGrid.getRemarks());
		}
		component.add(areaDescriptionTextField);
		return component;
	}

	@Override
	protected Map<String, ErrorMessage> validateErrorComponents(Map<String, Object> formPanelDatas, GeneralTransferObject generalTransferObject) {
		Map<String, ErrorMessage> errorComponents = new HashMap<String, ErrorMessage>();
		if(formPanelDatas.containsKey(EnumFieldArea.NAME_AREA.toString())){
			if(formPanelDatas.get(EnumFieldArea.NAME_AREA.toString()) == null || formPanelDatas.get(EnumFieldArea.NAME_AREA.toString()).toString().isEmpty()){
				errorComponents.put(EnumFieldArea.NAME_AREA.toString(), new UserError("Area Name not null!"));
			}
		}
		if(generalTransferObject != null){
			if(EResponseCode.RC_FAILURE.getResponseCode().equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error Update", "Area error, please try again later!");
				errorComponents.put(EWebUIConstant.EXCEPTION.toString(), new UserError("Area error, please try again later!"));
			}else if(EResponseCode.RC_AREA_EXISTS.getResponseCode().equals(generalTransferObject.getResponseCode())){
				tripoinNotification.show("Error Update", generalTransferObject.getResponseDesc());
				errorComponents.put(EnumFieldArea.NAME_AREA.toString(), new UserError(generalTransferObject.getResponseDesc()));
			}
		}
		return errorComponents;
	}

	@Override
	protected GeneralTransferObject doOkButtonEvent(HashMap<String, Object> formPanelDatas, AreaData dataOriginalGrid) {
		AreaTransferObject areaTransferObject = new AreaTransferObject();
		areaTransferObject.setParameterData(formPanelDatas);
		GeneralTransferObject generalTransferObject = areaService.saveArea(areaTransferObject, VaadinServlet.getCurrent().getServletContext());
		return generalTransferObject;
	}

	@Override
	protected GeneralTransferObject doReOkButtonEvent(HashMap<String, Object> formPanelDatas, AreaData dataOriginalGrid) {
		formPanelDatas.put(EnumFieldArea.CODE_AREA.toString(), dataOriginalGrid.getCode());
		AreaTransferObject areaTransferObject = new AreaTransferObject();
		areaTransferObject.setParameterData(formPanelDatas);
		GeneralTransferObject generalTransferObject = areaService.updateArea(areaTransferObject, VaadinServlet.getCurrent().getServletContext());
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
		return "Data Area";
	}

	@Override
	protected String afterButtonClickNavigate() {
		return DataAreaView.BEAN_NAME;
	}

	@Override
	protected Class<? extends ATripoinForm<AreaData>> getViewClass() {
		return this.getClass();
	}
    
}
