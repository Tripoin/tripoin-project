package com.tripoin.web.view.valo;

import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.ui.Notification;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class MockActionHandler {

    public static Handler actionHandler = new Handler() {    	
		private static final long serialVersionUID = -1297210206380850953L;
		private final Action ACTION_ONE = new Action("Action One");
        private final Action ACTION_TWO = new Action("Action Two");
        private final Action ACTION_THREE = new Action("Action Three");
        private final Action[] ACTIONS = new Action[] { ACTION_ONE, ACTION_TWO, ACTION_THREE };
        @Override
        public void handleAction(final Action action, final Object sender, final Object target) {
            Notification.show(action.getCaption());
        }
        @Override
        public Action[] getActions(final Object target, final Object sender) {
            return ACTIONS;
        }
    };

    public static Handler getActionHandler() {
        return actionHandler;
    }
    
}
