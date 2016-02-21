package com.tripoin.web.service.impl;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.EmployeeTransferObject.EnumFieldEmployee;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IDataLoadStarted;
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
	
	@Autowired
	private IDataLoadStarted dataLoadStarted;
	
	@Autowired
	@Qualifier(value="web-app-async-task-executor")
	private ThreadPoolTaskExecutor taskExecutor;

	@Override
	public EmployeeData getEmployee() {
		List<EmployeeData> employeeDatas = stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_EMPLOYEE), EmployeeTransferObject.class).getEmployeeDatas();
		if(employeeDatas == null || employeeDatas.isEmpty())
			return null;
		return employeeDatas.get(0);
	}

	@Override
	public List<EmployeeData> getAllEmployeeDatas() {
		return stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_EMPLOYEE_ALL), EmployeeTransferObject.class).getEmployeeDatas();
	}

	@Override
	public EmployeeTransferObject getAllEmployeeDatas(EmployeeTransferObject employeeTransferObject) {
		return stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_EMPLOYEE_ALL_PAGE), employeeTransferObject, EmployeeTransferObject.class);
	}

	@Override
	public EmployeeTransferObject updateEmployee(EmployeeTransferObject dataTransferObject, final ServletContext servletContext) {
		EmployeeTransferObject employeeTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_EMPLOYEE_UPDATE), dataTransferObject, EmployeeTransferObject.class);
		if(!RoleConstant.ROLE_SALESMAN.equals((String)dataTransferObject.getFindEmployeeData().get(EnumFieldEmployee.OCCUPATION_CODE.toString())))
			threadBuildEmployeeContainer(employeeTransferObject, servletContext);
		return employeeTransferObject;
	}

	@Override
	public EmployeeTransferObject saveEmployee(EmployeeTransferObject dataTransferObject, final ServletContext servletContext) {
		EmployeeTransferObject employeeTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_EMPLOYEE_SAVE), dataTransferObject, EmployeeTransferObject.class);
		if(!RoleConstant.ROLE_SALESMAN.equals((String)dataTransferObject.getFindEmployeeData().get(EnumFieldEmployee.OCCUPATION_CODE.toString())))
			threadBuildEmployeeContainer(employeeTransferObject, servletContext);
		return employeeTransferObject;
	}
	
	private void threadBuildEmployeeContainer(GeneralTransferObject generalTransferObject, final ServletContext servletContext){
		if("0".equals(generalTransferObject.getResponseCode())){
			taskExecutor.execute(new Runnable() {				
				@Override
				public void run() {
					List<EmployeeData> employeeDatas = dataLoadStarted.loadEmployeeNotSalesmanData();
					servletContext.setAttribute(WebServiceConstant.CONTEXT_CONSTANT_EMPLOYEE_NOT_SALESMAN, employeeDatas);
				}
			});
		}
	}

}
