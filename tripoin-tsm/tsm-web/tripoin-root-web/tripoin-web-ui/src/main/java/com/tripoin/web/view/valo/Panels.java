package com.tripoin.web.view.valo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.web.servlet.VaadinView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "panels", cached = true)
public class Panels extends VerticalLayout implements View {

	private static final long serialVersionUID = -4974968864806312762L;

	public Panels() {
        setMargin(true);

        Label h1 = new Label("Panels & Layout panels");
        h1.addStyleName("h1");
        addComponent(h1);

        HorizontalLayout row = new HorizontalLayout();
        row.addStyleName("wrapping");
        row.setSpacing(true);
        addComponent(row);
        BaseIcon baseIcon = new BaseIcon(60);

        Panel panel = new Panel("Normal");
        panel.setIcon(baseIcon.get());
        panel.setContent(panelContent());
        row.addComponent(panel);

        panel = new Panel("Sized");
        panel.setIcon(baseIcon.get());
        panel.setWidth("10em");
        panel.setHeight("250px");
        panel.setContent(panelContent());
        row.addComponent(panel);

        panel = new Panel("Custom Caption");
        panel.setIcon(baseIcon.get());
        panel.addStyleName("color1");
        panel.setContent(panelContent());
        row.addComponent(panel);

        panel = new Panel("Custom Caption");
        panel.setIcon(baseIcon.get());
        panel.addStyleName("color2");
        panel.setContent(panelContent());
        row.addComponent(panel);

        panel = new Panel("Custom Caption");
        panel.setIcon(baseIcon.get());
        panel.addStyleName("color3");
        panel.setContent(panelContent());
        row.addComponent(panel);

        panel = new Panel("Borderless style");
        panel.setIcon(baseIcon.get());
        panel.addStyleName("borderless");
        panel.setContent(panelContent());
        row.addComponent(panel);

        panel = new Panel("Borderless + scroll divider");
        panel.setIcon(baseIcon.get());
        panel.addStyleName("borderless");
        panel.addStyleName("scroll-divider");
        panel.setContent(panelContentScroll());
        panel.setHeight("17em");
        row.addComponent(panel);

        panel = new Panel("Well style");
        panel.setIcon(baseIcon.get());
        panel.addStyleName("well");
        panel.setContent(panelContent());
        row.addComponent(panel);

        CssLayout layout = new CssLayout();
        layout.setIcon(baseIcon.get());
        layout.setCaption("Panel style layout");
        layout.addStyleName("card");
        layout.addComponent(panelContent());
        row.addComponent(layout);

        layout = new CssLayout();
        layout.addStyleName("card");
        row.addComponent(layout);
        HorizontalLayout panelCaption = new HorizontalLayout();
        panelCaption.addStyleName("v-panel-caption");
        panelCaption.setWidth("100%");
        // panelCaption.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        Label label = new Label("Panel style layout");
        panelCaption.addComponent(label);
        panelCaption.setExpandRatio(label, 1);

        Button action = new Button();
        action.setIcon(FontAwesome.PENCIL);
        action.addStyleName("borderless-colored");
        action.addStyleName("small");
        action.addStyleName("icon-only");
        panelCaption.addComponent(action);
        MenuBar dropdown = new MenuBar();
        dropdown.addStyleName("borderless");
        dropdown.addStyleName("small");
        MenuItem addItem = dropdown.addItem("", FontAwesome.CHEVRON_DOWN, null);
        addItem.setStyleName("icon-only");
        addItem.addItem("Settings", null);
        addItem.addItem("Preferences", null);
        addItem.addSeparator();
        addItem.addItem("Sign Out", null);
        panelCaption.addComponent(dropdown);

        layout.addComponent(panelCaption);
        layout.addComponent(panelContent());
        layout.setWidth("14em");

        layout = new CssLayout();
        layout.setIcon(baseIcon.get());
        layout.setCaption("Well style layout");
        layout.addStyleName("well");
        layout.addComponent(panelContent());
        row.addComponent(layout);
    }

    com.vaadin.ui.Component panelContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);
        Label content = new Label(
                "Suspendisse dictum feugiat nisl ut dapibus. Mauris iaculis porttitor posuere. Praesent id metus massa, ut blandit odio.");
        content.setWidth("10em");
        layout.addComponent(content);
        Button button = new Button("Button");
        button.setSizeFull();
        layout.addComponent(button);
        return layout;
    }

    com.vaadin.ui.Component panelContentScroll() {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        Label content = new Label(
                "Suspendisse dictum feugiat nisl ut dapibus. Mauris iaculis porttitor posuere. Praesent id metus massa, ut blandit odio. Suspendisse dictum feugiat nisl ut dapibus. Mauris iaculis porttitor posuere. Praesent id metus massa, ut blandit odio.");
        content.setWidth("10em");
        layout.addComponent(content);
        Button button = new Button("Button");
        layout.addComponent(button);
        return layout;
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }

}
