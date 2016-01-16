package com.tripoin.web.service;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.http.HttpStatus;

import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.OccupationData;
import com.vaadin.data.util.BeanItemContainer;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IDataLoadStarted {
	
	public HttpStatus getStatusCode();
    
	public List<OccupationData> loadOccupationData();
	
	public BeanItemContainer<OccupationData> getOccupationContainer(ServletContext servletContext);
    
	public List<EmployeeData> loadEmployeeNotSalesmanData();
	
	public BeanItemContainer<EmployeeData> employeeNotSalesmanContainer(ServletContext servletContext);
	
}
