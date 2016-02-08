package com.tripoin.web.view.page.masterdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.MenuData;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.EmployeeTransferObject.EnumFieldEmployee;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IEmployeeService;
import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.base.ATripoinPage;
import com.tripoin.web.view.base.CommonComponent;
import com.tripoin.web.view.base.ITripoinPage;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TextField;

@Component
@Scope("prototype")
@VaadinView(value = ExamplePage.BEAN_NAME, cached = true)
public class ExamplePage extends ATripoinPage {
	private Object[] gridHeader = new Object[]{"nik", "profileData.name", "occupationData.name", "employeeDataParent.profileData.name"};
	private static final Logger logger=LoggerFactory.getLogger(ExamplePage.class);
	public static final String BEAN_NAME = "examplePage";
	public static final String PAGE_NAME = "Example Page";
	private int positionPage;
	private int totalPage;
	private BeanItemContainer<EmployeeData> employeeContainer = new BeanItemContainer<>(EmployeeData.class);
	private EmployeeTransferObject employeeTransferObjectSearch = new EmployeeTransferObject();
	private EmployeeTransferObject employeeTransferObject;
	private Map<String, Object> findEmployeeData;
	
	@Autowired
	private IEmployeeService employeeService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3718621115290540326L;
	@PostConstruct
	public void init() {
		logger.info("init");
		initComponent();
		initEvent();
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		

	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setParam(CommonComponent p_param) {
		// TODO Auto-generated method stub

	}

	@Override
	public CommonComponent getParam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResult(ITripoinPage p_result) {
		// TODO Auto-generated method stub

	}

	@Override
	public ITripoinPage getResult() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<? extends ATripoinPage> getViewClass() {
		
		return this.getClass();
	}

	

	@Override
	protected String getPageTitle() {
		
		return PAGE_NAME;
	}

	@Override
	protected String getCaptionNotification() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getDescriptionNotification() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ArrayList<MenuItem> getRightMenuItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ArrayList<MenuItem> getLeftMenuItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void constructBeanContainer() {
		Grid grid=gridContainer.getParam().getGrid();
		grid.getSelectionModel().reset();
		logger.info(grid.getColumns().toString());
    
    	employeeTransferObjectSearch.setPositionPage(getPositionPage());
    	employeeTransferObjectSearch.setRowPerPage(EWebUIConstant.ROW_PER_PAGE.getOperatorInt());
    	employeeTransferObjectSearch.setFindEmployeeData(findEmployeeData);
    	logger.info(employeeService.toString());
    	employeeTransferObject = employeeService.getAllEmployeeDatas(employeeTransferObjectSearch);
    	setPositionPage(employeeTransferObject.getPositionPage());
    	setTotalPage(employeeTransferObject.getTotalPage());
    	employeeContainer.removeAllItems();
    	logger.info(employeeTransferObject.toString());
    	employeeContainer.addAll(employeeTransferObject.getEmployeeDatas());
    	employeeContainer.removeContainerProperty("id");
    	employeeContainer.removeContainerProperty("code");
    	employeeContainer.removeContainerProperty("status");
    	employeeContainer.removeContainerProperty("remarks");
    	employeeContainer.removeContainerProperty("createdBy");
    	employeeContainer.removeContainerProperty("createdIP");
    	employeeContainer.removeContainerProperty("createdTime");
    	employeeContainer.removeContainerProperty("createdPlatform");
    	employeeContainer.removeContainerProperty("modifiedBy");
    	employeeContainer.removeContainerProperty("modifiedIP");
    	employeeContainer.removeContainerProperty("modifiedTime");
    	employeeContainer.removeContainerProperty("modifiedPlatform");
    	employeeContainer.removeContainerProperty("profileData");
    	employeeContainer.removeContainerProperty("occupationData");
    	employeeContainer.removeContainerProperty("employeeDataParent");
    	employeeContainer.addNestedContainerProperty("profileData.name");
    	employeeContainer.addNestedContainerProperty("occupationData.name");
    	employeeContainer.addNestedContainerProperty("employeeDataParent.profileData.name");
        grid.setContainerDataSource(employeeContainer);		
        employeeTransferObject = null;
        findEmployeeData = null;

	}

	@Override
	protected void setLayoutGrid() {

	}

	@Override
	protected Object[] setGridHeader() {
		return gridHeader;
	}

	public int getPositionPage() {
		return positionPage;
	}

	public void setPositionPage(int positionPage) {
		this.positionPage = positionPage;
	}

	
	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	protected ArrayList<com.vaadin.ui.Component> getSearchPanelComponents() {
		ArrayList<com.vaadin.ui.Component> searchPanelComponents=new  ArrayList<com.vaadin.ui.Component>();
		TextField tfParam1=new TextField("Param1");
		ComboBox cbParam2=new ComboBox("Param2");
		TextField tfParam3=new TextField("Param3");
		searchPanelComponents.add(tfParam1);
		searchPanelComponents.add(cbParam2);
		searchPanelComponents.add(tfParam3);
		return searchPanelComponents;
	}

}
