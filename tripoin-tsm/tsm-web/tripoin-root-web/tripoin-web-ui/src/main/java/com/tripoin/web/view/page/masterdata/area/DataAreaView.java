package com.tripoin.web.view.page.masterdata.area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.AreaData;
import com.tripoin.core.dto.AreaTransferObject;
import com.tripoin.core.dto.AreaTransferObject.EnumFieldArea;
import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IAreaService;
import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.base.ATripoinPage;
import com.tripoin.web.view.base.ITripoinConstantComponent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.TextField;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = DataAreaView.BEAN_NAME, cached = true)
public class DataAreaView extends ATripoinPage<AreaData> {

	private static final long serialVersionUID = -3718621115290540326L;
	public static final String BEAN_NAME = "dataAreaView";
	public static final String PAGE_NAME = "Data Area";
	private AreaTransferObject areaTransferObjectSearch = new AreaTransferObject();

	@Autowired
	private IAreaService areaService;
	
	@Override
	protected List<com.vaadin.ui.Component> designSearchComponents() {
		List<com.vaadin.ui.Component> component = new ArrayList<com.vaadin.ui.Component>();
		TextField areaNameTextField = new TextField("Area Name");
		areaNameTextField.setId(EnumFieldArea.NAME_AREA.toString());
		areaNameTextField.setStyleName("small");
		areaNameTextField.setWidth("60%");
		component.add(areaNameTextField);
		return component;
	}

	@Override
	protected GeneralPagingTransferObject<AreaData> getALlDatasService(GeneralPagingTransferObject<AreaData> generalPagingTransferObject, Map<String, Object> searchPanelDatas) {
    	areaTransferObjectSearch.setPositionPage(generalPagingTransferObject.getPositionPage());
    	areaTransferObjectSearch.setRowPerPage(EWebUIConstant.ROW_PER_PAGE.getOperatorInt());
    	areaTransferObjectSearch.setFindAreaData(searchPanelDatas);
    	return areaService.getAllAreaDatas(areaTransferObjectSearch);
	}

	@Override
	protected GeneralPagingTransferObject<AreaData> doDeleteService(List<AreaData> dataObjectSelect) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		for(AreaData areaData : dataObjectSelect)
			dataMap.put(areaData.getCode(), areaData.getCode());
		AreaTransferObject areaTransferObject = new AreaTransferObject();
		areaTransferObject.setFindAreaData(dataMap);
		return areaService.deleteArea(areaTransferObject, VaadinServlet.getCurrent().getServletContext());
	}
	
	@Override
	protected void initMenuItemGridDefault() {
		super.initMenuItemGridDefault();
	}

	@Override
	protected BeanItemContainer<AreaData> getBeanDataContainer() {
		return new BeanItemContainer<>(AreaData.class);
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
		columns.put("name", "Area Name");
		columns.put("remarks", "Description");
		columns.put("createdIP", "Created IP Address");
		columns.put("modifiedIP", "Modified IP Address");
		return columns;
	}

	@Override
    public void enter(ViewChangeEvent event) {
		super.enter(event);
		if(event.getOldView() instanceof DataAreaManageView){
			DataAreaManageView oldView = (DataAreaManageView)event.getOldView();
			if(ITripoinConstantComponent.Button.SAVE.equals(oldView.okButtonCaption()))
		        this.commonComponent.getSearchContainer().getDataField(false);	
		}
    }

	@Override
	protected String getPageTitle() {
		return "Data Area";
	}

	@Override
	protected String afterGridClickNavigate() {
		return DataAreaManageView.BEAN_NAME;
	}

	@Override
	protected boolean isSetMenuGrid() {
		return true;
	}

	@Override
	protected String reportJasperNameSelected() {
		return "Area.jasper";
	}

	@Override
	protected String reportJasperNameAll() {
		return "AreaAll.jasper";
	}

	@Override
	protected String reportNameSelected() {
		return "Report-Area";
	}

	@Override
	protected String reportNameAll() {
		return "Report-Area-All";
	}

	@Override
	protected Class<? extends ATripoinPage<AreaData>> getViewClass() {
		return this.getClass();
	}

}
