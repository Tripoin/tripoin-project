package com.tripoin.web.view.page.masterdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.core.dto.OccupationTransferObject.EnumFieldOccupation;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IOccupationService;
import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.base.ATripoinPage;
import com.tripoin.web.view.base.ITripoinConstantComponent;
import com.tripoin.web.view.page.masterdata.occupation.DataOccupationManageView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.TextField;

@Component
@Scope("prototype")
@VaadinView(value = ExamplePage.BEAN_NAME, cached = true)
public class ExamplePage extends ATripoinPage<OccupationData> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3718621115290540326L;
	public static final String BEAN_NAME = "dataProjectView";
	private OccupationTransferObject occupationTransferObjectSearch = new OccupationTransferObject();

	@Autowired
	private IOccupationService occupationService;

	@SuppressWarnings("rawtypes")
	@Override
	protected Map<String, AbstractField> getSearchPanelComponents() {
		Map<String, AbstractField> searchPanelComponents = new  HashMap<String, AbstractField>();
		TextField occupationNameTextField = new TextField("Occupation Name");
		searchPanelComponents.put(EnumFieldOccupation.NAME_OCCUPATION.toString(), occupationNameTextField);
		return searchPanelComponents;
	}

	@Override
	protected GeneralPagingTransferObject<OccupationData> getALlDatasService(GeneralPagingTransferObject<OccupationData> generalPagingTransferObject) {
    	occupationTransferObjectSearch.setPositionPage(generalPagingTransferObject.getPositionPage());
    	occupationTransferObjectSearch.setRowPerPage(EWebUIConstant.ROW_PER_PAGE.getOperatorInt());
    	occupationTransferObjectSearch.setFindOccupationData(getSearchPanelDatas());
        return occupationService.getAllOccupationDatas(occupationTransferObjectSearch);
	}

	@Override
	protected GeneralPagingTransferObject<OccupationData> doDeleteService(List<OccupationData> dataObjectSelect) {
		return occupationService.deleteOccupation(dataObjectSelect, VaadinServlet.getCurrent().getServletContext());
	}
	
	@Override
	protected void initMenuItemGridDefault() {
		super.initMenuItemGridDefault();
		tripoinMenuItemGridDefault.getMenuItemCreate().setEnabled(false);
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
		if(event.getOldView() instanceof DataOccupationManageView){
			DataOccupationManageView oldView = (DataOccupationManageView)event.getOldView();
			if(ITripoinConstantComponent.Button.SAVE.equals(oldView.getSubmit().getCaption()))
		        doCancel();
		}
    }

	@Override
	protected String getPageTitle() {
		return "Example Page";
	}

	@Override
	protected String getGridClickNavigate() {
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
