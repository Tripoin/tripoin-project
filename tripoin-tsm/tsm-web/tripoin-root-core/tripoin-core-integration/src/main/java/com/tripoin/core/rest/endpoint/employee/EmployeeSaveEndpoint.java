package com.tripoin.core.rest.endpoint.employee;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jasypt.digest.StandardStringDigester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.ProfileData;
import com.tripoin.core.dto.UserData;
import com.tripoin.core.pojo.Employee;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.User;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("employeeSaveEndpoint")
public class EmployeeSaveEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeSaveEndpoint.class);

    @Autowired
    private IGenericManagerJpa iGenericManagerJpa;

	@Autowired
	private StandardStringDigester jasyptStringDigester;
    
	private String currentUserName;
	
	@Value("${path.image}")
	private String rootPath;
	
    @Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<EmployeeTransferObject> saveEmployee(Message<EmployeeData> inMessage) {
    	EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
        try {
            EmployeeData employeeDataPayload = inMessage.getPayload();
            if(employeeDataPayload != null && employeeDataPayload.getProfileData().getUserData() != null){
            	FilterArgument[] filterArguments = new FilterArgument[]{
    					new FilterArgument("username", ECommonOperator.EQUALS)
    			};
    			List<User> userList = iGenericManagerJpa.loadObjectsFilterArgument(User.class, filterArguments, new Object[]{employeeDataPayload.getProfileData().getUserData().getUsername()}, null, null);
    			if(userList == null || userList.size() == 0){
                	UserData userDataPayload = employeeDataPayload.getProfileData().getUserData();       	
                    User user = new User(userDataPayload);
                	if(userDataPayload.getAuth() != null){
        	        	String basicAuthorization = userDataPayload.getAuth();
        	        	byte[] oauth = Base64.decode(basicAuthorization.getBytes());
        	        	userDataPayload.setAuth(null);
        	            user.setAuth(null);
        	            user.setPassword(jasyptStringDigester.digest(new String(oauth).split(":")[1]));	            
                	}        	
                    iGenericManagerJpa.saveObject(user);
                    
                	ProfileData profileDataPayload = employeeDataPayload.getProfileData();       	
                    Profile profile = new Profile(profileDataPayload);
                    profile.setUser(user);
                    UUID uuid = UUID.randomUUID();
                    profile.setResourcesUUID(uuid.toString());
                    File tempDir = new File(rootPath.concat(profile.getResourcesUUID()));
                    if(!tempDir.exists())
        				tempDir.mkdirs();
                    profile.setCreatedBy(currentUserName);
                    profile.setCreatedTime(new Date());
                	if(profile.getCreatedIP() == null)
                		profile.setCreatedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
                	if(profile.getCreatedPlatform() == null)
                		profile.setCreatedPlatform(ParameterConstant.PLATFORM_DEFAULT);
                    iGenericManagerJpa.saveObject(profile);            
                    
                    Employee employee = new Employee(employeeDataPayload);
                    employee.setCode(employee.getNik());
                    employee.setProfile(profile);
                    employee.setCreatedBy(currentUserName);
                    employee.setCreatedTime(profile.getCreatedTime());
                	if(employee.getCreatedIP() == null)
                		employee.setCreatedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
                	if(employee.getCreatedPlatform() == null)
                		employee.setCreatedPlatform(ParameterConstant.PLATFORM_DEFAULT);
                    iGenericManagerJpa.saveObject(employee);
                    
                    FilterArgument[] filterArgumentsEmployee = new FilterArgument[] { 
            				new FilterArgument("profile.user.username", ECommonOperator.EQUALS) 
            		};
            		List<Employee> employeeList = iGenericManagerJpa.loadObjectsFilterArgument(Employee.class, filterArgumentsEmployee, new Object[] { employeeDataPayload.getProfileData().getUserData().getUsername() }, null, null);
                    List<EmployeeData> employeeDatas = new ArrayList<EmployeeData>();
                    if (employeeList != null) {
                        for (Employee employeeTemp : employeeList)
                            employeeDatas.add(new EmployeeData(employeeTemp));
                        employeeTransferObject.setEmployeeDatas(employeeDatas);
                    }
                    employeeTransferObject.setResponseCode("0");
                    employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
                    employeeTransferObject.setResponseDesc("Save Employee Data Success");
    			}else{
    				employeeTransferObject.setResponseCode("2");
    	            employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
    	            employeeTransferObject.setResponseDesc("Username Already Exists");
    			}
        	}else{
				throw new Exception();
			}
        } catch (Exception e) {
            LOGGER.error("Save Employee System Error : " + e.getLocalizedMessage(), e);
            employeeTransferObject.setResponseCode("1");
            employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            employeeTransferObject.setResponseDesc("Save Employee System Error : " + e.getLocalizedMessage());
        }

        setReturnStatusAndMessage(employeeTransferObject, responseHeaderMap);
        Message<EmployeeTransferObject> message = new GenericMessage<EmployeeTransferObject>(employeeTransferObject, responseHeaderMap);
        return message;
    }

}
