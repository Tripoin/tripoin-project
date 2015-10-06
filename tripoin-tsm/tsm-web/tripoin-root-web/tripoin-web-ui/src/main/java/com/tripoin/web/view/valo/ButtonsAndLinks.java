package com.tripoin.web.view.valo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.web.servlet.VaadinView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "buttons-and-links", cached = true)
public class ButtonsAndLinks extends VerticalLayout implements View {

	private static final long serialVersionUID = -8238673662405761049L;

    public ButtonsAndLinks() {
        setMargin(true);

        Label h1 = new Label("Buttons");
        h1.addStyleName("h1");
        addComponent(h1);

        HorizontalLayout row = new HorizontalLayout();
        row.addStyleName("wrapping");
        row.setSpacing(true);
        addComponent(row);

        Button button = new Button("Normal");
        row.addComponent(button);

        button = new Button("Disabled");
        button.setEnabled(false);
        row.addComponent(button);

        button = new Button("Primary");
        button.addStyleName("primary");
        row.addComponent(button);

        button = new Button("Friendly");
        button.addStyleName("friendly");
        row.addComponent(button);

        button = new Button("Danger");
        button.addStyleName("danger");
        row.addComponent(button);

        BaseIcon baseIcon = new BaseIcon(10);
        button = new Button("Small");
        button.addStyleName("small");
        button.setIcon(baseIcon.get());
        row.addComponent(button);

        button = new Button("Large");
        button.addStyleName("large");
        button.setIcon(baseIcon.get());
        row.addComponent(button);

        button = new Button("Top");
        button.addStyleName("icon-align-top");
        button.setIcon(baseIcon.get());
        row.addComponent(button);

        button = new Button("Image icon");
        button.setIcon(baseIcon.get(true, 16));
        row.addComponent(button);

        button = new Button("Image icon");
        button.addStyleName("icon-align-right");
        button.setIcon(baseIcon.get(true));
        row.addComponent(button);

        button = new Button("Photos");
        button.setIcon(baseIcon.get());
        row.addComponent(button);

        button = new Button();
        button.setIcon(baseIcon.get());
        button.addStyleName("icon-only");
        row.addComponent(button);

        button = new Button("Borderless");
        button.setIcon(baseIcon.get());
        button.addStyleName("borderless");
        row.addComponent(button);

        button = new Button("Borderless, colored");
        button.setIcon(baseIcon.get());
        button.addStyleName("borderless-colored");
        row.addComponent(button);

        button = new Button("Quiet");
        button.setIcon(baseIcon.get());
        button.addStyleName("quiet");
        row.addComponent(button);

        button = new Button("Link style");
        button.setIcon(baseIcon.get());
        button.addStyleName("link");
        row.addComponent(button);

        button = new Button("Icon on right");
        button.setIcon(baseIcon.get());
        button.addStyleName("icon-align-right");
        row.addComponent(button);

        CssLayout group = new CssLayout();
        group.addStyleName("v-component-group");
        row.addComponent(group);

        button = new Button("One");
        group.addComponent(button);
        button = new Button("Two");
        group.addComponent(button);
        button = new Button("Three");
        group.addComponent(button);

        button = new Button("Tiny");
        button.addStyleName("tiny");
        row.addComponent(button);

        button = new Button("Huge");
        button.addStyleName("huge");
        row.addComponent(button);

        NativeButton nbutton = new NativeButton("Native");
        row.addComponent(nbutton);

        h1 = new Label("Links");
        h1.addStyleName("h1");
        addComponent(h1);

        row = new HorizontalLayout();
        row.addStyleName("wrapping");
        row.setSpacing(true);
        addComponent(row);

        Link link = new Link("vaadin.com", new ExternalResource(
                "https://vaadin.com"));
        row.addComponent(link);

        link = new Link("Link with icon", new ExternalResource(
                "https://vaadin.com"));
        link.addStyleName("color3");
        link.setIcon(baseIcon.get());
        row.addComponent(link);

        link = new Link("Small", new ExternalResource("https://vaadin.com"));
        link.addStyleName("small");
        row.addComponent(link);

        link = new Link("Large", new ExternalResource("https://vaadin.com"));
        link.addStyleName("large");
        row.addComponent(link);

        link = new Link(null, new ExternalResource("https://vaadin.com"));
        link.setIcon(baseIcon.get());
        link.addStyleName("large");
        row.addComponent(link);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }

}
