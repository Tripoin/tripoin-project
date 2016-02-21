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
import com.tripoin.core.dto.EmployeeTransferObject.EnumFieldEmployee;
import com.tripoin.core.dto.OccupationTransferObject.EnumFieldOccupation;
import com.tripoin.core.pojo.Employee;
import com.tripoin.core.pojo.Occupation;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.Role;
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

	@Secured({RoleConstant.ROLE_SALESMAN, RoleConstant.ROLE_AREASALESMANAGER, RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<EmployeeTransferObject> updateEmployee(Message<EmployeeTransferObject> inMessage) {
    	EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
        try {
        	EmployeeTransferObject datasTransmit = inMessage.getPayload();
        	Role role = new Role();
        	User user = new User();
        	Profile profile = new Profile();
        	Occupation occupation = new Occupation();
        	Employee employee = new Employee();
        	if(datasTransmit.getFindEmployeeData() != null){
                FilterArgument[] filterArguments = new FilterArgument[] { 
        				new FilterArgument(EnumFieldEmployee.USERNAME_EMPLOYE.toString(), ECommonOperator.EQUALS) 
        		};
                employee.setNik((String)datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.NIK_EMPLOYE.toString()));
                profile.setName((String)datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.NAME_EMPLOYE.toString()));
        		List<Employee> employeeList = iGenericManagerJpa.loadObjectsFilterArgument(Employee.class, filterArguments, new Object[] { datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.USERNAME_EMPLOYE.toString()) }, null, null);
        		if (employeeList != null) {
                    FilterArgument[] filterArgumentsRole = new FilterArgument[] { 
            				new FilterArgument("code", ECommonOperator.EQUALS) 
            		};
                    List<Role> roleList =  iGenericManagerJpa.loadObjectsFilterArgument(Role.class, filterArgumentsRole, new Object[] { datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.OCCUPATION_CODE.toString()) }, null, null);
                    if(roleList == null || roleList.isEmpty())
                    	roleList =  iGenericManagerJpa.loadObjectsFilterArgument(Role.class, filterArgumentsRole, new Object[] { RoleConstant.ROLE_SALESMAN }, null, null);
                    role = roleList.get(0);
                	user = employeeList.get(0).getProfile().getUser();
                	profile = employeeList.get(0).getProfile();
                	FilterArgument[] filterArgumentsOccupation = new FilterArgument[] { 
            				new FilterArgument(EnumFieldOccupation.CODE_OCCUPATION.toString(), ECommonOperator.EQUALS) 
            		};
                	occupation =  iGenericManagerJpa.loadObjectsFilterArgument(Occupation.class, filterArgumentsOccupation, new Object[] { datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.OCCUPATION_CODE.toString()) }, null, null).get(0);
                	employee = employeeList.get(0);
                }        		
        	}        	
        	user.setRole(role);
        	iGenericManagerJpa.updateObject(user);    		
        	profile.setUser(user);
        	profile.setModifiedBy(currentUserName);
        	profile.setModifiedTime(new Date());
        	if(profile.getModifiedIP() == null)
        		profile.setModifiedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
        	if(profile.getModifiedTime() == null)
            	profile.setModifiedTime(new Date());
        	if(profile.getModifiedPlatform() == null)
        		profile.setModifiedPlatform(ParameterConstant.PLATFORM_DEFAULT);
    		iGenericManagerJpa.updateObject(profile);    		
    		employee.setProfile(profile);
    		employee.setOccupation(occupation);
        	employee.setModifiedBy(currentUserName);
        	if(employee.getModifiedIP() == null)
        		employee.setModifiedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
        	if(employee.getModifiedTime() == null)
        		employee.setModifiedTime(new Date());
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
