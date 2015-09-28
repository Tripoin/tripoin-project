package com.tripoin.web.view.valo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.web.servlet.VaadinView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "selects", cached = true)
public class NativeSelects extends VerticalLayout implements View {

	private static final long serialVersionUID = -2235697110569798788L;

	public NativeSelects() {
        setMargin(true);

        Label h1 = new Label("Selects");
        h1.addStyleName("h1");
        addComponent(h1);

        HorizontalLayout row = new HorizontalLayout();
        row.addStyleName("wrapping");
        row.setSpacing(true);
        addComponent(row);

        NativeSelect select = new NativeSelect("Drop Down Select");
        row.addComponent(select);

        ListSelect list = new ListSelect("List Select");
        list.setNewItemsAllowed(true);
        row.addComponent(list);

        TwinColSelect tcs = new TwinColSelect("TwinCol Select");
        tcs.setLeftColumnCaption("Left Column");
        tcs.setRightColumnCaption("Right Column");
        tcs.setNewItemsAllowed(true);
        row.addComponent(tcs);

        TwinColSelect tcs2 = new TwinColSelect("Sized TwinCol Select");
        tcs2.setLeftColumnCaption("Left Column");
        tcs2.setRightColumnCaption("Right Column");
        tcs2.setNewItemsAllowed(true);
        tcs2.setWidth("280px");
        tcs2.setHeight("200px");
        row.addComponent(tcs2);

        for (int i = 1; i <= 10; i++) {
            select.addItem("Option " + i);
            list.addItem("Option " + i);
            tcs.addItem("Option " + i);
            tcs2.addItem("Option " + i);
        }
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }

}
