package com.tripoin.web.view.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripoin.web.view.base.container.ATripoinNotification;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.util.ui.platform.IdentifierPlatform;
import com.tripoin.web.common.EWebSessionConstant;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.view.base.container.AFormContainer;
import com.tripoin.web.view.base.container.TitleContainer;
import com.tripoin.web.view.exception.TripoinViewException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class ATripoinForm<T> extends VerticalLayout implements View, ClickListener, ITripoinPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -195915325891958375L;
	protected Logger LOGGER = LoggerFactory.getLogger(getViewClass());

	private static final int NOTIFICATION_TIME = 7000;
	private boolean isFieldReset = false;
	private ITripoinPage tripoinPage;
	private String msg;

	protected CommonComponent commonComponent = new CommonComponent();
	private TitleContainer titleContainer;
	private AFormContainer formContainer;
	private Map<String, Object> formPanelDatas;

	private T dataOriginalGrid = null;
	protected IdentifierPlatform tripoinIdentifierPlatform = new IdentifierPlatform(Page.getCurrent().getWebBrowser());
	protected ATripoinNotification tripoinNotification = new ATripoinNotification("", "") {
		private static final long serialVersionUID = 1736547096660591645L;
		@Override
		protected int delayMiliSecond() {
			return NOTIFICATION_TIME;
		}
	};

	@PostConstruct
	protected void init() throws Exception {
		initTitle();
		initForm();

        this.setMargin(true);
        this.addStyleName("tripoin-custom-screen");
	}

	private void initTitle() {
		titleContainer = new TitleContainer(new Label(getPageTitle()));
		this.commonComponent.setTitleContainer(titleContainer);
		addComponent(this.commonComponent.getTitleContainer());
	}

	private void initForm() {
		formContainer = new AFormContainer() {
			private static final long serialVersionUID = -9075849116444347844L;
			@SuppressWarnings("unchecked")
			@Override
			public List<Component> getFormComponents() {
				if(VaadinSession.getCurrent().getSession().getAttribute(EWebSessionConstant.SESSION_GRID_DATA.toString()) == null){
					getParam().getOkButton().setCaption(getOkButtonCaption());
				}else{
					try {
						dataOriginalGrid = (T) VaadinSession.getCurrent().getSession().getAttribute(EWebSessionConstant.SESSION_GRID_DATA.toString());
						VaadinSession.getCurrent().getSession().removeAttribute(EWebSessionConstant.SESSION_GRID_DATA.toString());
						getParam().getOkButton().setCaption(getReOkButtonCaption());
					} catch (ClassCastException e) {
						getParam().getOkButton().setCaption(getOkButtonCaption());
					} catch (Exception e) {
						getParam().getOkButton().setCaption(getOkButtonCaption());
						e.printStackTrace();
					}
				}
				return designFormComponents(dataOriginalGrid);
			}
		};
		formContainer.getParam().getOkButton().addClickListener(new ClickListener() {
			private static final long serialVersionUID = 6601057432872302615L;
			@Override
			public void buttonClick(ClickEvent event) {
				formPanelDatas = formContainer.getDataField(isFieldReset);
				if(formPanelDatas != null){
					formPanelDatas.put(EWebUIConstant.IDENTIFIER_IP.toString(), tripoinIdentifierPlatform.getIPAddress());
					formPanelDatas.put(EWebUIConstant.IDENTIFIER_TIME.toString(), ParameterConstant.FORMAT_DEFAULT.format(new Date()));
					formPanelDatas.put(EWebUIConstant.IDENTIFIER_PLATFORM.toString(), tripoinIdentifierPlatform.getDevice().concat(" | ").concat(tripoinIdentifierPlatform.getOperatingSystem()).concat(" | ").concat(tripoinIdentifierPlatform.getBrowser()));
					pressOkButtonAllEvent(event);
				}else{
					tripoinNotification.show("Error Submit", "Data form not null!");
				}
			}
		});
		formContainer.getParam().getCancelButton().setCaption(getCancelButtonCaption());
		formContainer.getParam().getCancelButton().addClickListener(new ClickListener() {
			private static final long serialVersionUID = 6601057432872302615L;
			@Override
			public void buttonClick(ClickEvent event) {
				doCancelEvent();
			}
		});
		this.commonComponent.setFormContainer(formContainer);
		addComponent(this.commonComponent.getFormContainer());
	}
	
	protected abstract List<Component> designFormComponents(T dataGrid);
	
	protected abstract Map<String, ErrorMessage> validateErrorComponents(Map<String, Object> formPanelDatas, GeneralTransferObject generalTransferObject);

	protected Map<String, Object> getFormPanelDatas() {
		return this.formPanelDatas;
	}
	
	protected void pressOkButtonAllEvent(ClickEvent event) {
		Map<String, ErrorMessage> errorComponents = validateErrorComponents(formPanelDatas, null);
		if(errorComponents != null && !errorComponents.isEmpty()){
			formContainer.setErrorComponents(errorComponents);
		}else{
			GeneralTransferObject generalTransferObject;
			if(event.getButton().getCaption().equalsIgnoreCase(getOkButtonCaption()))
				generalTransferObject = doOkButtonEvent(formPanelDatas, dataOriginalGrid);
			else
				generalTransferObject = doReOkButtonEvent(formPanelDatas, dataOriginalGrid);
			errorComponents = validateErrorComponents(formPanelDatas, generalTransferObject);
			if(errorComponents != null && !errorComponents.isEmpty()){
				formContainer.setErrorComponents(errorComponents);
			}else
				UI.getCurrent().getNavigator().navigateTo(afterButtonClickNavigate());
		}
	}

	protected abstract GeneralTransferObject doOkButtonEvent(Map<String, Object> formPanelDatas, T dataOriginalGrid);

	protected abstract GeneralTransferObject doReOkButtonEvent(Map<String, Object> formPanelDatas, T dataOriginalGrid);
	
	protected void doCancelEvent() {
		UI.getCurrent().getNavigator().navigateTo(afterButtonClickNavigate());
	}
	
	protected abstract String getPageTitle();
	
	protected abstract String afterButtonClickNavigate();

	protected abstract Class<? extends ATripoinForm<T>> getViewClass();
	
	protected  String getOkButtonCaption(){
		return ITripoinConstantComponent.Button.SAVE;
	}
	
	protected  String getReOkButtonCaption(){
		return ITripoinConstantComponent.Button.UPDATE;
	}
	
	protected  String getCancelButtonCaption(){
		return ITripoinConstantComponent.Button.CANCEL;
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
