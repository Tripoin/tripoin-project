package com.tripoin.web.service;

import javax.servlet.ServletContext;

import com.tripoin.core.dto.EmployeeTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IEmployeeService {
    
    public EmployeeTransferObject getAllEmployeeDatas(EmployeeTransferObject employeeTransferObject);
    
    public EmployeeTransferObject updateEmployee(EmployeeTransferObject dataTransferObject, final ServletContext servletContext);
    
    public EmployeeTransferObject saveEmployee(EmployeeTransferObject dataTransferObject, final ServletContext servletContext);

}
