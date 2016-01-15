package com.tripoin.web.service;

import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.OccupationData;
import com.vaadin.data.util.BeanItemContainer;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IDataLoadStarted {
    
	public void buildOccupationContainer();
	
	public BeanItemContainer<OccupationData> getOccupationContainer();
    
	public void buildEmployeeNotSalesmanContainer();
	
	public BeanItemContainer<EmployeeData> employeeNotSalesmanContainer();
	
}
