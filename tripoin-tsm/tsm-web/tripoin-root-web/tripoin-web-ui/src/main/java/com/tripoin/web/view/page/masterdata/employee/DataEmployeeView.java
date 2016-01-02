package com.tripoin.web.view.page.masterdata.employee;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.EmployeeTransferObject.EnumFieldEmployee;
import com.tripoin.web.common.EWebSessionConstant;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IDataLoadStarted;
import com.tripoin.web.service.IEmployeeService;
import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.ABaseGridView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TextField;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = DataEmployeeView.BEAN_NAME, cached = true)
public class DataEmployeeView extends ABaseGridView {

	private static final long serialVersionUID = -4592518571070450190L;
	public static final String BEAN_NAME = "dataEmployeeView";
	public static final String PAGE_NAME = "Data Employee";
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IDataLoadStarted dataLoadStarted;	
	
	private BeanItemContainer<EmployeeData> employeeContainer = new BeanItemContainer<>(EmployeeData.class);
	private Object[] headerGrid = new Object[]{"nik", "profileData.name", "occupationData.name", "employeeDataParent.profileData.name"};
	private TextField nikTextField;
	private TextField nameTextField;
    private ComboBox occupationComboBox;
    private TextField supervisorTextField;
	private EmployeeTransferObject employeeTransferObjectSearch = new EmployeeTransferObject();
	private EmployeeTransferObject employeeTransferObject;
	private Map<String, Object> findEmployeeData;

	@PostConstruct
	public void init() throws Exception {        
		super.init(PAGE_NAME);
    }
	
