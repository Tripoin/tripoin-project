package com.tripoin.web.service;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.http.HttpStatus;

import com.tripoin.core.dto.AreaData;
import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.OccupationData;
import com.vaadin.data.util.BeanItemContainer;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IDataLoadStarted {
	
	public HttpStatus getStatusCode();
    
	public List<OccupationData> loadOccupationData();
    
	public List<EmployeeData> loadEmployeeAreaSalesManagerData();
    
	public List<EmployeeData> loadEmployeeNationalSalesManagerData();
	
	public List<AreaData> loadAreaData();
	
	public BeanItemContainer<OccupationData> getOccupationContainer(ServletContext servletContext);
	
	public BeanItemContainer<EmployeeData> employeeAreaSalesManagerContainer(ServletContext servletContext);
	
	public BeanItemContainer<EmployeeData> employeeNationalSalesManagerContainer(ServletContext servletContext);
	
}
