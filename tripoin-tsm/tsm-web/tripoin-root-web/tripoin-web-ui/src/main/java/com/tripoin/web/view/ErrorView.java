package com.tripoin.web.view;

import javax.annotation.PostConstruct;

import com.tripoin.web.servlet.VaadinView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
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
@VaadinView(value = ErrorView.VIEW_NAME, cached = false)
public class ErrorView extends VerticalLayout implements View {

	private static final long serialVersionUID = 6392094992266168555L;
	public static final String VIEW_NAME = "errorView";
	public static final String DESCRIPTION = "The server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, invalid request message framing, or deceptive request routing).";
	private Label descriptionLabel = new Label();
	private Panel errorInformationPanel = new Panel("Error Infromation");
	
	public void setDescription(String description) {
		this.descriptionLabel.setValue(description);
	}
	
	@PostConstruct
    public void init() {
		setMargin(true);
		setSpacing(true);        
        errorInformationPanel = new Panel("Error Infromation");
        errorInformationPanel.setStyleName("bold");
        errorInformationPanel.setIcon(FontAwesome.STACK_OVERFLOW);
        errorInformationPanel.setWidth("100%");
        addComponent(errorInformationPanel);
        setComponentAlignment(errorInformationPanel, Alignment.TOP_CENTER);
        VerticalLayout descriptionLayout = new VerticalLayout();
        descriptionLayout.setSpacing(true);
        descriptionLayout.setMargin(true);
        descriptionLabel.setValue(DESCRIPTION);
        descriptionLabel.setSizeFull();
        descriptionLayout.addComponent(descriptionLabel);
        HorizontalLayout helpUsLayout = new HorizontalLayout();
        helpUsLayout.setSpacing(true);
        Label contactLabel = new Label("Please Contact Us : ");
        Link url = new Link("tripoin, inc.", new ExternalResource("http://www.tripoin.co.id"));
        url.setIcon(FontAwesome.SUPPORT);
        helpUsLayout.addComponents(contactLabel, url);
        descriptionLayout.addComponent(helpUsLayout);
        errorInformationPanel.setContent(descriptionLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
}
