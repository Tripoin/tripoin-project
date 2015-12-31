package com.tripoin.web.view;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.MediaType;

import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.common.ReportUtil;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.ResourceReference;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class ABaseGridView extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = -570015212316455087L;
    private MenuBar menuBarPaging;
    private MenuBar menuBarGrid;
    private HorizontalLayout panelCaption;
    private static final Integer NOTIFICATION_TIME = 10000;
    private Integer positionPage;
    private Integer totalPage;
	protected Grid grid = new Grid();
    protected MenuItem itemMenuGrid;
    protected Notification notification;

	public void init(String PAGE_NAME) throws Exception {
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
        Label title = new Label(PAGE_NAME);
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
        
        menuBarGrid = new MenuBar();
        menuBarGrid.setAutoOpen(true);
        menuBarGrid.addStyleName("borderless");
        menuBarGrid.addStyleName("small");
        itemMenuGrid = menuBarGrid.addItem("", FontAwesome.COG, null);
        itemMenuGrid.setStyleName("icon-only");
        setItemMenuGrid();
        
        panelCaption.addComponent(menuBarGrid);
        panelCaption.setExpandRatio(menuBarGrid, 1);
        menuBarPaging = new MenuBar();
        panelCaption.addComponent(menuBarPaging);
        menuBarPaging.addStyleName("borderless");
        menuBarPaging.addStyleName("small");
        menuBarPaging.setAutoOpen(true);
        constructDataContainer();
        getPaging();
        layout.addComponent(grid);
        setLayoutGrid();
        grid.setReadOnly(true);
        grid.setEditorEnabled(false);
        grid.setHeaderVisible(true);
        grid.setFrozenColumnCount(2);
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.setSizeUndefined();
        grid.setWidth("100%");
        grid.addStyleName("small");
        
        if(notification == null){
        	notification = new Notification("");
    		notification.setStyleName("system closable");
            notification.setPosition(Position.BOTTOM_CENTER);
            notification.setDelayMsec(NOTIFICATION_TIME);
        }
    }
	
	protected void getPaging() {
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
            Integer posMin = positionPage-(new Double(EWebUIConstant.BUTTON_PAGING.getOperatorInt()/2).intValue());
            if(posMin<1) posMin = 1; 
            Integer posMax = positionPage+(new Double(EWebUIConstant.BUTTON_PAGING.getOperatorInt()/2).intValue());
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
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>exportStreamDataReport(data, "report.jasper", null, "Report-Monthly", EWebUIConstant.REPORT_PDF);</code><br>
	 * @param reportUtil
	 * @param data
	 * @param reportFilename
	 * @param params
	 * @param outputFilename
	 * @param typeFile
	 */
	protected void exportStreamDataReport(ReportUtil reportUtil, Collection<?> data, String reportFilename, Map<String, Object> params, String outputFilename, EWebUIConstant typeFile){
		outputFilename = outputFilename.concat("-").concat(UUID.randomUUID().toString()).concat(typeFile.toString());
		final StreamResource resource = reportUtil.exportStreamReport(data, reportFilename, params, outputFilename, typeFile);	    
    	if(resource != null){
			if(data != null && !data.isEmpty()){
				resource.setMIMEType(typeFile.getOperand());
		    	resource.getStream().setParameter("Content-Disposition", "attachment; filename=\"".concat(outputFilename).concat("\""));
		    	final Window window = new Window("Report Preview");
			    final Embedded embedded = new Embedded();
			    embedded.setType(2);
			    embedded.setSizeFull();
		    	embedded.setSource(resource);
			    window.setContent(embedded);
			    window.setModal(true);
			    window.center();
			    window.setWidth("90%");
			    window.setHeight("90%");
			    UI.getCurrent().addWindow(window);		
			}else{
				resource.setMIMEType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
				setResource("export", resource);			
				ResourceReference resourceReference = ResourceReference.create(resource, this, "export");
				Page.getCurrent().open(resourceReference.getURL(), "_blank", false);			
			}
		}
	}

	public Integer getPositionPage() {
		return positionPage;
	}

	public void setPositionPage(Integer positionPage) {
		this.positionPage = positionPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	protected abstract void constructDataContainer();
	
	protected abstract FormLayout searchContent();
	
	protected abstract void setItemMenuGrid();
	
	protected abstract void setLayoutGrid();
	
}
