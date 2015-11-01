package com.tripoin.web.view.page.audittrail;

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
@VaadinView(value = "historyLoginView", cached = true)
public class HistoryLoginView extends VerticalLayout implements View {

	private static final long serialVersionUID = -4592518571070450190L;

	public HistoryLoginView() {
        setSpacing(true);
        setMargin(true);

        Label title = new Label("History Login");
        title.addStyleName("h1");
        addComponent(title);
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }
    
}
