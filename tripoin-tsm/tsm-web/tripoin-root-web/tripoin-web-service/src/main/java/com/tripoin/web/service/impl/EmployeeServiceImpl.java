package com.tripoin.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IEmployeeService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private ICommonRest commonRest;
	
	@Autowired
	private IStateFullRest stateFullRest;

	@Override
	public EmployeeData getEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeData> getAllEmployeeDatas() {
		return stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_EMPLOYEE_ALL), EmployeeTransferObject.class).getEmployeeDatas();
	}

	@Override
	public EmployeeTransferObject updateEmployee(EmployeeData employeeData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeTransferObject saveEmployee(EmployeeData employeeData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralTransferObject deleteEmployee(EmployeeData employeeData) {
		// TODO Auto-generated method stub
		return null;
	}

}
