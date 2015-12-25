package com.tripoin.web.view.page.masterdata.occupation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.web.common.EWebSessionConstant;
import com.tripoin.web.common.EWebUIConstant;
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
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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
@VaadinView(value = DataOccupationView.BEAN_NAME, cached = true)
public class DataOccupationView extends VerticalLayout implements View {

	private static final long serialVersionUID = -4592518571070450190L;
	public static final String BEAN_NAME = "dataOccupationView";
	
	@Autowired
	private IOccupationService occupationService;
	
	private BeanItemContainer<OccupationData> occupationContainer = new BeanItemContainer<>(OccupationData.class);	
	private Grid grid = new Grid();
	private Object[] headerGrid = new Object[]{"name", "remarks", "createdBy", "createdIP", "createdTime", 
    		"createdPlatform", "modifiedBy", "modifiedIP", "modifiedTime", "modifiedPlatform"};
    private Notification notification = new Notification("");
    private MenuBar menuBarPaging;
    private HorizontalLayout panelCaption;
    private TextField nameOccupationSearch;
	private OccupationTransferObject occupationTransferObjectSearch = new OccupationTransferObject();
	private OccupationTransferObject occupationTransferObject;
	private List<OccupationData> occupationDatasSelect;
	private OccupationData findOccupationData;
	private Integer positionPage;
	private Integer totalPage;

