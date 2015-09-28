package com.tripoin.web.servlet;

import com.vaadin.navigator.View;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface ViewCacheContainer {
	
    public View getView(String name, String beanName, boolean cached);
    
}
