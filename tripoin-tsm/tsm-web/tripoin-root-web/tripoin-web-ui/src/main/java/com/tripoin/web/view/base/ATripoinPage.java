package com.tripoin.web.view.base;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.web.common.EReportUIConstant;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.common.ReportUtil;
import com.tripoin.web.view.base.container.ATripoinDataReport;
import com.tripoin.web.view.base.container.ATripoinMenuItemGridDefault;
import com.tripoin.web.view.base.container.ATripoinNotification;
import com.tripoin.web.view.base.container.ATripoinPageable;
import com.tripoin.web.view.base.container.GridContainer;
import com.tripoin.web.view.base.container.ASearchContainer;
import com.tripoin.web.view.base.container.TitleContainer;
import com.tripoin.web.view.base.container.event.AMenuItemGridEventDefault;
import com.tripoin.web.view.exception.TripoinViewException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ResourceReference;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
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
	private boolean isFieldReset = false;
	private ITripoinPage tripoinPage;
	private String msg;

	protected CommonComponent commonComponent = new CommonComponent();
	private TitleContainer titleContainer;
	private ASearchContainer searchContainer;
	private List<Component> searchComponents;
	private GridContainer gridContainer;
	private BeanItemContainer<T> dataBeanContainer = getBeanDataContainer();
	
	protected ATripoinDataReport tripoinDataReport = new ATripoinDataReport() {
		@Override
		protected ResourceReference setResourceReport(String name, StreamResource resource) {
			setResource(name, resource);			
			return ResourceReference.create(resource, ATripoinPage.this, name);
		}
	};
	protected ATripoinNotification tripoinNotification = new ATripoinNotification() {
		@Override
		protected int delayMiliSecond() {
			return NOTIFICATION_TIME;
		}
	};
	protected ATripoinMenuItemGridDefault<T> tripoinMenuItemGridDefault;
	protected AMenuItemGridEventDefault<T> menuItemGridEventDefault;
	protected ATripoinPageable<T> tripoinPageable;

	@PostConstruct
	protected void init() throws Exception {
		initTitle();
		initSearch();
		initGrid();

        this.setMargin(true);
        this.addStyleName("tripoin-custom-screen");
	}

	private void initTitle() {
		titleContainer = new TitleContainer(new Label(getPageTitle()));
		this.commonComponent.setTitleContainer(titleContainer);
		addComponent(this.commonComponent.getTitleContainer());
	}

	private void initSearch() {
		if ((searchComponents = designSearchComponents()) != null) {
			searchContainer = new ASearchContainer() {
				private static final long serialVersionUID = -9075849116444347844L;
				@Override
				public List<Component> getSearchComponents() {
					return searchComponents;
				}
			};
			searchContainer.getParam().getOkButton().setCaption(getOkButtonCaption());
			searchContainer.getParam().getOkButton().addClickListener(new ClickListener() {
				private static final long serialVersionUID = 6601057432872302615L;
				@Override
				public void buttonClick(ClickEvent event) {
					isFieldReset = false;
					tripoinPageable.refreshPageable();
					menuItemGridEventDefault.setIndexSelected(null);
				}
			});
			searchContainer.getParam().getCancelButton().setCaption(getCancelButtonCaption());
			searchContainer.getParam().getCancelButton().addClickListener(new ClickListener() {
				private static final long serialVersionUID = 6601057432872302615L;
				@Override
				public void buttonClick(ClickEvent event) {
					isFieldReset = true;
					tripoinPageable.getGeneralPagingTransferObject().setPositionPage(1);
					tripoinPageable.refreshPageable();
					menuItemGridEventDefault.setIndexSelected(null);
				}
			});
			this.commonComponent.setSearchContainer(searchContainer);
			addComponent(this.commonComponent.getSearchContainer());
			searchComponents = null;
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
		generalPagingTransferObject = getALlDatasService(generalPagingTransferObject, searchContainer.getDataField(isFieldReset));
		if(generalPagingTransferObject.getDatas() != null) {
			dataBeanContainer.removeAllItems();
			dataBeanContainer.addAll(generalPagingTransferObject.getDatas());	
		}
		if(removeFieldContainerProperty() != null){
			for(Object property : removeFieldContainerProperty())
				dataBeanContainer.removeContainerProperty(property);	
		}
		if(addNestedFieldContainerProperty() != null){
			for(Object property : addNestedFieldContainerProperty())
				dataBeanContainer.addNestedContainerProperty((String)property);
		}
		commonComponent.getGridContainer().getParam().getGrid().getSelectionModel().reset();
		commonComponent.getGridContainer().getParam().getGrid().setContainerDataSource(dataBeanContainer);
		commonComponent.getGridContainer().getParam().getGrid().setColumnOrder(getFieldContainerPropertyHeader());
		for(String key : getColumnAlias().keySet())
			commonComponent.getGridContainer().getParam().getGrid().getColumn(key).setHeaderCaption(getColumnAlias().get(key));
		commonComponent.getGridContainer().getParam().getGrid().setFrozenColumnCount(2);
		isFieldReset = false;
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
				return new Command() {
					private static final long serialVersionUID = 5989159535771225427L;
					@Override
					public void menuSelected(MenuItem selectedItem) {
						UI.getCurrent().getNavigator().navigateTo(afterGridClickNavigate());
					}
				};
			}
			@Override
			protected Command doDelete() {
				return new Command() {
					private static final long serialVersionUID = -6262114274661510612L;
					@Override
		            public void menuSelected(MenuItem selectedItem) {
						if(tripoinMenuItemGridDefault.getDataObjectSelect() != null && tripoinMenuItemGridDefault.getDataObjectSelect().size() > 0){
							try {
								GeneralPagingTransferObject<T> response = doDeleteService(tripoinMenuItemGridDefault.getDataObjectSelect());
								if(EResponseCode.RC_USED.getResponseCode().equals(response.getResponseCode()))
									throw new Exception();
								tripoinPageable.refreshPageable();
							} catch (Exception e) {
								tripoinNotification.show("Error Delete", "Some data already being used");
							}
						}
		            }
		        };
			}
		};
		menuItemGridEventDefault = new AMenuItemGridEventDefault<T>() {
			@Override
			protected Grid getGrid() {
				return gridContainer.getParam().getGrid();
			}
			@Override
			protected ATripoinMenuItemGridDefault<T> getTripoinMenuItemGridDefault() {
				return tripoinMenuItemGridDefault;
			}
			@Override
			protected Object[] getFieldHeader() {
				return getFieldContainerPropertyHeader();
			}
			@Override
			protected String getClickNavigate() {
				return afterGridClickNavigate();
			}
			@Override
			protected boolean enabledCreate() {
				return isEnabledCreate();
			}
			@Override
			protected boolean enabledDelete() {
				return isEnabledDelete();
			}
			@Override
			protected boolean enabledExport() {
				return isEnabledExport();
			}
		};
	}
	
	protected abstract List<Component> designSearchComponents();
	
	protected abstract GeneralPagingTransferObject<T> getALlDatasService(GeneralPagingTransferObject<T> generalPagingTransferObject, Map<String, Object> searchPanelDatas);
	
	protected abstract GeneralPagingTransferObject<T> doDeleteService(List<T> dataObjectSelect);

	protected abstract BeanItemContainer<T> getBeanDataContainer();
	
	protected abstract Object[] getFieldContainerPropertyHeader();
	
	protected abstract Object[] addNestedFieldContainerProperty();
	
	protected abstract Object[] removeFieldContainerProperty();
	
	protected abstract Map<String, String> getColumnAlias();
	
	protected abstract boolean isSetMenuGrid();
	
	protected abstract String getPageTitle();

	protected abstract String afterGridClickNavigate();

	protected abstract String reportJasperNameSelected();

	protected abstract String reportJasperNameAll();

	protected abstract String reportNameSelected();

	protected abstract String reportNameAll();

	protected abstract Class<? extends ATripoinPage<T>> getViewClass();
	
	protected boolean isEnabledCreate() {
		return true;
	}
	
	protected boolean isEnabledDelete() {
		return true;
	}
	
	protected boolean isEnabledExport() {
		return true;
	}

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
	public void enter(ViewChangeEvent event) {
		if(EWebUIConstant.NAVIGATE_AFTER_FORM.toString().equalsIgnoreCase(event.getParameters())){
			tripoinPageable.refreshPageable();
			if(menuItemGridEventDefault.getIndexSelected()!=null && dataBeanContainer!=null)
				gridContainer.getParam().getGrid().select(dataBeanContainer.getIdByIndex(menuItemGridEventDefault.getIndexSelected()));	
		}else if(EWebUIConstant.NAVIGATE_AFTER_SUBMIT.toString().equalsIgnoreCase(event.getParameters())){
			tripoinPageable.getGeneralPagingTransferObject().setPositionPage(1);
			tripoinPageable.refreshPageable();
			if(menuItemGridEventDefault.getIndexSelected()!=null && dataBeanContainer!=null)
				gridContainer.getParam().getGrid().select(dataBeanContainer.getIdByIndex(menuItemGridEventDefault.getIndexSelected()));
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {}

}
