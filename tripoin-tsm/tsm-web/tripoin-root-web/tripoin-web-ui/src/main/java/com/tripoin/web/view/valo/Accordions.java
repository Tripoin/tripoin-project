package com.tripoin.web.view.valo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.web.servlet.VaadinView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "accordions", cached = true)
public class Accordions extends VerticalLayout implements View {

	private static final long serialVersionUID = 3709462060904714206L;

	public Accordions() {
        setMargin(true);

        Label h1 = new Label("Accordions");
        h1.addStyleName("h1");
        addComponent(h1);

        HorizontalLayout row = new HorizontalLayout();
        row.setSpacing(true);
        row.setWidth("100%");
        addComponent(row);

        row.addComponent(getAccordion("Normal"));

        Accordion ac = getAccordion("Borderless");
        ac.addStyleName("borderless");
        row.addComponent(ac);

    }

    Accordion getAccordion(String caption) {
        BaseIcon baseIcon = new BaseIcon(0);
        Accordion ac = new Accordion();
        ac.setCaption(caption);
        ac.addTab(new VerticalLayout() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -8605912631949995838L;

			{
                setMargin(true);
                addComponent(new Label(
                        "Fabio vel iudice vincam, sunt in culpa qui officia. Ut enim ad minim veniam, quis nostrud exercitation."));
            }
        }, "First Caption", baseIcon.get());
        ac.addTab(new VerticalLayout() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -6707444899417697868L;

			{
                setMargin(true);
                addComponent(new Label(
                        "Gallia est omnis divisa in partes tres, quarum."));
            }
        }, "Second Caption", baseIcon.get());
        ac.addTab(new VerticalLayout() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -4054133260368481007L;

			{
                setMargin(true);
                addComponent(new Label(
                        "Nihil hic munitissimus habendi senatus locus, nihil horum? Sed haec quis possit intrepidus aestimare tellus."));
            }
        }, "Third Caption", baseIcon.get());
        ac.addTab(new VerticalLayout() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 4008012720196686427L;

			{
                setMargin(true);
                addComponent(new Label(
                        "Inmensae subtilitatis, obscuris et malesuada fames. Quisque ut dolor gravida, placerat libero vel, euismod."));
            }
        }, "Custom Caption Style", baseIcon.get()).setStyleName("color1");
        return ac;
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }

}
