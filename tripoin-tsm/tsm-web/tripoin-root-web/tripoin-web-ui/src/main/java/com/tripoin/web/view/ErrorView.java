package com.tripoin.web.view;

import javax.annotation.PostConstruct;

import com.tripoin.web.servlet.VaadinView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * View shown when trying to navigate to a view that does not exist using
 * {@link com.vaadin.navigator.Navigator}.
 * 
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@VaadinView(value = "errorView", cached = true)
@Theme("tripoin-valo")
public class ErrorView extends VerticalLayout implements View {

	private static final long serialVersionUID = 6392094992266168555L;
	private String description;

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ErrorView() {}
	
	@PostConstruct
    public void init() throws Exception {
		setMargin(true);
		setSpacing(true);        
        Panel p = new Panel("Error Infromation");
        p.setStyleName("bold");
        p.setIcon(FontAwesome.STACK_OVERFLOW);
        VerticalLayout right = new VerticalLayout();
        right.setSpacing(true);
        right.setMargin(true);
        p.setContent(right);
        p.setWidth("50%");
        Label descriptionLabel = new Label(description);
        right.addComponent(descriptionLabel);
        Label contactLabel = new Label("Please Contact Us : ");
        Link url = new Link("tripoin, inc.", new ExternalResource("http://www.tripoin.co.id"));
        url.setIcon(FontAwesome.SUPPORT);
        right.addComponents(contactLabel, url);
        this.addComponent(p);
        this.setComponentAlignment(p, Alignment.TOP_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
}
