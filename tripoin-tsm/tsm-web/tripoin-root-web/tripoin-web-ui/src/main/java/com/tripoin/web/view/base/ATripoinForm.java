package com.tripoin.web.view.base;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripoin.web.view.base.container.ATripoinNotification;
import com.tripoin.web.view.base.container.AFormContainer;
import com.tripoin.web.view.base.container.TitleContainer;
import com.tripoin.web.view.exception.TripoinViewException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class ATripoinForm extends VerticalLayout implements View, ClickListener, ITripoinPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -195915325891958375L;
	protected Logger LOGGER = LoggerFactory.getLogger(getViewClass());

	private static final int NOTIFICATION_TIME = 7000;
	private ITripoinPage tripoinPage;
	private String msg;

	protected CommonComponent commonComponent = new CommonComponent();
	private TitleContainer titleContainer;
	private AFormContainer formContainer;
	private Map<String, Object> formPanelDatas;
	
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
		if (designFormComponent() != null) {
			formContainer = new AFormContainer() {
				private static final long serialVersionUID = -9075849116444347844L;
				@Override
				public List<Component> getFormComponent() {
					return designFormComponent();
				}
			};
			formContainer.getParam().getOkButton().setCaption(getOkButtonCaption());
			formContainer.getParam().getOkButton().addClickListener(new ClickListener() {
				private static final long serialVersionUID = 6601057432872302615L;
				@Override
				public void buttonClick(ClickEvent event) {
					formPanelDatas = formContainer.getDataField(false);
					if(formPanelDatas != null){
						if(event.getButton().getCaption().equalsIgnoreCase(getOkButtonCaption())){
							doOkButtonEvent();
						}else{
							doReOkButtonEvent();
						}
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
	}
	
	protected abstract List<Component> designFormComponent();

	protected Map<String, Object> getFormPanelDatas() {
		return this.formPanelDatas;
	}

	protected void doOkButtonEvent() {
		
		UI.getCurrent().getNavigator().navigateTo(afterButtonClickNavigate());		
	}

	protected void doReOkButtonEvent() {
		
		UI.getCurrent().getNavigator().navigateTo(afterButtonClickNavigate());		
	}
	
	protected void doCancelEvent() {
		UI.getCurrent().getNavigator().navigateTo(afterButtonClickNavigate());
	}
	
	protected abstract String getPageTitle();
	
	protected abstract String afterButtonClickNavigate();

	protected abstract Class<? extends ATripoinForm> getViewClass();
	
	protected  String getReOkButtonCaption(){
		return ITripoinConstantComponent.Button.SAVE;
	}
	
	protected  String getOkButtonCaption(){
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
