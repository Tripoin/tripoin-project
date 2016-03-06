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
import org.springframework.stereotype.Component;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dao.filter.PageArgument;
import com.tripoin.core.dto.AreaData;
import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.ProfileData;
import com.tripoin.core.dto.UserData;
import com.tripoin.core.pojo.Area;
import com.tripoin.core.pojo.Employee;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.User;
import com.tripoin.core.rest.endpoint.base.APageableEndpoint;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("employeeLoadEndpoint")
public class EmployeeLoadEndpoint extends APageableEndpoint {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeLoadEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/employee/load/all</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_ANONYMOUS_SECURE})
	public Message<EmployeeTransferObject> loadEmployeeAllByParam(Message<GeneralTransferObject> inMessage){	
		EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		try{			
			GeneralTransferObject generalTransferObject = inMessage.getPayload();
			if(generalTransferObject.getParameterData() != null && !generalTransferObject.getParameterData().isEmpty()){
				FilterArgument[] filterArguments = new FilterArgument[generalTransferObject.getParameterData().size()];
				Object[] values = new Object[generalTransferObject.getParameterData().size()];
				int i = 0;
				for(String key : generalTransferObject.getParameterData().keySet()){
					filterArguments[i] = new FilterArgument(key, ECommonOperator.EQUALS);
					values[i] = generalTransferObject.getParameterData().get(key);
					i++;
				}
				List<Employee> employeeList = iGenericManagerJpa.loadObjectsFilterArgument(Employee.class, filterArguments, values, null, null);
				List<EmployeeData> employeeDatas = new ArrayList<EmployeeData>();
				if(employeeList != null){
					for(Employee employee : employeeList){
						EmployeeData employeeData = new EmployeeData(employee);
						List<Profile> profileList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT obj.profile FROM Employee obj WHERE obj.code = ?", new Object[]{employeeData.getCode()}, null);
						ProfileData profileData = new ProfileData();
						profileData.setName(profileList.get(0).getName());
						employeeData.setProfileData(profileData);
						employeeDatas.add(employeeData);
						employeeData = null;
						profileList = null;
						profileData = null;
					}
					employeeTransferObject.setEmployeeDatas(employeeDatas);
					employeeList = null;
					employeeDatas = null;
				}
				employeeTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
				employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
				employeeTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
				values = null;
				filterArguments = null;
			}
			generalTransferObject = null;
		}catch (Exception e){
			LOGGER.error("Load All Employee System Error : "+e.getLocalizedMessage(), e);
			employeeTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			employeeTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString()+e.getLocalizedMessage());
		}		
		setReturnStatusAndMessage(employeeTransferObject, responseHeaderMap);
		Message<EmployeeTransferObject> message = new GenericMessage<EmployeeTransferObject>(employeeTransferObject, responseHeaderMap);
		employeeTransferObject = null;
		return message;		
	}
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/employee/load/paging</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<EmployeeTransferObject> loadEmployeePaging(Message<GeneralPagingTransferObject> inMessage){	
		EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		try{
			GeneralPagingTransferObject generalPagingTransferObject = inMessage.getPayload();
			if(generalPagingTransferObject != null){
				PageArgument pageArgument = getPageTransferObject(generalPagingTransferObject, generalPagingTransferObject.getParameterData());
				List<Employee> employeeList = iGenericManagerJpa.loadObjectsFilterArgument(Employee.class, getFilterArguments(), getValues(), null, pageArgument);
				List<EmployeeData> employeeDatas = new ArrayList<EmployeeData>();
				if(employeeList != null){
					for(int i=employeeList.size()-1; i>=0; i--){
						EmployeeData employeeData = new EmployeeData(employeeList.get(i));
						List<Profile> profileList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT obj.profile FROM Employee obj WHERE obj.code = ?", new Object[]{employeeData.getCode()}, null);
						ProfileData profileData = new ProfileData(profileList.get(0));
						List<User> userList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT obj.profile.user FROM Employee obj WHERE obj.code = ?", new Object[]{employeeData.getCode()}, null);
						UserData userData = new UserData();
						userData.setUsername(userList.get(0).getUsername());
						userData.setEnabled(userList.get(0).getEnabled());
						profileData.setUserData(userData);
						employeeData.setProfileData(profileData);
						if(!RoleConstant.ROLE_NATIONALSALESMANAGER.equals(employeeData.getOccupationData().getCode())){
							EmployeeData employeeDataParent = new EmployeeData();
							profileData = new ProfileData();
							profileList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT obj.employeeParent.profile FROM Employee obj WHERE obj.code = ?", new Object[]{employeeData.getCode()}, null);
							profileData.setName(profileList.get(0).getName());
							employeeDataParent.setProfileData(profileData);
							employeeData.setEmployeeDataParent(employeeDataParent);
							AreaData areaData;
							if(RoleConstant.ROLE_SALESMAN.equals(employeeData.getOccupationData().getCode())){
								List<Area> areaList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT obj.employeeParent.area FROM Employee obj WHERE obj.code = ?", new Object[]{employeeData.getCode()}, null);
								areaData = new AreaData(areaList.get(0));
								areaList = null;
							}else if(RoleConstant.ROLE_AREASALESMANAGER.equals(employeeData.getOccupationData().getCode())){
								List<Area> areaList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT obj.area FROM Employee obj WHERE obj.code = ?", new Object[]{employeeData.getCode()}, null);
								areaData = new AreaData(areaList.get(0));
								areaList = null;
							}else
								areaData = null;
							employeeData.setAreaData(areaData);
							employeeDataParent = null;
						}
						employeeDatas.add(employeeData);
						employeeData = null;
						profileList = null;
						profileData = null;
						userList = null;
						userData = null;
					}
					employeeTransferObject.setEmployeeDatas(employeeDatas);
					employeeList = null;
					employeeDatas = null;
					pageArgument = null;
				}
				employeeTransferObject.setPositionPage(getPositionPage());
				employeeTransferObject.setRowPerPage(getRowPerPage());
				employeeTransferObject.setTotalPage(getTotalPage());
				employeeTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
				employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
				employeeTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
				generalPagingTransferObject = null;
			}			
		}catch (Exception e){
			LOGGER.error("Load Paging Employee System Error : "+e.getLocalizedMessage(), e);
			employeeTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			employeeTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString()+e.getLocalizedMessage());
		}		
		setReturnStatusAndMessage(employeeTransferObject, responseHeaderMap);
		Message<EmployeeTransferObject> message = new GenericMessage<EmployeeTransferObject>(employeeTransferObject, responseHeaderMap);
		employeeTransferObject = null;
		return message;		
	}

	@Override
	protected Long getTotalRowVcsTable(FilterArgument[] filterArguments, Object[] values) throws Exception {
		return iGenericManagerJpa.totalRowData(Employee.class, filterArguments, values, null, null);
	}

	@Override
	protected String getTableName() {
		return Employee.TABLE_NAME;
	}
	
}
