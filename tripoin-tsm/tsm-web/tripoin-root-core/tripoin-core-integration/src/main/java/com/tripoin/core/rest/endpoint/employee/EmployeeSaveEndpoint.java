package com.tripoin.core.rest.endpoint.employee;

import java.io.File;
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
import com.tripoin.core.pojo.Role;
import com.tripoin.core.pojo.SystemParameter;
import com.tripoin.core.pojo.User;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.ISystemParameterService;
import com.tripoin.util.mail.ICoreMailSender;

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
	
	@Autowired
	private ICoreMailSender iCoreMailSender;
	
	@Autowired
	private ISystemParameterService systemParameterService;
    
	private String currentUserName;	

	@Value("${tripoin.email.from}")
	private String emailFrom;
	
	@Value("${path.image}")
	private String rootPath;
	
    @Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
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
            	    String passwordPlainText = randomGeneratorAlphanumeric(7);
                    user.setPassword(jasyptStringDigester.digest(passwordPlainText));
                    Object[] data;
                    if(RoleConstant.ROLE_AREASALESMANAGER.toString().equals(employeeDataPayload.getOccupationData().getCode()) ||
                    		RoleConstant.ROLE_AREASALESMANAGER.toString().equals(employeeDataPayload.getOccupationData().getCode())){
                    	data = new Object[]{employeeDataPayload.getOccupationData().getCode()};
                    }else data = new Object[]{RoleConstant.ROLE_SALESMAN.toString()};
                    List<Role> roles = iGenericManagerJpa.loadObjectsFilterArgument(Role.class, new FilterArgument[]{
    					new FilterArgument("code", ECommonOperator.EQUALS)
        			}, data, null, null);
                	user.setRole(roles.get(0));
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
                    
                    List<SystemParameter> systemParameters = systemParameterService.listValue(new Object[]{ParameterConstant.NEW_USER_SUBJECT, ParameterConstant.NEW_USER_BODY});
				    Map<String, String> mapSystemParamter = new HashMap<String, String>();
				    for(SystemParameter systemParameter : systemParameters)
				    	mapSystemParamter.put(systemParameter.getCode(), systemParameter.getValue());
				    String content = mapSystemParamter.get(ParameterConstant.NEW_USER_BODY);
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_FULLNAME, profile.getName());
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_USERNAME, profile.getUser().getUsername());
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_PASSWORD, passwordPlainText);
                    iCoreMailSender.sendMailContent(emailFrom, employee.getProfile().getEmail(), mapSystemParamter.get(ParameterConstant.NEW_USER_SUBJECT), content);
                    
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
	
	private static String randomGeneratorAlphanumeric(int length){
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuffer buffer = new StringBuffer();
		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}

}
