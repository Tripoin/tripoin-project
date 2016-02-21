package com.tripoin.web.service;

import java.util.List;

import javax.servlet.ServletContext;

import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeeTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IEmployeeService {

    public EmployeeData getEmployee();
    
    public List<EmployeeData> getAllEmployeeDatas();
    
    public EmployeeTransferObject getAllEmployeeDatas(EmployeeTransferObject employeeTransferObject);
    
    public EmployeeTransferObject updateEmployee(EmployeeTransferObject dataTransferObject, final ServletContext servletContext);
    
    public EmployeeTransferObject saveEmployee(EmployeeTransferObject dataTransferObject, final ServletContext servletContext);

}
