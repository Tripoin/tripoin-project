package com.tripoin.web.view.menu;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class CustomMenuLayout extends CssLayout implements View {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1704822489790265905L;

	public CustomMenuLayout getMenu() {
        addStyleName("large-icons");

        final Label logo = new Label("Va");
        logo.setSizeUndefined();
        logo.setPrimaryStyleName("valo-menu-logo");
        addComponent(logo);

        Button b = new Button("Reference <span class=\"valo-menu-badge\">3</span>");
        b.setIcon(FontAwesome.TH_LIST);
        b.setPrimaryStyleName("valo-menu-item");
        b.addStyleName("selected");
        b.setHtmlContentAllowed(true);
        addComponent(b);

        b = new Button("API");
        b.setIcon(FontAwesome.BOOK);
        b.setPrimaryStyleName("valo-menu-item");
        addComponent(b);

        b = new Button("Examples <span class=\"valo-menu-badge\">12</span>");
        b.setIcon(FontAwesome.TABLE);
        b.setPrimaryStyleName("valo-menu-item");
        b.setHtmlContentAllowed(true);
        addComponent(b);
        return this;
	}
	
	@PostConstruct
	public void init(){}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
