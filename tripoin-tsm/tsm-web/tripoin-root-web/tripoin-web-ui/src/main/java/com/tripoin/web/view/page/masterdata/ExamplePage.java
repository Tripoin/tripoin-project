package com.tripoin.web.view.page.masterdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject.EnumFieldOccupation;
import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.base.ATripoinForm;
import com.tripoin.web.view.page.masterdata.occupation.DataOccupationView;
import com.vaadin.server.ErrorMessage;
import com.vaadin.ui.TextField;

/**
 * @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = ExamplePage.BEAN_NAME, cached = false)
public class ExamplePage extends ATripoinForm<OccupationData> {

	private static final long serialVersionUID = -4592518571070450190L;
	public static final String BEAN_NAME = "examplePage";

	@Override
	protected List<com.vaadin.ui.Component> designFormComponent(OccupationData dataGrid) {
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
	protected Map<String, ErrorMessage> errorComponents(Map<String, Object> dataFields, GeneralTransferObject generalTransferObject) {
		return null;
	}

	@Override
	protected GeneralTransferObject doOkButtonEvent(Map<String, Object> formPanelDatas, OccupationData dataOriginalGrid) {
		return null;
	}

	@Override
	protected GeneralTransferObject doReOkButtonEvent(Map<String, Object> formPanelDatas, OccupationData dataOriginalGrid) {
		return null;
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
