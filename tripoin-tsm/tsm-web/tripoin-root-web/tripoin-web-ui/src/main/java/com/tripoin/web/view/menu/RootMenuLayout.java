package com.tripoin.web.view.menu;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class RootMenuLayout extends HorizontalLayout {

	private static final long serialVersionUID = 5628348322337740893L;
	private CssLayout contentArea = new CssLayout();
    private CssLayout menuArea = new CssLayout();

    public RootMenuLayout() {
        setSizeFull();

        menuArea.setPrimaryStyleName("valo-menu");

        contentArea.setPrimaryStyleName("valo-content");
        contentArea.addStyleName("v-scrollable");
        contentArea.addStyleName("view-content");
        contentArea.setSizeFull();

        addComponent(menuArea);
        addComponent(contentArea);        
        setExpandRatio(contentArea, 1.0f);
    }

    public ComponentContainer getContentContainer() {
        return contentArea;
    }

    public void addMenu(Component menu) {
        menu.addStyleName("sidebar");
        menu.addStyleName("valo-menu-part");
        menu.addStyleName("no-vertical-drag-hints");
        menu.addStyleName("no-horizontal-drag-hints");
        menuArea.addComponent(menu);
    }

}
