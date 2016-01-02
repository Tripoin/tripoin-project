package com.tripoin.web.service;

import com.tripoin.core.dto.OccupationData;
import com.vaadin.data.util.BeanItemContainer;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IDataLoadStarted {
	
	public BeanItemContainer<OccupationData> getOccupationContainer();
    
}
