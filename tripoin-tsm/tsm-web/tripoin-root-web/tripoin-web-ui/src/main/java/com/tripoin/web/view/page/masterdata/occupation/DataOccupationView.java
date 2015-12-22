package com.tripoin.web.view.page.masterdata.occupation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.core.pojo.Occupation;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IOccupationService;
import com.tripoin.web.service.IPaginationService;
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
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = DataOccupationView.BEAN_NAME, cached = false)
public class DataOccupationView extends VerticalLayout implements View {

	private static final long serialVersionUID = -4592518571070450190L;
	public static final String BEAN_NAME = "dataOccupationView";
	
	@Autowired
	private IOccupationService occupationService;
	
	@Autowired
	private IPaginationService paginationService;
	
	private BeanItemContainer<OccupationData> occupationContainer = new BeanItemContainer<>(OccupationData.class);	
	private Grid grid = new Grid();
	private Object[] headerGrid = new Object[]{"name", "remarks", "createdBy", "createdIP", "createdTime", 
    		"createdPlatform", "modifiedBy", "modifiedIP", "modifiedTime", "modifiedPlatform"};
    private Notification notification = new Notification("");
    private MenuBar menuBarPaging;
	private List<OccupationData> occupationDatasSelect;
	private OccupationTransferObject occupationTransferObject;
	private Integer positionPage;
	private Integer maxRow;
	private Integer minRow;
	private Integer totalPage;

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
        layout.setWidth("100%");
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
        		UI.getCurrent().getNavigator().navigateTo(DataOccupationManageView.BEAN_NAME);
            }
        });
        addItem.addItem("Export", null).setEnabled(false);
        addItem.addSeparator();
        addItem.addItem("Delete", FontAwesome.TRASH_O, new Command() {
			private static final long serialVersionUID = -6262114274661510612L;
			@Override
            public void menuSelected(MenuItem selectedItem) {
				OccupationTransferObject occupationTransferObject = occupationService.deleteOccupation(occupationDatasSelect);
	        	grid.getSelectionModel().reset();
	        	calculatePage();
		        constructDataContainer();
		        menuBarPaging = getPaging();	        	
				if("2".equals(occupationTransferObject.getResponseCode())){
					String listOccupation = "Occupation : ";
					for(OccupationData occupationData : occupationTransferObject.getOccupationDatas())
						listOccupation = listOccupation.concat(occupationData.getName()).concat(", ");
					listOccupation = listOccupation.concat("#END-OCCUPATION#").replace(", #END-OCCUPATION#", "");
		            notification.setCaption("Error Data Occupation");
					notification.setDescription("Some Occupation data already being used\n"
							.concat(listOccupation));
					notification.show(Page.getCurrent());
				}
            }
        });
        panelCaption.addComponent(dropdown);
        panelCaption.setExpandRatio(dropdown, 1);
        layout.addComponent(panelCaption);
        
        calculatePage();
        constructDataContainer();
        menuBarPaging = getPaging();
        panelCaption.addComponent(menuBarPaging);

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
					VaadinSession.getCurrent().getSession().setAttribute("occupationData", (OccupationData)event.getItemId());
	        		UI.getCurrent().getNavigator().navigateTo(DataOccupationManageView.BEAN_NAME);
				}
			}
		});
        grid.addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = -6491823805538480108L;
			@Override
			public void select(SelectionEvent event) {
				occupationDatasSelect = new ArrayList<OccupationData>();
				for(Object object : event.getSelected())
					occupationDatasSelect.add((OccupationData)object);
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
        contentLayout.addComponent(layout);        
        addComponent(contentLayout);
        
		notification.setStyleName("system closable");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(10000);
    }
	
	private void calculatePage(){
		try {
			GeneralPagingTransferObject generalPagingTransferObject = paginationService.getPagination(new GeneralPagingTransferObject(Occupation.TABLE_NAME));
			totalPage = new Double(generalPagingTransferObject.getTotalRow()/EWebUIConstant.ROW_PER_PAGE.getInt()).intValue();
	        if(generalPagingTransferObject.getTotalRow()%EWebUIConstant.ROW_PER_PAGE.getInt()>0)totalPage++;	
		} catch (Exception e) {
			totalPage = 1;
		}
		if(Page.getCurrent().getUriFragment().split("/").length>1){
	        String fragmentUriPage = Page.getCurrent().getUriFragment().split("/")[1].replaceAll(EWebUIConstant.REGEX_FRONT_CONTAINS_DIGIT.toString(), "");
	        try {
	        	if(fragmentUriPage.isEmpty()) throw new Exception();
				positionPage = Integer.parseInt(fragmentUriPage);
			} catch (Exception e) {
				positionPage = 1;
			}
		}else positionPage = 1;
        maxRow = positionPage * EWebUIConstant.ROW_PER_PAGE.getInt();
        minRow = maxRow - EWebUIConstant.ROW_PER_PAGE.getInt();
	}
	
	private void constructDataContainer(){
        occupationTransferObject = occupationService.getAllOccupationDatasPaging(minRow, maxRow);        
		occupationContainer.removeAllItems();
    	occupationContainer.addAll(occupationTransferObject.getOccupationDatas());
    	occupationContainer.removeContainerProperty("id");
    	occupationContainer.removeContainerProperty("status");
    	occupationContainer.removeContainerProperty("code");
    	grid.setContainerDataSource(occupationContainer);
    	occupationTransferObject = null;		
	}
	
	private MenuBar getPaging() {
        MenuBar menubarPaging = new MenuBar();
        menubarPaging.addStyleName("borderless");
        menubarPaging.addStyleName("small");
        menubarPaging.setAutoOpen(true);
        if(totalPage > 1){
    		Command eventPageClick = new Command() {
    			private static final long serialVersionUID = 2102926468458330518L;
    			@Override
    			public void menuSelected(MenuItem selectedItem) {
					selectedItem.setCheckable(true);
					selectedItem.setChecked(true);
    				if("".equals(selectedItem.getText()) || selectedItem.getText().isEmpty() || selectedItem.getText() == null){
    					if(FontAwesome.BACKWARD == selectedItem.getIcon())
    						positionPage = 1;
    					else if(FontAwesome.CARET_LEFT == selectedItem.getIcon()){
    						positionPage--;
    						if(positionPage<1)
    							positionPage = 1;
    					}else if(FontAwesome.CARET_RIGHT == selectedItem.getIcon()){
    						positionPage++;
    						if(positionPage>totalPage)
    							positionPage = totalPage;					
    					}else
    						positionPage = totalPage;	
    				}else{
    					try {
    						positionPage = Integer.parseInt(selectedItem.getText());
    					} catch (Exception e) {
    						positionPage = 1;
    					}
    				}
    				if(positionPage>0)
    					Page.getCurrent().setUriFragment("!".concat(BEAN_NAME).concat("/") + positionPage.toString(), true);    				
    			}
    		};
            Integer posMin = positionPage-(new Double(EWebUIConstant.BUTTON_PAGING.getInt()/2).intValue());
            if(posMin<1) posMin = 1; 
            Integer posMax = positionPage+(new Double(EWebUIConstant.BUTTON_PAGING.getInt()/2).intValue());
            if(posMax>totalPage) posMax = totalPage; 
            if(positionPage>1){
            	if(posMin>1) menubarPaging.addItem("", FontAwesome.BACKWARD, eventPageClick);
                menubarPaging.addItem("", FontAwesome.CARET_LEFT, eventPageClick);        	
            }      
            for(Integer i=posMin; i<=posMax; i++){
            	if(i==positionPage){
            		MenuItem currentPageItem = menubarPaging.addItem(i.toString(), eventPageClick);
                    currentPageItem.setCheckable(true);
                    currentPageItem.setChecked(true);
            	}else menubarPaging.addItem(i.toString(), eventPageClick);
            } 
            if(positionPage<totalPage){
                menubarPaging.addItem("", FontAwesome.CARET_RIGHT, eventPageClick);
            	if(posMax<totalPage) menubarPaging.addItem("", FontAwesome.FORWARD, eventPageClick);        	
            }
        }
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
