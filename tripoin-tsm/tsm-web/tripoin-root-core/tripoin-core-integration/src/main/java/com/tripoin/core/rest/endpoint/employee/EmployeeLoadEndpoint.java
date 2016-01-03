package com.tripoin.core.rest.endpoint.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dao.filter.PageArgument;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeeTransferObject.EnumFieldEmployee;
import com.tripoin.core.pojo.Employee;
import com.tripoin.core.pojo.VersionControlSystemTable;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.IVersionControlSystemTableService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("employeeLoadEndpoint")
public class EmployeeLoadEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeLoadEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private IVersionControlSystemTableService iVersionControlSystemTableService;
	
	private VersionControlSystemTable versionControlSystemTable;
	private Integer positionPage;
	private Integer rowPerPage;
	private Integer totalPage;
	private Integer minRow;
	private Integer maxRow;
	private String currentUserName;

	@Secured({RoleConstant.ROLE_SALESMAN, RoleConstant.ROLE_SALESSUPERVISOR, RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<EmployeeTransferObject> loadEmployee(Message<?> inMessage){	
		EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken))
		    this.currentUserName = authentication.getName();		
		
		try{
			FilterArgument[] filterArguments = new FilterArgument[] { 
					new FilterArgument("profile.user.username", ECommonOperator.EQUALS) 
			};
			List<Employee> employeeList = iGenericManagerJpa.loadObjectsFilterArgument(Employee.class, filterArguments, new Object[] { currentUserName }, null, null);
			List<EmployeeData> employeeDatas = new ArrayList<EmployeeData>();
			if(employeeList != null){
				for(Employee employee : employeeList){
					EmployeeData employeeData = new EmployeeData(employee);
					employeeDatas.add(employeeData);
				}
				employeeTransferObject.setEmployeeDatas(employeeDatas);
			}
			employeeList = null;
			employeeDatas = null;
			employeeTransferObject.setResponseCode("0");
			employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			employeeTransferObject.setResponseDesc("Load Employee Data Success");			
		}catch (Exception e){
			LOGGER.error("Load Employee System Error : "+e.getLocalizedMessage(), e);
			employeeTransferObject.setResponseCode("1");
			employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			employeeTransferObject.setResponseDesc("Load Employee System Error : "+e.getLocalizedMessage());
		}
		
		setReturnStatusAndMessage(employeeTransferObject, responseHeaderMap);
		Message<EmployeeTransferObject> message = new GenericMessage<EmployeeTransferObject>(employeeTransferObject, responseHeaderMap);
		employeeTransferObject = null;
		return message;		
	}

	@Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<EmployeeTransferObject> loadAllEmployees(Message<?> inMessage){	
		EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();	
		
		try{
			List<Employee> employeeList = iGenericManagerJpa.loadObjects(Employee.class);
			List<EmployeeData> employeeDatas = new ArrayList<EmployeeData>();
			if(employeeList != null){
				for(Employee employee : employeeList){
					EmployeeData employeeData = new EmployeeData(employee);
					employeeDatas.add(employeeData);
				}
				employeeTransferObject.setEmployeeDatas(employeeDatas);
			}
			employeeTransferObject.setResponseCode("0");
			employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			employeeTransferObject.setResponseDesc("Load All Employee Data Success");			
		}catch (Exception e){
			LOGGER.error("Load All Employee System Error : "+e.getLocalizedMessage(), e);
			employeeTransferObject.setResponseCode("1");
			employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			employeeTransferObject.setResponseDesc("Load All Employee System Error : "+e.getLocalizedMessage());
		}
		
		setReturnStatusAndMessage(employeeTransferObject, responseHeaderMap);
		Message<EmployeeTransferObject> message = new GenericMessage<EmployeeTransferObject>(employeeTransferObject, responseHeaderMap);
		employeeTransferObject = null;
		return message;		
	}
	
	@Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<EmployeeTransferObject> loadEmployeePaging(Message<EmployeeTransferObject> inMessage){	
		EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		
		try{			
			FilterArgument[] filterArguments = null;
			Object[] values = null;
			if(inMessage.getPayload() != null){
				employeeTransferObject = inMessage.getPayload();				
				try {					
					if(employeeTransferObject.getFindEmployeeData() != null){
						filterArguments = new FilterArgument[employeeTransferObject.getFindEmployeeData().size()];
						values = new Object[employeeTransferObject.getFindEmployeeData().size()];
						int i = 0;
						for(String key : employeeTransferObject.getFindEmployeeData().keySet()){
							if(EnumFieldEmployee.NIK_EMPLOYE.toString().equals(key))
								filterArguments[i] = new FilterArgument(key, ECommonOperator.EQUALS);
							else filterArguments[i] = new FilterArgument(key, ECommonOperator.LIKE_BOTH_SIDE);
							values[i] = employeeTransferObject.getFindEmployeeData().get(key);
							i++;
						}
						versionControlSystemTable = new VersionControlSystemTable();
						versionControlSystemTable.setTotalRow(iGenericManagerJpa.totalRowData(Employee.class, filterArguments, values, null, null));
					}else versionControlSystemTable = iVersionControlSystemTableService.loadValue(Employee.TABLE_NAME);						
					positionPage = employeeTransferObject.getPositionPage();
					rowPerPage = employeeTransferObject.getRowPerPage();		
					if(rowPerPage == null || rowPerPage == 0) rowPerPage = ParameterConstant.ROW_PER_PAGE;
					totalPage = new Double(versionControlSystemTable.getTotalRow()/rowPerPage).intValue();
				} catch (Exception e) {
					LOGGER.error("Load Paging Employee System Error : "+e.getLocalizedMessage(), e);
					rowPerPage = ParameterConstant.ROW_PER_PAGE;
					versionControlSystemTable = new VersionControlSystemTable();
					versionControlSystemTable.setTotalRow(new Long(rowPerPage));
					totalPage = 0;
				}
				if(positionPage == null || positionPage <= 0) positionPage = 1;
				if(versionControlSystemTable.getTotalRow()%rowPerPage>0)totalPage++;	
				if(positionPage > totalPage) positionPage = totalPage;				
		        minRow = versionControlSystemTable.getTotalRow().intValue() - (positionPage * rowPerPage);
		        maxRow = minRow + rowPerPage;
		        employeeTransferObject.setPositionPage(positionPage);
		        employeeTransferObject.setRowPerPage(rowPerPage);
		        employeeTransferObject.setTotalPage(totalPage);
			}
			List<Employee> employeeList = iGenericManagerJpa.loadObjectsFilterArgument(Employee.class, filterArguments, values, null, new PageArgument(minRow, maxRow));
			List<EmployeeData> employeeDatas = new ArrayList<EmployeeData>();
			if(employeeList != null){
				for(int i=employeeList.size()-1; i>=0; i--)
					employeeDatas.add(new EmployeeData(employeeList.get(i)));					
				employeeTransferObject.setEmployeeDatas(employeeDatas);
			}
			employeeTransferObject.setResponseCode("0");
			employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			employeeTransferObject.setResponseDesc("Load Paging Employee Data Success");			
		}catch (Exception e){
			LOGGER.error("Load Paging Employee System Error : "+e.getLocalizedMessage(), e);
			employeeTransferObject.setResponseCode("1");
			employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			employeeTransferObject.setResponseDesc("Load Paging Employee System Error : "+e.getLocalizedMessage());
		}
		
		setReturnStatusAndMessage(employeeTransferObject, responseHeaderMap);
		Message<EmployeeTransferObject> message = new GenericMessage<EmployeeTransferObject>(employeeTransferObject, responseHeaderMap);
		employeeTransferObject = null;
		versionControlSystemTable = null;
		return message;		
	}
	
}
