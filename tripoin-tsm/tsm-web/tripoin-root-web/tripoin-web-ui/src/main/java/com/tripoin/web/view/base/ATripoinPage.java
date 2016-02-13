package com.tripoin.web.view.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.vaadin.data.util.BeanItemContainer;
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
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractSelect;
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
public abstract class ATripoinPage<T> extends VerticalLayout implements View, ClickListener, ITripoinPage {

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
	private Map<String, Object> searchPanelDatas;
	private GridContainer gridContainer;
	private BeanItemContainer<T> dataBeanContainer = getBeanDataContainer();
	
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
	protected ATripoinMenuItemGridDefault<T> tripoinMenuItemGridDefault;
	protected ATripoinPageable<T> tripoinPageable;

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
				@SuppressWarnings("rawtypes")
				@Override
				public Map<String, AbstractField> getComponents() {
					return getSearchPanelComponents();
				}
			};
			searchContainer.getParam().getOkButton().setCaption(getOkButtonCaption());
			searchContainer.getParam().getOkButton().addClickListener(new ClickListener() {
				private static final long serialVersionUID = 6601057432872302615L;
				@Override
				public void buttonClick(ClickEvent event) {
					searchPanelDatas = new HashMap<String, Object>();
					for (String key : getSearchPanelComponents().keySet()) {
						if(searchContainer.getSearchContainerComponents().get(key).getValue() != null){
							searchPanelDatas.put(key, searchContainer.getSearchContainerComponents().get(key).getValue());
						}
					}
					tripoinPageable.refreshPageable();
				}
			});
			searchContainer.getParam().getCancelButton().setCaption(getCancelButtonCaption());
			searchContainer.getParam().getCancelButton().addClickListener(new ClickListener() {
				private static final long serialVersionUID = 6601057432872302615L;
				@SuppressWarnings("unchecked")
				@Override
				public void buttonClick(ClickEvent event) {
					for (String key : searchContainer.getComponents().keySet()) {
						if(searchContainer.getComponents().get(key).getValue() != null){
							if(searchContainer.getSearchContainerComponents().get(key) instanceof AbstractSelect)
								searchContainer.getSearchContainerComponents().get(key).setValue(null);
							else
								searchContainer.getSearchContainerComponents().get(key).setValue("");
							searchPanelDatas = null;
						}
					}
					tripoinPageable.refreshPageable();
				}
			});
			this.commonComponent.setSearchContainer(searchContainer);
			addComponent(this.commonComponent.getSearchContainer());
		}
	}

	private void initGrid() {
		gridContainer = new GridContainer();
		this.commonComponent.setGridContainer(gridContainer);
		addComponent(this.commonComponent.getGridContainer());
		tripoinPageable = new ATripoinPageable<T>() {
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
		if(isSetMenuGrid())
			initMenuItemGridDefault();
	}

	private GeneralPagingTransferObject<T> constructBeanContainer(GeneralPagingTransferObject<T> generalPagingTransferObject) {
		generalPagingTransferObject = getALlDatasService(generalPagingTransferObject);
		dataBeanContainer.removeAllItems();
		dataBeanContainer.addAll(generalPagingTransferObject.getDatas());
		for(Object property : removeFieldContainerProperty())
			dataBeanContainer.removeContainerProperty(property);
		commonComponent.getGridContainer().getParam().getGrid().getSelectionModel().reset();
		commonComponent.getGridContainer().getParam().getGrid().setContainerDataSource(dataBeanContainer);
		commonComponent.getGridContainer().getParam().getGrid().setColumnOrder(getFieldContainerPropertyHeader());
		for(String key : getColumnAlias().keySet())
			commonComponent.getGridContainer().getParam().getGrid().getColumn(key).setHeaderCaption(getColumnAlias().get(key));
		commonComponent.getGridContainer().getParam().getGrid().setFrozenColumnCount(2);
		return generalPagingTransferObject;
	}
	
	protected void initMenuItemGridDefault() {
		tripoinMenuItemGridDefault = new ATripoinMenuItemGridDefault<T>() {
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
						tripoinDataReport.exportStreamDataReport(reportUtil, tripoinMenuItemGridDefault.getDataObjectSelect(), reportJasperNameSelected(), null, reportNameSelected(), reportTypeSelected());
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
						if(tripoinMenuItemGridDefault.getDataObjectSelect() != null && tripoinMenuItemGridDefault.getDataObjectSelect().size() > 0){
							GeneralPagingTransferObject<T> response = doDeleteService(tripoinMenuItemGridDefault.getDataObjectSelect());
							if("2".equals(response.getResponseCode()))
								tripoinNotification.show("Error Data Delete", "Some data already being used");
							else
								tripoinPageable.refreshPageable();
						}
		            }
		        };
			}
		};
	}
	
	@SuppressWarnings("rawtypes")
	protected abstract Map<String, AbstractField> getSearchPanelComponents();

	protected Map<String, Object> getSearchPanelDatas() {
		return this.searchPanelDatas;
	}
	
	protected abstract GeneralPagingTransferObject<T> getALlDatasService(GeneralPagingTransferObject<T> generalPagingTransferObject);
	
	protected abstract GeneralPagingTransferObject<T> doDeleteService(List<T> dataObjectSelect);

	protected abstract BeanItemContainer<T> getBeanDataContainer();
	
	protected abstract Object[] getFieldContainerPropertyHeader();
	
	protected abstract Object[] removeFieldContainerProperty();
	
	protected abstract Map<String, String> getColumnAlias();
	
	protected abstract boolean isSetMenuGrid();
	
	protected void gridClickEvent() {
		gridContainer.getParam().getGrid().addItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = -2614893307330224109L;
			@Override
			public void itemClick(ItemClickEvent event) {
				boolean isSelectEdited = false;
				for(Object object : getFieldContainerPropertyHeader()){
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
			@SuppressWarnings("unchecked")
			@Override
			public void select(SelectionEvent event) {
				if(event.getSelected().isEmpty()){
					if(tripoinMenuItemGridDefault.getMenuItemDelete().isEnabled())tripoinMenuItemGridDefault.getMenuItemDelete().setEnabled(false);
					if(tripoinMenuItemGridDefault.getMenuItemExportSelected().isEnabled())tripoinMenuItemGridDefault.getMenuItemExportSelected().setEnabled(false);
				}else{
					if(!tripoinMenuItemGridDefault.getMenuItemDelete().isEnabled())tripoinMenuItemGridDefault.getMenuItemDelete().setEnabled(true);
					if(!tripoinMenuItemGridDefault.getMenuItemExportSelected().isEnabled())tripoinMenuItemGridDefault.getMenuItemExportSelected().setEnabled(true);
				}		
				tripoinMenuItemGridDefault.getDataObjectSelect().clear();
				for(Object object : event.getSelected())
					tripoinMenuItemGridDefault.getDataObjectSelect().add((T)object);
			}
		});
	}
	
	protected abstract String getPageTitle();

	protected abstract String getGridClickNavigate();

	protected abstract String reportJasperNameSelected();

	protected abstract String reportJasperNameAll();

	protected abstract String reportNameSelected();

	protected abstract String reportNameAll();

	protected abstract Class<? extends ATripoinPage<T>> getViewClass();

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