	@Override
	protected void constructDataContainer(){
    	grid.getSelectionModel().reset();
    	if(nikTextField.getValue() != null && !nikTextField.getValue().isEmpty()){
    		if(findEmployeeData==null)findEmployeeData = new HashMap<String, Object>();
    		findEmployeeData.put(EnumFieldEmployee.NIK_EMPLOYE.toString(), nikTextField.getValue());
    	}
    	if(nameTextField.getValue() != null && !nameTextField.getValue().isEmpty()){
    		if(findEmployeeData==null)findEmployeeData = new HashMap<String, Object>();
    		findEmployeeData.put(EnumFieldEmployee.NAME_EMPLOYE.toString(), nameTextField.getValue());
    	}
    	if(occupationComboBox.getValue() != null){
    		if(findEmployeeData==null)findEmployeeData = new HashMap<String, Object>();
    		findEmployeeData.put(EnumFieldEmployee.OCCUPATION_EMPLOYE.toString(), ((OccupationData)occupationComboBox.getValue()).getName());
    	}
    	if(supervisorTextField.getValue() != null && !supervisorTextField.getValue().isEmpty()){
    		if(findEmployeeData==null)findEmployeeData = new HashMap<String, Object>();
    		findEmployeeData.put(EnumFieldEmployee.NAME_PARENT_EMPLOYE.toString(), supervisorTextField.getValue());
    	}
    	employeeTransferObjectSearch.setPositionPage(getPositionPage());
    	employeeTransferObjectSearch.setRowPerPage(EWebUIConstant.ROW_PER_PAGE.getOperatorInt());
    	employeeTransferObjectSearch.setFindEmployeeData(findEmployeeData);
    	employeeTransferObject = employeeService.getAllEmployeeDatas(employeeTransferObjectSearch);
    	setPositionPage(employeeTransferObject.getPositionPage());
    	setTotalPage(employeeTransferObject.getTotalPage());
    	employeeContainer.removeAllItems();
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
	protected void setItemMenuGrid(){        
        itemMenuGrid.addItem("Create", new Command() {
        	private static final long serialVersionUID = 4272237366468831374L;
        	@Override
            public void menuSelected(MenuItem selectedItem) {
        		UI.getCurrent().getNavigator().navigateTo(DataEmployeeManageView.BEAN_NAME);
            }
        });
        itemMenuGrid.addItem("Export", null).setEnabled(false);
        itemMenuGrid.addSeparator();
        itemMenuGrid.addItem(EWebUIConstant.BUTTON_DELETE.toString(), FontAwesome.TRASH_O, null).setEnabled(false);
	}
	
	@Override
	protected void setLayoutGrid(){
		grid.setColumnOrder(headerGrid);
        grid.getColumn("nik").setHeaderCaption("NIK");
        grid.getColumn("profileData.name").setHeaderCaption("Name");
        grid.getColumn("occupationData.name").setHeaderCaption("Occupation");
        grid.getColumn("employeeDataParent.profileData.name").setHeaderCaption("Supervisor");
        grid.addItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = -2614893307330224109L;
			@Override
			public void itemClick(ItemClickEvent event) {
				boolean isSelectEdited = false;
				for(Object object : headerGrid){
					if(object.equals(event.getPropertyId())){
						grid.getSelectionModel().reset();
						grid.select(event.getItemId());
						isSelectEdited = true;
						break;
					}
				}
				if(isSelectEdited){
					isSelectEdited = false;
					VaadinSession.getCurrent().getSession().setAttribute(EWebSessionConstant.SESSION_EMPLOYEE_DATA.toString(), (EmployeeData)event.getItemId());					
					UI.getCurrent().getNavigator().navigateTo(DataEmployeeManageView.BEAN_NAME);
				}
			}
		});
        grid.addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = -6491823805538480108L;
			@Override
			public void select(SelectionEvent event) {
				
			}
		});
	}
	
	@Override
	protected FormLayout searchContent(){
		FormLayout groupSearch = new FormLayout();
		Label section = new Label();
        groupSearch.addComponent(section);
        section.addStyleName("h3");
        section.addStyleName("colored");
        section.setWidth("100%");  
        nikTextField = new TextField("NIK");
        groupSearch.addComponent(nikTextField);
        nikTextField.addStyleName("small");
        nikTextField.setWidth("50%");
        nameTextField = new TextField("Name");
        groupSearch.addComponent(nameTextField);
        nameTextField.addStyleName("small");
        nameTextField.setWidth("50%");
        occupationComboBox = new ComboBox("Occupation");
        groupSearch.addComponent(occupationComboBox);
        occupationComboBox.setContainerDataSource(dataLoadStarted.getOccupationContainer());
        occupationComboBox.setItemCaptionMode(ItemCaptionMode.ITEM);
        occupationComboBox.setInputPrompt("Select Occupation");
        occupationComboBox.addStyleName("small");
        occupationComboBox.setTextInputAllowed(false);
        occupationComboBox.setNullSelectionAllowed(false);
        occupationComboBox.setWidth("50%");
        supervisorTextField = new TextField("Supervisor");
        groupSearch.addComponent(supervisorTextField);
        supervisorTextField.addStyleName("small");
        supervisorTextField.setWidth("50%");   
        ClickListener eventSearchClick = new ClickListener() {
			private static final long serialVersionUID = 4757500485569771186L;
			@Override
			public void buttonClick(ClickEvent event) {
				if("Search".equals(event.getButton().getCaption())){					
					constructDataContainer();
			        getPaging();					
				}else{
			        nikTextField.setValue("");
			        nameTextField.setValue("");
			        occupationComboBox.setValue(null);
			        supervisorTextField.setValue("");
			        setPositionPage(1);
			        constructDataContainer();
			        getPaging();
				}
			}
		};  
		HorizontalLayout footerSearch = new HorizontalLayout();
        groupSearch.addComponent(footerSearch);
		footerSearch.setSpacing(true);
        Button search = new Button("Search");
		footerSearch.addComponent(search);
        search.addStyleName("primary");
        search.addStyleName("small");
        search.addClickListener(eventSearchClick);
        search.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        Button reset = new Button("Reset");
		footerSearch.addComponent(reset);
        reset.addStyleName("small");
        reset.addClickListener(eventSearchClick);
        reset.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
		return groupSearch;
	}

    @Override
    public void enter(ViewChangeEvent event) {

    }

	@Override
	public void buttonClick(ClickEvent event) {
		
	}
    
}
