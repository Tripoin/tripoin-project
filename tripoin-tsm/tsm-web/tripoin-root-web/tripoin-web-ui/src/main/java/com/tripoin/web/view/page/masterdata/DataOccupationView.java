package com.tripoin.web.view.page.masterdata;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.OccupationData;
import com.tripoin.web.service.IOccupationService;
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
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "dataOccupationView", cached = true)
public class DataOccupationView extends VerticalLayout implements View {

	private static final long serialVersionUID = -4592518571070450190L;
	
	@Autowired
	private IOccupationService occupationService;
	
	private BeanItemContainer<OccupationData> occupationContainer = new BeanItemContainer<>(OccupationData.class);	
	private Grid grid;
	private Object[] headerGrid = new Object[]{"name", "remarks", "createdBy", "createdIP", "createdTime", 
    		"createdPlatform", "modifiedBy", "modifiedIP", "modifiedTime", "modifiedPlatform"};

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
        Label title = new Label("Data Occupation");
        title.addStyleName("h1");
        formTitle.addComponent(title);        
        row.addComponent(formTitle);
        addComponent(row);   
        
        final FormLayout groupSearch = searchContent();
        groupSearch.setStyleName("tripoin-custom-form");
        groupSearch.setMargin(new MarginInfo(false, false, true, false));
		groupSearch.setSpacing(true);
		groupSearch.setWidth("100%");
		addComponent(groupSearch);
		
        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.setWidth("100%");
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
        	List<OccupationData> occupationDatas = occupationService.getAllOccupationDatas();
        	occupationContainer.removeAllItems();
        	occupationContainer.addAll(occupationDatas);
        	occupationContainer.removeContainerProperty("id");
        	occupationContainer.removeContainerProperty("status");
        	occupationContainer.removeContainerProperty("code");
            grid.setContainerDataSource(occupationContainer);
            grid.setColumnOrder(headerGrid);
            grid.getColumn("name").setHeaderCaption("Occupation Name");
            grid.getColumn("remarks").setHeaderCaption("Description");
            grid.getColumn("createdIP").setHeaderCaption("Created IP Address");
            grid.getColumn("modifiedIP").setHeaderCaption("Modified IP Address");
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
					List<OccupationData> listOccupationDatas = new ArrayList<OccupationData>();
					for(Object object : event.getSelected()){
						listOccupationDatas.add((OccupationData)object);
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
	
	private FormLayout searchContent(){
		FormLayout groupSearch = new FormLayout();
		Label section = new Label();
        section.addStyleName("h3");
        section.addStyleName("colored");
        section.setWidth("100%");
        groupSearch.addComponent(section);
        TextField nameTF = new TextField("Name");
        nameTF.addStyleName("small");
        nameTF.setWidth("50%");
        groupSearch.addComponent(nameTF);        
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
