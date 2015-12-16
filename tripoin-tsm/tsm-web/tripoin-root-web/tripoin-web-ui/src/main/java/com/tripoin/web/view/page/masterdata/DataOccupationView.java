package com.tripoin.web.view.page.masterdata;

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
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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

	@PostConstruct
	public void init() throws Exception {        
        setMargin(true);
        addStyleName("tripoin-custom-screen");
        HorizontalLayout row = new HorizontalLayout();
        row.setMargin(false);
        row.setWidth("100%");
        addComponent(row);
        final FormLayout formTitle = new FormLayout();
        formTitle.setMargin(false);
        formTitle.addStyleName("light");        
        Label title = new Label("Data Occupation");
        title.addStyleName("h1");
        formTitle.addComponent(title);        
        row.addComponent(formTitle);        

        HorizontalLayout contentLayout = new HorizontalLayout();
        contentLayout.setWidth("100%");
        if(grid == null){
        	grid = new Grid();
        	List<OccupationData> occupationDatas = occupationService.getAllOccupationDatas();
        	occupationContainer.removeAllItems();
        	occupationContainer.addAll(occupationDatas);
        	occupationContainer.removeContainerProperty("id");
        	occupationContainer.removeContainerProperty("status");
        	occupationContainer.removeContainerProperty("code");
            grid.setContainerDataSource(occupationContainer);
            grid.setColumnOrder(new Object[]{"name", "remarks", "createdBy", "createdIP", "createdTime", 
            		"createdPlatform", "modifiedBy", "modifiedIP", "modifiedTime", "modifiedPlatform"});
            grid.getColumn("name").setHeaderCaption("Occupation Name");
            grid.getColumn("remarks").setHeaderCaption("Description");
            grid.getColumn("createdIP").setHeaderCaption("Created IP Address");
            grid.getColumn("modifiedIP").setHeaderCaption("Modified IP Address");
            grid.addItemClickListener(new ItemClickListener() {
				private static final long serialVersionUID = -2614893307330224109L;
				@Override
				public void itemClick(ItemClickEvent event) {
					if(event.isDoubleClick()){
						
					}
				}
			});
            grid.setEditorEnabled(false);
            grid.setHeaderVisible(true);
            grid.setFrozenColumnCount(2);
            grid.setSelectionMode(SelectionMode.MULTI);
            grid.setSizeUndefined();
            grid.setWidth("100%");
            grid.addStyleName("small");
            contentLayout.addComponent(grid);
        }
        addComponent(contentLayout);
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }
    
}
