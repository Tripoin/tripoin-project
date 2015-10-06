package com.tripoin.web.view.home;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.web.servlet.VaadinView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.Version;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = HomeView.VIEW_NAME, cached = true)
public class HomeView extends VerticalLayout implements View {

	private static final long serialVersionUID = 3293749140955725830L;
	public static final String VIEW_NAME = "";

    public HomeView() {
        setMargin(true);
        addStyleName("content-common");
        Label h1 = new Label("Tripoin Home");
        h1.addStyleName("h1");
        addComponent(h1);
        VerticalLayout row = new VerticalLayout();
        row.setWidth("100%");
        row.setSpacing(true);
        addComponent(row);

        row.addComponent(loadingIndicators());
    }

    private Panel loadingIndicators() {
        Panel p = new Panel("Information");
        final VerticalLayout content = new VerticalLayout();
        p.setContent(content);
        p.setIcon(FontAwesome.HOME);
        content.setSpacing(true);
        content.setMargin(true);
        content.addComponent(new Label(" This application is using Vaadin " + Version.getFullVersion()));
        Link urlTripoin = new Link("tripoin. inc,", new ExternalResource("http://www.tripoin.net"));
        urlTripoin.setIcon(FontAwesome.LINK);
        content.addComponent(urlTripoin);
        return p;
    }
    @Override
    public void enter(ViewChangeEvent event) {
    	
    }

}