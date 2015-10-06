package com.tripoin.web.view.valo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.web.servlet.VaadinView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.PopupView.Content;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "popupviews", cached = true)
public class PopupViews extends VerticalLayout implements View {

	private static final long serialVersionUID = -5338338740907860757L;

	public PopupViews() {
        setMargin(true);

        Label h1 = new Label("Popup Views");
        h1.addStyleName("h1");
        addComponent(h1);

        HorizontalLayout row = new HorizontalLayout();
        row.addStyleName("wrapping");
        row.setSpacing(true);
        addComponent(row);

        PopupView pv = new PopupView(new Content() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 3178595923379249115L;

			@Override
            public com.vaadin.ui.Component getPopupComponent() {
                return new VerticalLayout() {
                    /**
					 * 
					 */
					private static final long serialVersionUID = -2582089126619180324L;

					{
                        setMargin(true);
                        setWidth("300px");
                        addComponent(new Label(
                                "Fictum,  deserunt mollit anim laborum astutumque! Magna pars studiorum, prodita quaerimus."));
                    }
                };
            }

            @Override
            public String getMinimizedValueAsHTML() {
                return "Click to view";
            }
        });
        row.addComponent(pv);
        pv.setHideOnMouseOut(true);
        pv.setCaption("Hide on mouse-out");

        pv = new PopupView(new Content() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -7600967537251843315L;
			int count = 0;

            @Override
            public com.vaadin.ui.Component getPopupComponent() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                return new VerticalLayout() {
                    /**
					 * 
					 */
					private static final long serialVersionUID = -7588414957814046363L;

					{
                        setMargin(true);
                        addComponent(new Label(
                                "<h3>Thanks for waiting!</h3><p>You've opened this popup <b>"
                                        + ++count + " time"
                                        + (count > 1 ? "s" : " only")
                                        + "</b>.</p>", ContentMode.HTML));
                    }
                };
            }

            @Override
            public String getMinimizedValueAsHTML() {
                return "Show slow loading content";
            }
        });
        row.addComponent(pv);
        pv.setHideOnMouseOut(false);
        pv.setCaption("Hide on click-outside");
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }

}
