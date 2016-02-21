package com.tripoin.web.view.page.masterdata.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.EmployeeTransferObject.EnumFieldEmployee;
import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IDataLoadStarted;
import com.tripoin.web.service.IEmployeeService;
import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.base.ATripoinPage;
import com.tripoin.web.view.base.ITripoinConstantComponent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = DataEmployeeView.BEAN_NAME, cached = true)
public class DataEmployeeView extends ATripoinPage<EmployeeData> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -960436667387354091L;
	public static final String BEAN_NAME = "dataEmployeeView";
	private EmployeeTransferObject employeeTransferObjectSearch = new EmployeeTransferObject();
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IDataLoadStarted dataLoadStarted;

	@Override
	protected List<com.vaadin.ui.Component> designSearchComponents() {
		List<com.vaadin.ui.Component> component = new ArrayList<com.vaadin.ui.Component>();
		TextField nikTextField = new TextField("NIK");
		nikTextField.setId(EnumFieldEmployee.NIK_EMPLOYE.toString());
        nikTextField.addStyleName("small");
        nikTextField.setWidth("50%");
		TextField nameTextField = new TextField("Name");
		nameTextField.setId(EnumFieldEmployee.NAME_EMPLOYE.toString());
        nameTextField.addStyleName("small");
        nameTextField.setWidth("50%");
        ComboBox occupationComboBox = new ComboBox("Occupation");
		occupationComboBox.setId(EnumFieldEmployee.OCCUPATION.toString());
        occupationComboBox.setContainerDataSource(dataLoadStarted.getOccupationContainer(VaadinServlet.getCurrent().getServletContext()));
        occupationComboBox.setItemCaptionMode(ItemCaptionMode.ITEM);
        occupationComboBox.setInputPrompt("Select Occupation");
        occupationComboBox.addStyleName("small");
        occupationComboBox.setTextInputAllowed(false);
        occupationComboBox.setNullSelectionAllowed(false);
        occupationComboBox.setWidth("50%");
        TextField supervisorTextField = new TextField("Supervisor");
        supervisorTextField.setId(EnumFieldEmployee.NAME_PARENT_EMPLOYE.toString());
        supervisorTextField.addStyleName("small");
        supervisorTextField.setWidth("50%");
		component.add(nikTextField);
		component.add(nameTextField);
		component.add(occupationComboBox);
		component.add(supervisorTextField);
		return component;
	}

	@Override
	protected GeneralPagingTransferObject<EmployeeData> getALlDatasService(GeneralPagingTransferObject<EmployeeData> generalPagingTransferObject, Map<String, Object> searchPanelDatas) {
		if(searchPanelDatas != null && !searchPanelDatas.isEmpty()){
			if(searchPanelDatas.containsKey(EnumFieldEmployee.OCCUPATION.toString())){
				System.out.println(searchPanelDatas.get(EnumFieldEmployee.OCCUPATION.toString()));
				OccupationData occupationData = (OccupationData)searchPanelDatas.get(EnumFieldEmployee.OCCUPATION.toString());
				searchPanelDatas.put(EnumFieldEmployee.OCCUPATION_CODE.toString(), occupationData.getName());
			}
		}
		employeeTransferObjectSearch.setPositionPage(generalPagingTransferObject.getPositionPage());
		employeeTransferObjectSearch.setRowPerPage(EWebUIConstant.ROW_PER_PAGE.getOperatorInt());
		employeeTransferObjectSearch.setFindEmployeeData(searchPanelDatas);
	    return employeeService.getAllEmployeeDatas(employeeTransferObjectSearch);
	}

	@Override
	protected GeneralPagingTransferObject<EmployeeData> doDeleteService(List<EmployeeData> dataObjectSelect) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BeanItemContainer<EmployeeData> getBeanDataContainer() {
		return new BeanItemContainer<>(EmployeeData.class);
	}

	@Override
	protected Object[] getFieldContainerPropertyHeader() {
		return new Object[]{"nik", "profileData.name", "occupationData.name", "employeeDataParent.profileData.name"};
	}

	@Override
	protected Object[] addNestedFieldContainerProperty() {
		return new Object[]{"profileData.name","occupationData.name","employeeDataParent.profileData.name"};
	}
	
	@Override
	protected Object[] removeFieldContainerProperty() {
		return new String[]{"id","code","status","remarks","createdBy","createdIP","createdTime","createdPlatform","modifiedBy","modifiedIP","modifiedTime","modifiedPlatform","profileData","occupationData","employeeDataParent"};
	}

	@Override
	protected Map<String, String> getColumnAlias() {
		Map<String, String> columns = new HashMap<String, String>();
		columns.put("nik", "NIK");
		columns.put("profileData.name", "Name");
		columns.put("occupationData.name", "Name");
		columns.put("employeeDataParent.profileData.name", "Supervisor");
		return columns;
	}

	@Override
	protected boolean isSetMenuGrid() {
		return true;
	}
	
	@Override
    public void enter(ViewChangeEvent event) {
		super.enter(event);
		if(event.getOldView() instanceof DataEmployeeManageView){
			DataEmployeeManageView oldView = (DataEmployeeManageView)event.getOldView();
			if(ITripoinConstantComponent.Button.SAVE.equals(oldView.okButtonCaption()))
		        this.commonComponent.getSearchContainer().getDataField(false);
		}
    }

	@Override
	protected String getPageTitle() {
		return "Data Employee";
	}

	@Override
	protected String afterGridClickNavigate() {
		return DataEmployeeManageView.BEAN_NAME;
	}

	@Override
	protected String reportJasperNameSelected() {
		return "Employee.jasper";
	}

	@Override
	protected String reportJasperNameAll() {
		return "EmployeeAll.jasper";
	}

	@Override
	protected String reportNameSelected() {
		return "Report-Employee";
	}

	@Override
	protected String reportNameAll() {
		return "Report-Employee-All";
	}

	@Override
	protected Class<? extends ATripoinPage<EmployeeData>> getViewClass() {
		return this.getClass();
	}
	
}
