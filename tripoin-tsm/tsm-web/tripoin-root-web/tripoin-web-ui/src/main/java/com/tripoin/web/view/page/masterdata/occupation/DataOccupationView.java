package com.tripoin.web.view.page.masterdata.occupation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.util.report.ReportsUtil;
import com.tripoin.web.common.EWebSessionConstant;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IOccupationService;
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
import com.vaadin.server.Page;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = DataOccupationView.BEAN_NAME, cached = true)
public class DataOccupationView extends ABaseGridView {

	private static final long serialVersionUID = -4592518571070450190L;
	public static final String BEAN_NAME = "dataOccupationView";
	public static final String PAGE_NAME = "Data Occupation";
	
	@Autowired
	private IOccupationService occupationService;
	
	private BeanItemContainer<OccupationData> occupationContainer = new BeanItemContainer<>(OccupationData.class);	
	private Object[] headerGrid = new Object[]{"name", "remarks", "createdBy", "createdIP", "createdTime", 
    		"createdPlatform", "modifiedBy", "modifiedIP", "modifiedTime", "modifiedPlatform"};
    private TextField nameOccupationSearch;
	private OccupationTransferObject occupationTransferObjectSearch = new OccupationTransferObject();
	private OccupationTransferObject occupationTransferObject;
	private List<OccupationData> occupationDatasSelect;
	private OccupationData findOccupationData;

	@PostConstruct
	public void init() throws Exception {  
		super.init(PAGE_NAME);
    }
	
	@Override
	protected void constructDataContainer(){
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
        findOccupationData = null;
	}
	
	@Override
	protected FormLayout searchContent(){
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
	protected void setItemMenuGrid(){        
        itemMenuGrid.addItem("Create", new Command() {
        	private static final long serialVersionUID = 4272237366468831374L;
        	@Override
            public void menuSelected(MenuItem selectedItem) {
        		UI.getCurrent().getNavigator().navigateTo(DataOccupationManageView.BEAN_NAME);
            }
        });
        /**
         * If ItemMenu not used
         * addItemMenuGrid.addItem("Export", null).setEnabled(false);
         */
        itemMenuGrid.addItem("Export", new Command() {
			private static final long serialVersionUID = 5989159535771225427L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				Map<String, Object> params = new HashMap<String, Object>();
				ReportsUtil reportUtil = ReportsUtil.getInstance();
				StreamResource resources = reportUtil.createPdfReport(occupationDatasSelect, "Occupation.jrxml", params, "Report-Occupation");
				
		        Window window = new Window();
			    window.setCaption("Preview PDF");
			    Embedded c = new Embedded("", resources);
			    c.setType(2);
			    c.setSizeFull();
			    resources.setMIMEType("application/pdf");
			    window.setContent(c);
			    window.setModal(true);
			    window.setWidth("90%");
			    window.setHeight("90%");
			    UI.getCurrent().addWindow(window);
			}
		});
        itemMenuGrid.addSeparator();
        itemMenuGrid.addItem(EWebUIConstant.BUTTON_DELETE.toString(), FontAwesome.TRASH_O, new Command() {
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
	}
	
	@Override
	protected void setLayoutGrid(){
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
	}

	@Override
    public void enter(ViewChangeEvent event) {
		if(event.getOldView() instanceof DataOccupationManageView){
			DataOccupationManageView oldView = (DataOccupationManageView)event.getOldView();
			if(EWebUIConstant.BUTTON_SAVE.toString().equals(oldView.getSubmit().getCaption())){
				positionPage = 1;
		        nameOccupationSearch.setValue("");
		        constructDataContainer();
		        getPaging();				
			}
		}
    }

	@Override
	public void buttonClick(ClickEvent event) {
		
	}

}
