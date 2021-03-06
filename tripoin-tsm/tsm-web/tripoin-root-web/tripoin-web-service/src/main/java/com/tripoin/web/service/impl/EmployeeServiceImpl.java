package com.tripoin.web.service.impl;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.EmployeePrivateData;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.EmployeeTransferObject.EnumFieldEmployee;
import com.tripoin.core.dto.GeneralPagingTransferObject;
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
	public EmployeeTransferObject getAllEmployeeDatas(GeneralPagingTransferObject generalPagingTransferObject) {
		return stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_EMPLOYEE_ALL_PAGE), generalPagingTransferObject, EmployeeTransferObject.class);
	}

	@Override
	public GeneralTransferObject updateEmployee(GeneralTransferObject dataTransferObject, final ServletContext servletContext) {
		GeneralTransferObject generalTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_EMPLOYEE_UPDATE), dataTransferObject, EmployeeTransferObject.class);
		if(RoleConstant.ROLE_AREASALESMANAGER.equals((String)dataTransferObject.getParameterData().get(EnumFieldEmployee.OCCUPATION_CODE.toString())))
			threadBuildEmployeeAreaSalesManagerContainer(generalTransferObject, servletContext);
		else if(RoleConstant.ROLE_NATIONALSALESMANAGER.equals((String)dataTransferObject.getParameterData().get(EnumFieldEmployee.OCCUPATION_CODE.toString())))
			threadBuildEmployeeNationalSalesManagerContainer(generalTransferObject, servletContext);
		return generalTransferObject;
	}

	@Override
	public GeneralTransferObject saveEmployee(GeneralTransferObject dataTransferObject, final ServletContext servletContext) {
		GeneralTransferObject generalTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_EMPLOYEE_SAVE), dataTransferObject, EmployeeTransferObject.class);
		if(RoleConstant.ROLE_AREASALESMANAGER.equals((String)dataTransferObject.getParameterData().get(EnumFieldEmployee.OCCUPATION_CODE.toString())))
			threadBuildEmployeeAreaSalesManagerContainer(generalTransferObject, servletContext);
		else if(RoleConstant.ROLE_NATIONALSALESMANAGER.equals((String)dataTransferObject.getParameterData().get(EnumFieldEmployee.OCCUPATION_CODE.toString())))
			threadBuildEmployeeNationalSalesManagerContainer(generalTransferObject, servletContext);
		return generalTransferObject;
	}
	
	private void threadBuildEmployeeAreaSalesManagerContainer(GeneralTransferObject generalTransferObject, final ServletContext servletContext){
		if(EResponseCode.RC_SUCCESS.getResponseCode().equals(generalTransferObject.getResponseCode())){
			taskExecutor.execute(new Runnable() {				
				@Override
				public void run() {
					List<EmployeePrivateData> employeePrivateDatas = dataLoadStarted.loadEmployeeAreaSalesManagerData();
					servletContext.setAttribute(WebServiceConstant.CONTEXT_CONSTANT_EMPLOYEE_AREASALESMANAGER, employeePrivateDatas);
					employeePrivateDatas = null;
				}
			});
		}
	}
	
	private void threadBuildEmployeeNationalSalesManagerContainer(GeneralTransferObject generalTransferObject, final ServletContext servletContext){
		if(EResponseCode.RC_SUCCESS.getResponseCode().equals(generalTransferObject.getResponseCode())){
			taskExecutor.execute(new Runnable() {				
				@Override
				public void run() {
					List<EmployeePrivateData> employeePrivateDatas = dataLoadStarted.loadEmployeeNationalSalesManagerData();
					servletContext.setAttribute(WebServiceConstant.CONTEXT_CONSTANT_EMPLOYEE_NATIONALSALESMANAGER, employeePrivateDatas);
					employeePrivateDatas = null;
				}
			});
		}
	}

}
