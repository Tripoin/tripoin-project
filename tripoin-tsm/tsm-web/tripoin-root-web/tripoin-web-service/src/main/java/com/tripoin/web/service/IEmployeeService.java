package com.tripoin.web.service;

import javax.servlet.ServletContext;

import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.core.dto.GeneralTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IEmployeeService {
    
    public EmployeeTransferObject getAllEmployeeDatas(GeneralPagingTransferObject generalPagingTransferObject);
    
    public GeneralTransferObject updateEmployee(GeneralTransferObject dataTransferObject, final ServletContext servletContext);
    
    public GeneralTransferObject saveEmployee(GeneralTransferObject dataTransferObject, final ServletContext servletContext);

}
