package com.tripoin.core.rest.endpoint.profile;

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

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.ProfileData;
import com.tripoin.core.dto.ProfileTransferObject;
import com.tripoin.core.dto.UserData;
import com.tripoin.core.pojo.Employee;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.User;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("profileLoadEndpoint")
public class ProfileLoadEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(ProfileLoadEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	private String currentUserName;

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/profile/load</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_SALESMAN, RoleConstant.ROLE_AREASALESMANAGER, RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<ProfileTransferObject> getProfile(Message<?> inMessage){	
		ProfileTransferObject profileTransferObject = new ProfileTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken))
		    this.currentUserName = authentication.getName();
		authentication = null;
		try{
			List<Profile> profileList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT pr FROM Profile pr WHERE pr.user.username = ?", new Object[]{currentUserName}, null);
			List<User> userList = iGenericManagerJpa.loadObjectsJQLStatement("FROM User WHERE username = ?", new Object[]{currentUserName}, null);
			List<ProfileData> profileDatas = new ArrayList<ProfileData>();
			if(profileList != null && !profileList.isEmpty()){
				ProfileData profileData = new ProfileData(profileList.get(0));
				profileData.setUserData(new UserData(userList.get(0)));
				profileDatas.add(profileData);
				profileTransferObject.setProfileDatas(profileDatas);
				userList = null;
				profileData = null;
				profileList = null;
				profileDatas = null;
			}
			profileTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
			profileTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			profileTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());		
		}catch (Exception e){
			LOGGER.error("Load Profile System Error : "+e.getLocalizedMessage(), e);
			profileTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			profileTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			profileTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString()+e.getLocalizedMessage());
		}
		setReturnStatusAndMessage(profileTransferObject, responseHeaderMap);
		Message<ProfileTransferObject> message = new GenericMessage<ProfileTransferObject>(profileTransferObject, responseHeaderMap);
		profileTransferObject = null;
		currentUserName = null;
		return message;		
	}
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/profile/employee/load</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_SALESMAN, RoleConstant.ROLE_AREASALESMANAGER, RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<EmployeeTransferObject> getProfileEmployee(Message<?> inMessage){	
		EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken))
		    this.currentUserName = authentication.getName();
		authentication = null;		
		try{
			List<Employee> employeeList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT em FROM Employee em WHERE em.profile.user.username = ?", new Object[]{currentUserName}, null);
			List<Profile> profileList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT pr FROM Profile pr WHERE pr.user.username = ?", new Object[]{currentUserName}, null);
			List<User> userList = iGenericManagerJpa.loadObjectsJQLStatement("FROM User WHERE username = ?", new Object[]{currentUserName}, null);
			List<EmployeeData> employeeDatas = new ArrayList<EmployeeData>();
			if(profileList != null && !profileList.isEmpty()){
				EmployeeData employeeData = new EmployeeData();
				if(employeeList != null && !employeeList.isEmpty()){
					employeeData = new EmployeeData(employeeList.get(0));
					if(employeeList.get(0).getEmployeeParent() != null){
						List<Employee> employeeParentList = iGenericManagerJpa.loadObjectsJQLStatement("FROM Employee WHERE id = ?", new Object[]{employeeList.get(0).getEmployeeParent().getId()}, null);
						List<Profile> profileParentList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT em.profile FROM Employee em WHERE em.id = ?", new Object[]{employeeList.get(0).getEmployeeParent().getId()}, null);
						EmployeeData employeeParentData = new EmployeeData(employeeParentList.get(0));
						ProfileData profileParentData = new ProfileData();
						profileParentData.setName(profileParentList.get(0).getName());
						employeeParentData.setProfileData(profileParentData);
						employeeData.setEmployeeDataParent(employeeParentData);
						employeeParentList = null;
						profileParentList = null;
						employeeParentData = null;
						profileParentData = null;
					}
				}
				ProfileData profileData = new ProfileData(profileList.get(0));
				profileData.setUserData(new UserData(userList.get(0)));
				employeeData.setProfileData(profileData);
				employeeDatas.add(employeeData);
				employeeTransferObject.setEmployeeDatas(employeeDatas);
				userList = null;
				profileData = null;
				profileList = null;
				employeeList = null;
				employeeData = null;
				employeeDatas = null;
			}
			employeeTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
			employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			employeeTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());			
		}catch (Exception e){
			LOGGER.error("Load Profile System Error : "+e.getLocalizedMessage(), e);
			employeeTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			employeeTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString()+e.getLocalizedMessage());
		}
		setReturnStatusAndMessage(employeeTransferObject, responseHeaderMap);
		Message<EmployeeTransferObject> message = new GenericMessage<EmployeeTransferObject>(employeeTransferObject, responseHeaderMap);
		employeeTransferObject = null;
		currentUserName = null;
		return message;		
	}
	
}