	@PostConstruct
	public void init() throws Exception {        
        setMargin(true);
        addStyleName("tripoin-custom-screen");
        HorizontalLayout row = new HorizontalLayout();
        addComponent(row);
        row.setMargin(false);
        row.setWidth("100%");
        final FormLayout formTitle = new FormLayout();       
        row.addComponent(formTitle);
        formTitle.setMargin(false);
        formTitle.addStyleName("light");        
        Label title = new Label("Data Occupation");
        formTitle.addComponent(title); 
        title.addStyleName("h1");
                
        final FormLayout groupSearch = searchContent();
		addComponent(groupSearch);
        groupSearch.setStyleName("tripoin-custom-form");
        groupSearch.setMargin(new MarginInfo(false, false, true, false));
		groupSearch.setSpacing(true);
		groupSearch.setWidth("100%");
        
        VerticalLayout contentLayout = new VerticalLayout();      
        addComponent(contentLayout);
        contentLayout.setWidth("100%"); 
        CssLayout layout = new CssLayout();
        contentLayout.addComponent(layout); 
        layout.addStyleName("card");
        layout.setWidth("100%");
        panelCaption = new HorizontalLayout();
        layout.addComponent(panelCaption);
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
        addItem.addItem(EWebUIConstant.BUTTON_DELETE.toString(), FontAwesome.TRASH_O, new Command() {
			private static final long serialVersionUID = -6262114274661510612L;
			@Override
            public void menuSelected(MenuItem selectedItem) {
				if(occupationDatasSelect != null && occupationDatasSelect.size() > 0){
					OccupationTransferObject occupationTransferObject = occupationService.deleteOccupation(occupationDatasSelect);
					occupationDatasSelect = null;
		        	grid.getSelectionModel().reset();
			        constructDataContainer();
			        getPaging();
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
            }
        });
        panelCaption.addComponent(dropdown);
        panelCaption.setExpandRatio(dropdown, 1);
        menuBarPaging = new MenuBar();
        panelCaption.addComponent(menuBarPaging);
        menuBarPaging.addStyleName("borderless");
        menuBarPaging.addStyleName("small");
        menuBarPaging.setAutoOpen(true);
        constructDataContainer();
        getPaging();

        layout.addComponent(grid);
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
					VaadinSession.getCurrent().getSession().setAttribute(EWebSessionConstant.SESSION_OCUPATION_DATA.toString(), (OccupationData)event.getItemId());					
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
        
		notification.setStyleName("system closable");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(10000);
    }
	
	private void constructDataContainer(){
    	grid.getSelectionModel().reset();
		if(nameOccupationSearch.getValue() != null && !nameOccupationSearch.getValue().isEmpty()){
			findOccupationData = new OccupationData();
			findOccupationData.setName(nameOccupationSearch.getValue());
		}
		occupationTransferObjectSearch.setPositionPage(positionPage);
		occupationTransferObjectSearch.setRowPerPage(EWebUIConstant.ROW_PER_PAGE.getInt());
		occupationTransferObjectSearch.setFindOccupationData(findOccupationData);
        occupationTransferObject = occupationService.getAllOccupationDatas(occupationTransferObjectSearch);
        positionPage = occupationTransferObject.getPositionPage();
        totalPage = occupationTransferObject.getTotalPage();
		occupationContainer.removeAllItems();
    	occupationContainer.addAll(occupationTransferObject.getOccupationDatas());
    	occupationContainer.removeContainerProperty("id");
    	occupationContainer.removeContainerProperty("status");
    	occupationContainer.removeContainerProperty("code");
    	grid.setContainerDataSource(occupationContainer);
    	occupationTransferObject = null;		
	}
	
	private void getPaging() {
        menuBarPaging.removeItems();
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
    				if(positionPage>0){
    			        constructDataContainer();
    			        getPaging();
    				}    					    				
    			}
    		};
            Integer posMin = positionPage-(new Double(EWebUIConstant.BUTTON_PAGING.getInt()/2).intValue());
            if(posMin<1) posMin = 1; 
            Integer posMax = positionPage+(new Double(EWebUIConstant.BUTTON_PAGING.getInt()/2).intValue());
            if(posMax>totalPage) posMax = totalPage; 
            if(positionPage>1){
            	if(posMin>1) menuBarPaging.addItem("", FontAwesome.BACKWARD, eventPageClick);
                menuBarPaging.addItem("", FontAwesome.CARET_LEFT, eventPageClick);        	
            }      
            for(Integer i=posMin; i<=posMax; i++){
            	if(i==positionPage){
            		MenuItem currentPageItem = menuBarPaging.addItem(i.toString(), eventPageClick);
                    currentPageItem.setCheckable(true);
                    currentPageItem.setChecked(true);
            	}else menuBarPaging.addItem(i.toString(), eventPageClick);
            } 
            if(positionPage<totalPage){
                menuBarPaging.addItem("", FontAwesome.CARET_RIGHT, eventPageClick);
            	if(posMax<totalPage) menuBarPaging.addItem("", FontAwesome.FORWARD, eventPageClick);        	
            }
        }
    }
	
	private FormLayout searchContent(){
		FormLayout groupSearch = new FormLayout();
		Label section = new Label();
        groupSearch.addComponent(section);
        section.addStyleName("h3");
        section.addStyleName("colored");
        section.setWidth("100%");
        nameOccupationSearch = new TextField("Name");
        groupSearch.addComponent(nameOccupationSearch); 
        nameOccupationSearch.addStyleName("small");
        nameOccupationSearch.setWidth("50%");    
		ClickListener eventSearchClick = new ClickListener() {
			private static final long serialVersionUID = -825124355144374247L;
			@Override
			public void buttonClick(ClickEvent event) {
				if("Search".equals(event.getButton().getCaption())){
					if(nameOccupationSearch.getValue() != null && !nameOccupationSearch.getValue().isEmpty()){
						constructDataContainer();
				        getPaging();
					}					
				}else{
			        if(VaadinSession.getCurrent().getSession().getAttribute(EWebSessionConstant.SESSION_OCUPATION_POSITION_PAGE.toString()) != null)
			        	VaadinSession.getCurrent().getSession().removeAttribute(EWebSessionConstant.SESSION_OCUPATION_POSITION_PAGE.toString());
			        if(VaadinSession.getCurrent().getSession().getAttribute(EWebSessionConstant.SESSION_OCUPATION_DATA_SEARCH.toString()) != null)			        	
			        	VaadinSession.getCurrent().getSession().removeAttribute(EWebSessionConstant.SESSION_OCUPATION_DATA_SEARCH.toString());
			        positionPage = 1;
			        findOccupationData = null;
			        nameOccupationSearch.setValue("");
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
		if(event.getOldView() instanceof DataOccupationManageView){
			DataOccupationManageView oldView = (DataOccupationManageView)event.getOldView();
			if(EWebUIConstant.BUTTON_SAVE.toString().equals(oldView.getSubmit().getCaption())){
		        constructDataContainer();
		        getPaging();				
			}
		}
    }

}
