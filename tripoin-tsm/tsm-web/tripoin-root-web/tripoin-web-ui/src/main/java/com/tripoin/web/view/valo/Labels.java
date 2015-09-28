package com.tripoin.web.view.valo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.web.servlet.VaadinView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "labels", cached = true)
public class Labels extends VerticalLayout implements View {

	private static final long serialVersionUID = -5967125651907718175L;

	public Labels() {
        setMargin(true);
        addStyleName("content-labels");

        Label h1 = new Label("Labels");
        h1.addStyleName("h1");
        addComponent(h1);

        VerticalLayout left = new VerticalLayout();
        left.setMargin(new MarginInfo(false, true, false, false));
        addComponent(left);

        Label huge = new Label("Huge type for display text.");
        huge.addStyleName("huge");
        left.addComponent(huge);

        Label large = new Label(
                "Large type for introductory text. Etiam at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu.");
        large.addStyleName("large");
        left.addComponent(large);

        Label h2 = new Label("Subtitle");
        h2.addStyleName("h2");
        left.addComponent(h2);

        Label normal = new Label(
                "Normal type for plain text, with a <a href=\"https://vaadin.com\">regular link</a>. Etiam at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu.",
                ContentMode.HTML);
        left.addComponent(normal);

        Label h3 = new Label("Small Title");
        h3.addStyleName("h3");
        left.addComponent(h3);

        Label small = new Label(
                "Small type for additional text. Etiam at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu.");
        small.addStyleName("small");
        left.addComponent(small);

        Label tiny = new Label("Tiny type for minor text.");
        tiny.addStyleName("tiny");
        left.addComponent(tiny);

        Label h4 = new Label("Section Title");
        h4.addStyleName("h4");
        left.addComponent(h4);

        normal = new Label(
                "Normal type for plain text. Etiam at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu.");
        left.addComponent(normal);

        Panel p = new Panel("Additional Label Styles");
        addComponent(p);

        VerticalLayout right = new VerticalLayout();
        right.setSpacing(true);
        right.setMargin(true);
        p.setContent(right);

        Label label = new Label(
                "Bold type for prominent text. Etiam at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu.");
        label.addStyleName("bold");
        right.addComponent(label);

        label = new Label(
                "Light type for subtle text. Etiam at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu.");
        label.addStyleName("light");
        right.addComponent(label);

        label = new Label(
                "Colored type for highlighted text. Etiam at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu.");
        label.addStyleName("colored");
        right.addComponent(label);

        label = new Label("A label for success");
        label.addStyleName("success");
        right.addComponent(label);

        label = new Label("A label for failure");
        label.addStyleName("failure");
        right.addComponent(label);

    }

    @Override
    public void enter(final ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }
}
