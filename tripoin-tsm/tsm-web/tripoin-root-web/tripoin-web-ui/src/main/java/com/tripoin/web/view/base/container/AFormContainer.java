package com.tripoin.web.view.base.container;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.tripoin.web.view.base.ITripoinComponent;
import com.tripoin.web.view.base.container.component.FormPanel;
import com.tripoin.web.view.exception.TripoinViewException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;

public abstract class AFormContainer extends FormLayout implements ITripoinComponent<FormPanel, AFormContainer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1575878452013068877L;
	@SuppressWarnings("rawtypes")
	private Map<String, AbstractField> formContainerComponents = new HashMap<String, AbstractField>();
	private FormPanel formPanel = new FormPanel();
	private AFormContainer searchContainer;
	private String msg;
	
	@SuppressWarnings("rawtypes")
	public AFormContainer() {
		Label section = new Label();
		section.addStyleName("h3");
		section.addStyleName("colored");
		section.setWidth("100%");
		this.addComponent(section);
		
		formPanel.setFormPanelComponents(getFormComponents());
		for (String key : this.getParam().getFormPanelComponents().keySet()) {
			AbstractField component = this.getParam().getFormPanelComponents().get(key);
			component.addStyleName("small");
			component.setWidth("60%");
			this.addComponent(component);
			this.formContainerComponents.put(key, component);
		}
		
		this.getParam().getFooterSearch().setSpacing(true);
		this.getParam().getOkButton().addStyleName("primary");
		this.getParam().getOkButton().addStyleName("small");
		this.getParam().getOkButton().setClickShortcut(ShortcutAction.KeyCode.ENTER);
		this.getParam().getCancelButton().addStyleName("small");
		this.getParam().getCancelButton().setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
		this.addComponent(getParam().getFooterSearch());

		this.setStyleName("tripoin-custom-form");
		this.setMargin(false);        
	}
	
	@SuppressWarnings("rawtypes")
	protected abstract Map<String, AbstractField> getFormComponents();

	@SuppressWarnings("rawtypes")
	public Map<String, AbstractField> getFormContainerComponents() {
		return this.formContainerComponents;
	}

	public Map<String, Object> doOk() {
		Map<String, Object> searchPanelDatas = new HashMap<String, Object>();
		for (String key : getFormComponents().keySet()) {
			if(formContainerComponents.get(key).getValue() != null && !((String)formContainerComponents.get(key).getValue()).isEmpty()){
				searchPanelDatas.put(key, formContainerComponents.get(key).getValue());
			}
		}
		return searchPanelDatas;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> doCancel() {
		for (String key : searchContainer.getFormComponents().keySet()) {
			if(getFormComponents().get(key).getValue() != null){
				if(formContainerComponents.get(key) instanceof AbstractSelect)
					formContainerComponents.get(key).setValue(null);
				else
					formContainerComponents.get(key).setValue("");
			}
		}
		return null;
	}

	public String getMsg() {
		return msg;
	}
	
	@Value("${searchcontainer.getresult.error}")
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
		this.searchContainer = result;	
	}

	@Override
	public AFormContainer getResult() throws Exception{
		if(this.searchContainer!=null){
			return searchContainer;
		}else{
			throw new TripoinViewException(msg);
		}
	}

}
