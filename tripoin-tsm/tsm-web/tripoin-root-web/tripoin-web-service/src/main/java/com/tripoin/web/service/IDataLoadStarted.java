package com.tripoin.web.service;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.http.HttpStatus;

import com.tripoin.core.dto.AreaData;
import com.tripoin.core.dto.EmployeePrivateData;
import com.tripoin.core.dto.OccupationData;
import com.vaadin.data.util.BeanItemContainer;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IDataLoadStarted {
	
	public HttpStatus getStatusCode();
    
	public List<OccupationData> loadOccupationData();
    
	public List<EmployeePrivateData> loadEmployeeAreaSalesManagerData();
    
	public List<EmployeePrivateData> loadEmployeeNationalSalesManagerData();
	
	public List<AreaData> loadAreaData();
	
	public BeanItemContainer<OccupationData> getOccupationContainer(ServletContext servletContext);
	
	public BeanItemContainer<AreaData> getAreaContainer(ServletContext servletContext);
	
	public BeanItemContainer<EmployeePrivateData> employeeAreaSalesManagerContainer(ServletContext servletContext);
	
	public BeanItemContainer<EmployeePrivateData> employeeNationalSalesManagerContainer(ServletContext servletContext);
	
}
