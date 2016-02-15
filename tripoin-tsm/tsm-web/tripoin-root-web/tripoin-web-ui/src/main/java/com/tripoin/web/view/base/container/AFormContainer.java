package com.tripoin.web.view.base.container;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.tripoin.web.view.base.ITripoinComponent;
import com.tripoin.web.view.base.container.component.FormPanel;
import com.tripoin.web.view.exception.TripoinViewException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class AFormContainer extends FormLayout implements ITripoinComponent<FormPanel, AFormContainer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1575878452013068877L;
	private FormPanel formPanel = new FormPanel();
	private AFormContainer formContainer;
	private String msg;
	
	public AFormContainer() {
		Label section = new Label();
		section.addStyleName("h3");
		section.addStyleName("colored");
		section.setWidth("100%");
		this.addComponent(section);

		for(Component component : getFormComponent())
			this.addComponent(component);
		
		this.getParam().getFooterSearch().setSpacing(true);
		this.getParam().getOkButton().addStyleName("primary");
		this.getParam().getOkButton().addStyleName("small");
		this.getParam().getOkButton().setClickShortcut(ShortcutAction.KeyCode.ENTER);
		this.getParam().getCancelButton().addStyleName("small");
		this.getParam().getCancelButton().setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
		this.addComponent(getParam().getFooterSearch());

		this.setStyleName("tripoin-custom-form");
		this.setMargin(new MarginInfo(false, false, true, false));
		this.setSpacing(true);
		this.setWidth("100%");
	}
	
	protected abstract List<Component> getFormComponent();

	public Map<String, Object> getDataField(boolean isResetField) {
		return TripoinDataField.getDataField(this, isResetField);
	}

	public String getMsg() {
		return msg;
	}
	
	@Value("${formcontainer.getresult.error}")
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public FormPanel getParam() {
		return this.formPanel;
	}
	
	@Override
	public void setParam(FormPanel param) {
		this.formPanel = param;
	}

	@Override
	public void setResult(AFormContainer result) {
		this.formContainer = result;	
	}

	@Override
	public AFormContainer getResult() throws Exception{
		if(this.formContainer!=null){
			return formContainer;
		}else{
			throw new TripoinViewException(msg);
		}
	}

}