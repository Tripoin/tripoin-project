package com.tripoin.web.view.page.masterdata.occupation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.ABaseDataTransferObject;
import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject.EnumFieldOccupation;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IOccupationService;
import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.base.ATripoinPage;
import com.tripoin.web.view.base.ITripoinConstantComponent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TextField;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = DataOccupationView.BEAN_NAME, cached = true)
public class DataOccupationView extends ATripoinPage<OccupationData> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3718621115290540326L;
	public static final String BEAN_NAME = "dataOccupationView";
	private GeneralPagingTransferObject generalPagingTransferObjectSearch = new GeneralPagingTransferObject();

	@Autowired
	private IOccupationService occupationService;

	@Override
	protected List<com.vaadin.ui.Component> designSearchComponents() {
		List<com.vaadin.ui.Component> component = new ArrayList<com.vaadin.ui.Component>();
		TextField occupationNameTextField = new TextField("Occupation Name");
		occupationNameTextField.setId(EnumFieldOccupation.NAME_OCCUPATION.toString());
		occupationNameTextField.setStyleName("small");
		occupationNameTextField.setWidth("60%");
		component.add(occupationNameTextField);
		return component;
	}

	@Override
	protected ABaseDataTransferObject<OccupationData> getAllDatasService(GeneralPagingTransferObject generalPagingTransferObject, HashMap<String, Object> searchPanelDatas) {
    	generalPagingTransferObjectSearch.setPositionPage(generalPagingTransferObject.getPositionPage());
    	generalPagingTransferObjectSearch.setRowPerPage(EWebUIConstant.ROW_PER_PAGE.getOperatorInt());
    	generalPagingTransferObjectSearch.setParameterData(searchPanelDatas);
    	return occupationService.getAllOccupationDatas(generalPagingTransferObjectSearch);
	}

	@Override
	protected GeneralPagingTransferObject doDeleteService(List<OccupationData> dataObjectSelect) {
		return null;
	}
	
	@Override
	protected void initMenuItemGridDefault() {
		super.initMenuItemGridDefault();
	}

	@Override
	protected boolean isEnabledCreate() {
		return false;
	}

	@Override
	protected BeanItemContainer<OccupationData> getBeanDataContainer() {
		return new BeanItemContainer<>(OccupationData.class);
	}

	@Override
	protected Object[] getFieldContainerPropertyHeader() {
		return new Object[]{"name", "remarks", "createdBy", "createdIP", "createdTime", 
	    		"createdPlatform", "modifiedBy", "modifiedIP", "modifiedTime", "modifiedPlatform"};
	}
	
	@Override
	protected Object[] addNestedFieldContainerProperty() {
		return null;
	}

	@Override
	protected Object[] removeFieldContainerProperty() {
		return new String[]{"id", "status", "code"};
	}

	@Override
	protected Map<String, String> getColumnAlias() {
		Map<String, String> columns = new HashMap<String, String>();
		columns.put("name", "Occupation Name");
		columns.put("remarks", "Description");
		columns.put("createdIP", "Created IP Address");
		columns.put("modifiedIP", "Modified IP Address");
		return columns;
	}

	@Override
    public void enter(ViewChangeEvent event) {
		super.enter(event);
		if(event.getOldView() instanceof DataOccupationManageView){
			DataOccupationManageView oldView = (DataOccupationManageView)event.getOldView();
			if(ITripoinConstantComponent.Button.SAVE.equals(oldView.okButtonCaption()))
		        this.commonComponent.getSearchContainer().getDataField(false);
		}
    }

	@Override
	protected String getPageTitle() {
		return "Data Occupation";
	}

	@Override
	protected String afterGridClickNavigate() {
		return DataOccupationManageView.BEAN_NAME;
	}

	@Override
	protected boolean isSetMenuGrid() {
		return true;
	}

	@Override
	protected String reportJasperNameSelected() {
		return "Occupation.jasper";
	}

	@Override
	protected String reportJasperNameAll() {
		return "OccupationAll.jasper";
	}

	@Override
	protected String reportNameSelected() {
		return "Report-Occupation";
	}

	@Override
	protected String reportNameAll() {
		return "Report-Occupation-All";
	}

	@Override
	protected Class<? extends ATripoinPage<OccupationData>> getViewClass() {
		return this.getClass();
	}
	
}