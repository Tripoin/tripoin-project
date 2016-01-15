package com.tripoin.core.rest.endpoint.employee;

import java.util.Date;
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
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.pojo.Employee;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.User;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("employeeUpdateEndpoint")
public class EmployeeUpdateEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeUpdateEndpoint.class);

    @Autowired
    private IGenericManagerJpa iGenericManagerJpa;
    
	private String currentUserName;

	@Secured({RoleConstant.ROLE_SALESMAN, RoleConstant.ROLE_SALESSUPERVISOR, RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<EmployeeTransferObject> updateEmployee(Message<EmployeeData> inMessage) {
    	EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
        try {
        	Employee employee = new Employee(inMessage.getPayload());
        	User user = employee.getProfile().getUser();
        	Profile profile = employee.getProfile();
        	if(employee != null && employee.getId() == null){
                FilterArgument[] filterArguments = new FilterArgument[] { 
        				new FilterArgument("profile.user.username", ECommonOperator.EQUALS) 
        		};
        		List<Employee> employeeList = iGenericManagerJpa.loadObjectsFilterArgument(Employee.class, filterArguments, new Object[] { employee.getProfile().getUser().getUsername() }, null, null);    		
        		if (employeeList != null) {
                    for (Employee employeeTemp : employeeList){
                    	user.setId(employeeTemp.getProfile().getUser().getId());
                    	profile.setId(employeeTemp.getProfile().getId());
                    	employee.setId(employeeTemp.getId());
                    }                    	
                }        		
        	}        	
        	iGenericManagerJpa.updateObject(user);    		
        	profile.setUser(user);
        	profile.setModifiedBy(currentUserName);
        	profile.setModifiedTime(new Date());
        	if(profile.getModifiedIP() == null)
        		profile.setModifiedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
        	if(profile.getModifiedPlatform() == null)
        		profile.setModifiedPlatform(ParameterConstant.PLATFORM_DEFAULT);
    		iGenericManagerJpa.updateObject(profile);    		
    		employee.setProfile(profile);
        	employee.setModifiedBy(currentUserName);
        	employee.setModifiedTime(profile.getModifiedTime());
        	if(employee.getModifiedIP() == null)
        		employee.setModifiedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
        	if(employee.getModifiedPlatform() == null)
        		employee.setModifiedPlatform(ParameterConstant.PLATFORM_DEFAULT);        	
    		iGenericManagerJpa.updateObject(employee);
    		
            employeeTransferObject.setResponseCode("0");
            employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
            employeeTransferObject.setResponseDesc("Update Employee Data Success");
        } catch (Exception e) {
            LOGGER.error("Update Employee System Error : " + e.getMessage(), e);
            employeeTransferObject.setResponseCode("1");
            employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            employeeTransferObject.setResponseDesc("Update Employee System Error : " + e.getMessage());
        }
        setReturnStatusAndMessage(employeeTransferObject, responseHeaderMap);
        Message<EmployeeTransferObject> message = new GenericMessage<EmployeeTransferObject>(employeeTransferObject, responseHeaderMap);
        return message;
    }

}
