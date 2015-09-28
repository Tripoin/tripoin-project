package com.tripoin.web.view.valo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.web.servlet.VaadinView;
import com.vaadin.data.Container;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.TreeDragMode;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "trees", cached = true)
public class Trees extends VerticalLayout implements View {

	private static final long serialVersionUID = 8091134144779751164L;

	public Trees() {
        setMargin(true);

        Label h1 = new Label("Trees");
        h1.addStyleName("h1");
        addComponent(h1);

        HorizontalLayout row = new HorizontalLayout();
        row.addStyleName("wrapping");
        row.setSpacing(true);
        addComponent(row);

        Tree tree = new Tree();
        tree.setSelectable(true);
        tree.setMultiSelect(true);
        Container generateContainer = MockGeneratorContainer.generateContainer(10, true);
        tree.setContainerDataSource(generateContainer);
        tree.setDragMode(TreeDragMode.NODE);
        row.addComponent(tree);
        tree.setItemCaptionPropertyId(MockGeneratorContainer.CAPTION_PROPERTY);
        tree.setItemIconPropertyId(MockGeneratorContainer.ICON_PROPERTY);
        tree.expandItem(generateContainer.getItemIds().iterator().next());

        tree.setDropHandler(new DropHandler() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -1552827777910999593L;

			@Override
            public AcceptCriterion getAcceptCriterion() {
                return AcceptAll.get();
            }

            @Override
            public void drop(DragAndDropEvent event) {
                Notification.show(event.getTransferable().toString());
            }
        });

        // Add actions (context menu)
        tree.addActionHandler(MockActionHandler.getActionHandler());
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
