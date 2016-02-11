package com.tripoin.web.view.base;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.web.common.EReportUIConstant;
import com.tripoin.web.common.EWebSessionConstant;
import com.tripoin.web.common.ReportUtil;
import com.tripoin.web.view.base.container.GridContainer;
import com.tripoin.web.view.base.container.SearchContainer;
import com.tripoin.web.view.base.container.TitleContainer;
import com.tripoin.web.view.exception.TripoinViewException;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ResourceReference;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author Fadhil Paramanindo
 *
 * @param <HEADER>
 */
public abstract class ATripoinPage extends VerticalLayout implements View, ClickListener, ITripoinPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -195915325891958375L;
	protected Logger LOGGER = LoggerFactory.getLogger(getViewClass());
	
	@Autowired
	private ReportUtil reportUtil;

	private static final int NOTIFICATION_TIME = 7000;
	private ITripoinPage tripoinPage;
	private String msg;

	protected CommonComponent commonComponent = new CommonComponent();
	private TitleContainer titleContainer;
	private SearchContainer searchContainer;
	private GridContainer gridContainer;

	protected final ATripoinDataReport tripoinDataReport = new ATripoinDataReport() {
		@Override
		ResourceReference setResourceReport(String name, StreamResource resource) {
			setResource(name, resource);			
			return ResourceReference.create(resource, ATripoinPage.this, name);
		}
	};
	protected ATripoinNotification tripoinNotification = new ATripoinNotification("", "") {
		private static final long serialVersionUID = 1736547096660591645L;
		@Override
		protected int delayMiliSecond() {
			return NOTIFICATION_TIME;
		}
	};
	protected ATripoinMenuItemGrid tripoinMenuItemGrid;
	protected ATripoinPageable tripoinPageable;

	protected void initComponent() {
		initTitle();
		initSearch();
		initGrid();

        this.setMargin(true);
        this.addStyleName("tripoin-custom-screen");
	}

	public void initEvent() {
		gridClickEvent();
		if(isSetMenuGrid())
			gridSelectEvent();
	}

	private void initTitle() {
		titleContainer = new TitleContainer(new Label(getPageTitle()));
		this.commonComponent.setTitleContainer(titleContainer);
		addComponent(this.commonComponent.getTitleContainer());
	}

	private void initSearch() {
		if (getSearchPanelComponents() != null) {
			searchContainer = new SearchContainer() {
				private static final long serialVersionUID = -9075849116444347844L;
				@Override
				public ArrayList<Component> getComponents() {
					return getSearchPanelComponents();
				}
			};
			searchContainer.getParam().getOkButton().setCaption(getOkButtonCaption());
			searchContainer.getParam().getCancelButton().setCaption(getCancelButtonCaption());
			this.commonComponent.setSearchContainer(searchContainer);
			addComponent(this.commonComponent.getSearchContainer());
		}
	}

	private void initGrid() {
		gridContainer = new GridContainer();
		this.commonComponent.setGridContainer(gridContainer);
		addComponent(this.commonComponent.getGridContainer());
		tripoinPageable = new ATripoinPageable() {
			@Override
			public void refreshPageable() {
				this.setGeneralPagingTransferObject(constructBeanContainer(this.getGeneralPagingTransferObject()));
				this.initPaging();
			}
			@Override
			protected MenuBar getMenuBar() {
				return gridContainer.getParam().getMenuBarRight();
			}
		};
		if(isSetMenuGrid()){
			tripoinMenuItemGrid = new ATripoinMenuItemGrid() {
				@Override
				protected MenuBar getMenuBar() {
					return gridContainer.getParam().getMenuBarLeft();
				}
				@Override
				protected Command doGenerateReportAll() {
					return new Command() {
						private static final long serialVersionUID = 5989159535771225427L;
						@Override
						public void menuSelected(MenuItem selectedItem) {
							tripoinDataReport.exportStreamDataReport(reportUtil, null, reportJasperNameAll(), null, reportNameAll(), reportTypeAll());
						}
					};
				}
				@Override
				protected Command doGenerateReportSelected() {
					return new Command() {
						private static final long serialVersionUID = 5989159535771225427L;
						@Override
						public void menuSelected(MenuItem selectedItem) {
							tripoinDataReport.exportStreamDataReport(reportUtil, tripoinMenuItemGrid.getDataObjectSelect(), reportJasperNameSelected(), null, reportNameSelected(), reportTypeSelected());
						}
					};
				}
				@Override
				protected Command doCreate() {
					return null;
				}
				@Override
				protected Command doDelete() {
					return new Command() {
						private static final long serialVersionUID = -6262114274661510612L;
						@Override
			            public void menuSelected(MenuItem selectedItem) {
							if(tripoinMenuItemGrid.getDataObjectSelect() != null && tripoinMenuItemGrid.getDataObjectSelect().size() > 0){
								deleteSelectionEvent(tripoinMenuItemGrid.getDataObjectSelect());
								tripoinPageable.refreshPageable();
							}
			            }
			        };
				}
			};
		}
	}
	
	protected abstract ArrayList<Component> getSearchPanelComponents();

	protected abstract GeneralPagingTransferObject constructBeanContainer(GeneralPagingTransferObject generalPagingTransferObject);
	
	protected void gridClickEvent() {
		gridContainer.getParam().getGrid().addItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = -2614893307330224109L;
			@Override
			public void itemClick(ItemClickEvent event) {
				boolean isSelectEdited = false;
				for(Object object : getGridHeader()){
					if(object.equals(event.getPropertyId())){
						gridContainer.getParam().getGrid().getSelectionModel().reset();
						gridContainer.getParam().getGrid().select(event.getItemId());
						isSelectEdited = true; break;
					}
				}
				if(isSelectEdited){
					isSelectEdited = false;
					VaadinSession.getCurrent().getSession().setAttribute(EWebSessionConstant.SESSION_GRID_DATA.toString(), event.getItemId());					
					UI.getCurrent().getNavigator().navigateTo(getGridClickNavigate());
				}
			}
		});
	}
	
	protected void gridSelectEvent() {
		gridContainer.getParam().getGrid().addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = -6491823805538480108L;
			@Override
			public void select(SelectionEvent event) {
				if(event.getSelected().isEmpty()){
					if(tripoinMenuItemGrid.getMenuItemDelete().isEnabled())tripoinMenuItemGrid.getMenuItemDelete().setEnabled(false);
					if(tripoinMenuItemGrid.getMenuItemExportSelected().isEnabled())tripoinMenuItemGrid.getMenuItemExportSelected().setEnabled(false);
				}else{
					if(!tripoinMenuItemGrid.getMenuItemDelete().isEnabled())tripoinMenuItemGrid.getMenuItemDelete().setEnabled(true);
					if(!tripoinMenuItemGrid.getMenuItemExportSelected().isEnabled())tripoinMenuItemGrid.getMenuItemExportSelected().setEnabled(true);
				}		
				tripoinMenuItemGrid.getDataObjectSelect().clear();
				for(Object object : event.getSelected())tripoinMenuItemGrid.getDataObjectSelect().add(object);
			}
		});
	}
	
	protected abstract void deleteSelectionEvent(List<Object> dataObjectSelect);
	
	protected abstract String getPageTitle();

	protected abstract String getGridClickNavigate();
	
	protected abstract Object[] getGridHeader();
	
	protected abstract boolean isSetMenuGrid();

	protected abstract String reportJasperNameSelected();

	protected abstract String reportJasperNameAll();

	protected abstract String reportNameSelected();

	protected abstract String reportNameAll();

	protected abstract Class<? extends ATripoinPage> getViewClass();

	protected EReportUIConstant reportTypeSelected() {
		return EReportUIConstant.REPORT_PDF;
	}

	protected EReportUIConstant reportTypeAll() {
		return EReportUIConstant.REPORT_PDF;
	}
	
	protected  String getOkButtonCaption(){
		return ITripoinConstantComponent.Button.SEARCH;
	}
	
	protected  String getCancelButtonCaption(){
		return ITripoinConstantComponent.Button.RESET;
	}

	@Override
	public void setParam(CommonComponent param) {
		this.commonComponent = param;
	}

	@Override
	public CommonComponent getParam() {
		return this.commonComponent;
	}

	@Override
	public void setResult(ITripoinPage result) {
		this.tripoinPage = result;
	}

	@Override
	public ITripoinPage getResult() throws Exception {
		if (this.tripoinPage != null) {
			return tripoinPage;
		} else {
			throw new TripoinViewException(msg);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {}

	@Override
	public void buttonClick(ClickEvent event) {}

}
