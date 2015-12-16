package com.tripoin.web.service;

import java.util.List;

import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.GeneralTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IEmployeeService {

    public EmployeeData getEmployee();
    
    public List<EmployeeData> getAllEmployeeDatas();
    
    public EmployeeTransferObject updateEmployee(EmployeeData employeeData);
    
    public EmployeeTransferObject saveEmployee(EmployeeData employeeData);

	public GeneralTransferObject deleteEmployee(EmployeeData employeeData);

}
