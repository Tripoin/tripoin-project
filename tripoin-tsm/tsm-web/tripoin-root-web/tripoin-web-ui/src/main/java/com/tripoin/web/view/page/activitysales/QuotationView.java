package com.tripoin.web.view.page.activitysales;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.web.servlet.VaadinView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "quotationView", cached = true)
public class QuotationView extends VerticalLayout implements View {

	private static final long serialVersionUID = -4592518571070450190L;

	public QuotationView() {
        setSpacing(true);
        setMargin(true);

        Label title = new Label("Quotation");
        title.addStyleName("h1");
        addComponent(title);
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }
    
}
