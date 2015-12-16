package com.tripoin.web.view.page.masterdata;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.EmployeeData;
import com.tripoin.web.service.IEmployeeService;
import com.tripoin.web.servlet.VaadinView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "dataEmployeeView", cached = true)
public class DataEmployeeView extends VerticalLayout implements View {

	private static final long serialVersionUID = -4592518571070450190L;
	
	@Autowired
	private IEmployeeService employeeService;
	
	private BeanItemContainer<EmployeeData> employeeContainer = new BeanItemContainer<>(EmployeeData.class);	
	private Grid grid;
	private Object[] headerGrid = new Object[]{"nik", "profileData.name", "occupationData.name", "employeeDataParent.profileData.name"};

	@PostConstruct
	public void init() throws Exception {        
        setMargin(true);
        addStyleName("tripoin-custom-screen");
        HorizontalLayout row = new HorizontalLayout();
        row.setMargin(false);
        row.setWidth("100%");
        final FormLayout formTitle = new FormLayout();
        formTitle.setMargin(false);
        formTitle.addStyleName("light");        
        Label title = new Label("Data Employee");
        title.addStyleName("h1");
        formTitle.addComponent(title);        
        row.addComponent(formTitle); 
        addComponent(row);       

        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.setWidth("100%");
        com.vaadin.ui.Component groupSearch = searchContent();
        contentLayout.addComponent(groupSearch); 
        
        CssLayout layout = new CssLayout();
        layout.addStyleName("card");
        HorizontalLayout panelCaption = new HorizontalLayout();
        panelCaption.addStyleName("v-panel-caption");
        panelCaption.setWidth("100%");
        MenuBar dropdown = new MenuBar();
        dropdown.setAutoOpen(true);
        dropdown.addStyleName("borderless");
        dropdown.addStyleName("small");
        MenuItem addItem = dropdown.addItem("", FontAwesome.COG, null);
        addItem.setStyleName("icon-only");
        addItem.addItem("Create", new Command() {
        	private static final long serialVersionUID = 4272237366468831374L;
        	@Override
            public void menuSelected(MenuItem selectedItem) {
        		// TODO
        		
            }
        });
        addItem.addItem("Export", null).setEnabled(false);
        addItem.addSeparator();
        addItem.addItem("Delete", FontAwesome.TRASH_O, null).setEnabled(false);
        panelCaption.addComponent(dropdown);
        panelCaption.setExpandRatio(dropdown, 1);
        MenuBar menuBarPaging = getPaging();
        panelCaption.addComponent(menuBarPaging);

        layout.addComponent(panelCaption);
        layout.setWidth("100%");
        
        if(grid == null){
        	grid = new Grid();
        	List<EmployeeData> employeeDatas = employeeService.getAllEmployeeDatas();
        	employeeContainer.removeAllItems();
        	employeeContainer.addAll(employeeDatas);
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
						// TODO
					}
				}
			});
            grid.addSelectionListener(new SelectionListener() {
				private static final long serialVersionUID = -6491823805538480108L;
				@Override
				public void select(SelectionEvent event) {
					List<EmployeeData> listEmployeeDatas = new ArrayList<EmployeeData>();
					for(Object object : event.getSelected()){
						listEmployeeDatas.add((EmployeeData)object);
					}
				}
			});
            grid.setReadOnly(true);
            grid.setEditorEnabled(false);
            grid.setHeaderVisible(true);
            grid.setFrozenColumnCount(2);
            grid.setSelectionMode(SelectionMode.MULTI);
            grid.setSizeUndefined();
            grid.setWidth("100%");
            grid.addStyleName("small");
            layout.addComponent(grid);
        }
        contentLayout.addComponent(layout);        
        addComponent(contentLayout);
    }
	
	private static MenuBar getPaging() {
        MenuBar menubarPaging = new MenuBar();
        menubarPaging.addStyleName("borderless");
        menubarPaging.addStyleName("small");
        menubarPaging.setAutoOpen(true);
        
        // TODO
        menubarPaging.addItem("", FontAwesome.BACKWARD, null);
        menubarPaging.addItem("", FontAwesome.CARET_LEFT, null);
        menubarPaging.addItem("3", null);
        menubarPaging.addItem("4", null);
        MenuItem currentPageItem = menubarPaging.addItem("5", null);
        currentPageItem.setCheckable(true);
        currentPageItem.setChecked(true);
        menubarPaging.addItem("6", null);
        menubarPaging.addItem("7", null);
        menubarPaging.addItem("", FontAwesome.CARET_RIGHT, null);
        menubarPaging.addItem("", FontAwesome.FORWARD, null);
        //

        return menubarPaging;
    }
	
	private com.vaadin.ui.Component searchContent(){
		FormLayout groupSearch = new FormLayout();
		groupSearch.setSpacing(true);
		groupSearch.setWidth("100%");
		groupSearch.setStyleName("tripoin-custom-form");
		Label section = new Label();
        section.addStyleName("h3");
        section.addStyleName("colored");
        section.setWidth("100%");
        groupSearch.addComponent(section);  
        TextField nikTF = new TextField("NIK");
        nikTF.addStyleName("small");
        nikTF.setWidth("50%");
        groupSearch.addComponent(nikTF);
        TextField nameTF = new TextField("Name");
        nameTF.addStyleName("small");
        nameTF.setWidth("50%");
        groupSearch.addComponent(nameTF);
        TextField occupationTF = new TextField("Occupation");
        occupationTF.addStyleName("small");
        occupationTF.setWidth("50%");
        groupSearch.addComponent(occupationTF);
        TextField supervisorTF = new TextField("Supervisor");
        supervisorTF.addStyleName("small");
        supervisorTF.setWidth("50%");
        groupSearch.addComponent(supervisorTF);        
        Button search = new Button("Search");
        search.addStyleName("primary");
        search.addStyleName("small");
        search.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        groupSearch.addComponent(search);
		return groupSearch;
	}

    @Override
    public void enter(ViewChangeEvent event) {

    }
    
}
