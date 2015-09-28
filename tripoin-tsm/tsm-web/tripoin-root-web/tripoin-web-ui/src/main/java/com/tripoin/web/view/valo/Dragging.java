package com.tripoin.web.view.valo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.web.servlet.VaadinView;
import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.DropTarget;
import com.vaadin.event.dd.TargetDetails;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.event.dd.acceptcriteria.SourceIsTarget;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.dd.HorizontalDropLocation;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "dragging", cached = true)
public class Dragging extends VerticalLayout implements View {

	private static final long serialVersionUID = 3938364301298669281L;
	SortableLayout sample;

    public Dragging() {
        setMargin(true);
        setSpacing(true);

        Label h1 = new Label("Dragging Components");
        h1.addStyleName("h1");
        addComponent(h1);

        MenuBar options = new MenuBar();
        options.setCaption("Drop Hints");
        addComponent(options);

        // Use these styles to hide irrelevant drag hints
        // Can be used either on a parent or directly on the DnDWrapper
        MenuItem opt = options.addItem("Vertical", new MenuBar.Command() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -2786417871109712260L;

			@Override
            public void menuSelected(MenuItem selectedItem) {
                if (selectedItem.isChecked()) {
                    sample.removeStyleName("no-vertical-drag-hints");
                } else {
                    sample.addStyleName("no-vertical-drag-hints");
                }
            }
        });
        opt.setCheckable(true);
        opt.setChecked(true);

        opt = options.addItem("Horizontal", new MenuBar.Command() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -4437855465445108595L;

			@Override
            public void menuSelected(MenuItem selectedItem) {
                if (selectedItem.isChecked()) {
                    sample.removeStyleName("no-horizontal-drag-hints");
                } else {
                    sample.addStyleName("no-horizontal-drag-hints");
                }
            }
        });
        opt.setCheckable(true);
        opt.setChecked(true);

        opt = options.addItem("Box", new MenuBar.Command() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 8558680268774947749L;

			@Override
            public void menuSelected(MenuItem selectedItem) {
                if (selectedItem.isChecked()) {
                    sample.removeStyleName("no-box-drag-hints");
                } else {
                    sample.addStyleName("no-box-drag-hints");
                }
            }
        });
        opt.setCheckable(true);
        opt.setChecked(true);

        sample = new SortableLayout();
        sample.setSizeUndefined();
        sample.setHeight("100px");

        for (final com.vaadin.ui.Component component : createComponents()) {
            sample.addComponent(component);
        }

        addComponent(sample);

    }

    private List<com.vaadin.ui.Component> createComponents() {
        final List<com.vaadin.ui.Component> components = new ArrayList<com.vaadin.ui.Component>();

        final Label label = new Label(
                "This is a long text block that will wrap.");
        label.setWidth("120px");
        components.add(label);

        final Embedded image = new Embedded("", new ThemeResource(
                "../runo/icons/64/document.png"));
        components.add(image);

        final CssLayout documentLayout = new CssLayout();
        documentLayout.setWidth("19px");
        for (int i = 0; i < 5; ++i) {
            final Embedded e = new Embedded(null, new ThemeResource(
                    "../runo/icons/16/document.png"));
            e.setHeight("16px");
            e.setWidth("16px");
            documentLayout.addComponent(e);
        }
        components.add(documentLayout);

        final VerticalLayout buttonLayout = new VerticalLayout();
        final Button button = new Button("Button");
        button.addClickListener(new Button.ClickListener() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 4252281534034188917L;

			@Override
            public void buttonClick(final ClickEvent event) {
                Notification.show("Button clicked");
            }
        });
        buttonLayout.addComponent(button);
        buttonLayout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
        components.add(buttonLayout);

        return components;
    }

    private static class ReorderLayoutDropHandler implements DropHandler {

        /**
		 * 
		 */
		private static final long serialVersionUID = 7803698517335328687L;
		private final AbstractOrderedLayout layout;

        public ReorderLayoutDropHandler(final AbstractOrderedLayout layout) {
            this.layout = layout;
        }

        @Override
        public AcceptCriterion getAcceptCriterion() {
            return new Not(SourceIsTarget.get());
        }

        @SuppressWarnings("deprecation")
		@Override
        public void drop(final DragAndDropEvent dropEvent) {
            final Transferable transferable = dropEvent.getTransferable();
            final com.vaadin.ui.Component sourceComponent = transferable.getSourceComponent();
            if (sourceComponent instanceof WrappedComponent) {
                final TargetDetails dropTargetData = dropEvent
                        .getTargetDetails();
                final DropTarget target = dropTargetData.getTarget();

                // find the location where to move the dragged component
                boolean sourceWasAfterTarget = true;
                int index = 0;
                final Iterator<com.vaadin.ui.Component> componentIterator = layout
                        .getComponentIterator();
                com.vaadin.ui.Component next = null;
                while (next != target && componentIterator.hasNext()) {
                    next = componentIterator.next();
                    if (next != sourceComponent) {
                        index++;
                    } else {
                        sourceWasAfterTarget = false;
                    }
                }
                if (next == null || next != target) {
                    // component not found - if dragging from another layout
                    return;
                }

                // drop on top of target?
                if (dropTargetData.getData("horizontalLocation").equals(
                        HorizontalDropLocation.CENTER.toString())) {
                    if (sourceWasAfterTarget) {
                        index--;
                    }
                }

                // drop before the target?
                else if (dropTargetData.getData("horizontalLocation").equals(
                        HorizontalDropLocation.LEFT.toString())) {
                    index--;
                    if (index < 0) {
                        index = 0;
                    }
                }

                // move component within the layout
                layout.removeComponent(sourceComponent);
                layout.addComponent(sourceComponent, index);
            }
        }
    }

    private static class SortableLayout extends CustomComponent {
        /**
		 * 
		 */
		private static final long serialVersionUID = -9215228541033140561L;
		private final AbstractOrderedLayout layout;
        private final DropHandler dropHandler;

        public SortableLayout() {
            layout = new HorizontalLayout();
            layout.setMargin(true);
            dropHandler = new ReorderLayoutDropHandler(layout);

            // final DragAndDropWrapper pane = new DragAndDropWrapper(layout);
            setCompositionRoot(layout);
        }

        public void addComponent(final com.vaadin.ui.Component component) {
            final WrappedComponent wrapper = new WrappedComponent(component,
                    dropHandler);
            wrapper.setSizeUndefined();
            component.setHeight("100%");
            wrapper.setHeight("100%");
            layout.addComponent(wrapper);
        }
    }

    private static class WrappedComponent extends DragAndDropWrapper {

        /**
		 * 
		 */
		private static final long serialVersionUID = 4336701737193760660L;
		private final DropHandler dropHandler;

        public WrappedComponent(final com.vaadin.ui.Component content,
                final DropHandler dropHandler) {
            super(content);
            this.dropHandler = dropHandler;
            setDragStartMode(DragStartMode.WRAPPER);
        }

        @Override
        public DropHandler getDropHandler() {
            return dropHandler;
        }

    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }
}
