package com.tripoin.web.view.page.masterdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IOccupationService;
import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.base.ATripoinPage;
import com.tripoin.web.view.page.masterdata.occupation.DataOccupationManageView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;

@Component
@Scope("prototype")
@VaadinView(value = ExamplePage.BEAN_NAME, cached = true)
public class ExamplePage extends ATripoinPage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3718621115290540326L;
	private Object[] gridHeader = new Object[]{"name", "remarks", "createdBy", "createdIP", "createdTime", 
    		"createdPlatform", "modifiedBy", "modifiedIP", "modifiedTime", "modifiedPlatform"};
	public static final String BEAN_NAME = "dataProjectView";
	public static final String PAGE_NAME = "Example Page";
	private BeanItemContainer<OccupationData> occupationContainer = new BeanItemContainer<>(OccupationData.class);	
	private OccupationTransferObject occupationTransferObjectSearch = new OccupationTransferObject();
	private OccupationTransferObject occupationTransferObject;
	private List<OccupationData> occupationDatasSelect;
	private Map<String, Object> findOccupationData;

	@Autowired
	private IOccupationService occupationService;
	
	@PostConstruct
	public void init() {
		initComponent();
		initEvent();
	}

	@Override
	protected ArrayList<com.vaadin.ui.Component> getSearchPanelComponents() {
		ArrayList<com.vaadin.ui.Component> searchPanelComponents = new  ArrayList<com.vaadin.ui.Component>();
		TextField tfParam1 = new TextField("Param1");
		ComboBox cbParam2 = new ComboBox("Param2");
		TextField tfParam3 = new TextField("Param3");
		searchPanelComponents.add(tfParam1);
		searchPanelComponents.add(cbParam2);
		searchPanelComponents.add(tfParam3);
		return searchPanelComponents;
	}

	@Override
	protected GeneralPagingTransferObject constructBeanContainer(GeneralPagingTransferObject generalPagingTransferObject) {
    	occupationTransferObjectSearch.setPositionPage(generalPagingTransferObject.getPositionPage());
    	occupationTransferObjectSearch.setRowPerPage(EWebUIConstant.ROW_PER_PAGE.getOperatorInt());
    	occupationTransferObjectSearch.setFindOccupationData(findOccupationData);
    	occupationTransferObject = occupationService.getAllOccupationDatas(occupationTransferObjectSearch);
		occupationContainer.removeAllItems();
    	occupationContainer.addAll(occupationTransferObject.getOccupationDatas());
    	occupationContainer.removeContainerProperty("id");
    	occupationContainer.removeContainerProperty("status");
    	occupationContainer.removeContainerProperty("code");
    	commonComponent.getGridContainer().getParam().getGrid().getSelectionModel().reset();
    	commonComponent.getGridContainer().getParam().getGrid().setContainerDataSource(occupationContainer);
    	commonComponent.getGridContainer().getParam().getGrid().setColumnOrder(gridHeader);
    	commonComponent.getGridContainer().getParam().getGrid().getColumn("name").setHeaderCaption("Occupation Name");
    	commonComponent.getGridContainer().getParam().getGrid().getColumn("remarks").setHeaderCaption("Description");
    	commonComponent.getGridContainer().getParam().getGrid().getColumn("createdIP").setHeaderCaption("Created IP Address");
    	commonComponent.getGridContainer().getParam().getGrid().getColumn("modifiedIP").setHeaderCaption("Modified IP Address");
    	commonComponent.getGridContainer().getParam().getGrid().setFrozenColumnCount(2);
        findOccupationData = null;
        occupationTransferObject.setOccupationDatas(null);
        return occupationTransferObject;
	}

	@Override
	protected void deleteSelectionEvent(List<Object> dataObjectSelect) {
		for(Object object : dataObjectSelect)
			occupationDatasSelect.add((OccupationData)object);
		OccupationTransferObject occupationTransferObject = occupationService.deleteOccupation(occupationDatasSelect, VaadinServlet.getCurrent().getServletContext());
		occupationDatasSelect = null;
		if("2".equals(occupationTransferObject.getResponseCode())){
			String listOccupation = "Occupation : ";
			for(OccupationData occupationData : occupationTransferObject.getOccupationDatas())
				listOccupation = listOccupation.concat(occupationData.getName()).concat(", ");
			listOccupation = listOccupation.concat("#END-OCCUPATION#").replace(", #END-OCCUPATION#", "");
			tripoinNotification.show("Error Data Occupation", "Some Occupation data already being used\n".concat(listOccupation));
		}
	}

	@Override
	protected String getPageTitle() {
		return PAGE_NAME;
	}

	@Override
	protected String getGridClickNavigate() {
		return DataOccupationManageView.BEAN_NAME;
	}

	@Override
	protected Object[] getGridHeader() {
		return this.gridHeader;
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
	protected Class<? extends ATripoinPage> getViewClass() {
		return this.getClass();
	}
	
}
