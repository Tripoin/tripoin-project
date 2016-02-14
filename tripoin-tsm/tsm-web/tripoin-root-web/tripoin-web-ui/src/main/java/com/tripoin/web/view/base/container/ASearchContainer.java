package com.tripoin.web.view.base.container;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.tripoin.web.view.base.ITripoinComponent;
import com.tripoin.web.view.base.container.component.SearchPanel;
import com.tripoin.web.view.exception.TripoinViewException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;

public abstract class ASearchContainer extends FormLayout implements ITripoinComponent<SearchPanel, ASearchContainer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1575878452013068877L;
	@SuppressWarnings("rawtypes")
	private Map<String, AbstractField> searchContainerComponents = new HashMap<String, AbstractField>();
	private SearchPanel searchPanel = new SearchPanel();
	private ASearchContainer searchContainer;
	private String msg;
	
	@SuppressWarnings("rawtypes")
	public ASearchContainer() {
		Label section = new Label();
		section.addStyleName("h3");
		section.addStyleName("colored");
		section.setWidth("100%");
		this.addComponent(section);
		
		searchPanel.setSearcPanelComponents(getComponents());
		for (String key : this.getParam().getSearcPanelComponents().keySet()) {
			AbstractField component = this.getParam().getSearcPanelComponents().get(key);
			component.addStyleName("small");
			component.setWidth("50%");
			this.addComponent(component);
			this.searchContainerComponents.put(key, component);
		}
		
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
	
	@SuppressWarnings("rawtypes")
	public abstract Map<String, AbstractField> getComponents();

	@SuppressWarnings("rawtypes")
	public Map<String, AbstractField> getSearchContainerComponents() {
		return this.searchContainerComponents;
	}

	public String getMsg() {
		return msg;
	}
	
	@Value("${searchcontainer.getresult.error}")
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public SearchPanel getParam() {
		return this.searchPanel;
	}
	
	@Override
	public void setParam(SearchPanel param) {
		this.searchPanel = param;
	}

	@Override
	public void setResult(ASearchContainer result) {
		this.searchContainer = result;	
	}

	@Override
	public ASearchContainer getResult() throws Exception{
		if(this.searchContainer!=null){
			return searchContainer;
		}else{
			throw new TripoinViewException(msg);
		}
	}

}