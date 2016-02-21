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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.EmployeeTransferObject.EnumFieldEmployee;
import com.tripoin.core.dto.OccupationTransferObject.EnumFieldOccupation;
import com.tripoin.core.dto.exception.WSEndpointFault;
import com.tripoin.core.pojo.Employee;
import com.tripoin.core.pojo.Occupation;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.Role;
import com.tripoin.core.pojo.SystemParameter;
import com.tripoin.core.pojo.User;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.soap.handler.WSEndpointFaultException;
import com.tripoin.core.service.util.ISystemParameterService;
import com.tripoin.core.service.util.IVersionControlSystemTableService;
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
    
    @Autowired
    private IVersionControlSystemTableService versionControlSystemTableService;
    
	@Autowired
	@Qualifier(value="transactionManager")
	private PlatformTransactionManager transactionManager ;
	
	@Autowired
	@Qualifier(value="web-async-task-executor")
	private ThreadPoolTaskExecutor taskExecutor; 
    
	private String currentUserName;	
	
	private WSEndpointFault wsEndpointFault = new WSEndpointFault();

	@Value("${tripoin.email.from}")
	private String emailFrom;
	
	@Value("${path.image}")
	private String rootPath;

	private Role role;
	private User user;
	private Profile profile;
	private Occupation occupation;
	private Employee employee;
	private String passwordPlainText;
	
    @Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<EmployeeTransferObject> saveEmployee(Message<EmployeeTransferObject> inMessage) {
    	EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
        try {
        	EmployeeTransferObject datasTransmit = inMessage.getPayload();
        	Map<String, Object> findDataEmployee = datasTransmit.getFindEmployeeData();
        	role = new Role();
        	user = new User();
        	profile = new Profile();
        	occupation = new Occupation();
        	employee = new Employee();
            if(findDataEmployee != null){
            	String name = (String)findDataEmployee.get(EnumFieldEmployee.NAME_EMPLOYE.toString());
            	String nik = (String)findDataEmployee.get(EnumFieldEmployee.NIK_EMPLOYE.toString());
            	String username = (String)findDataEmployee.get(EnumFieldEmployee.USERNAME_EMPLOYE.toString());
            	String birthPlace = (String)findDataEmployee.get(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString());
            	Date birthDate = ParameterConstant.FORMAT_DEFAULT.parse((String)findDataEmployee.get(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString()));
            	String gender = ((String)findDataEmployee.get(EnumFieldEmployee.GENDER_EMPLOYE.toString())).toUpperCase();
            	String mobilePhone = (String)findDataEmployee.get(EnumFieldEmployee.PHONE_EMPLOYE.toString());
            	String homePhone = (String)findDataEmployee.get(EnumFieldEmployee.TELP_EMPLOYE.toString());
            	String email = (String)findDataEmployee.get(EnumFieldEmployee.EMAIL_EMPLOYE.toString());
            	String address = (String)findDataEmployee.get(EnumFieldEmployee.ADDRESS_EMPLOYE.toString());
            	Integer enabled = ((Double)findDataEmployee.get(EnumFieldEmployee.ENABLE_EMPLOYE.toString())).intValue();
            	FilterArgument[] filterArguments = new FilterArgument[]{
    					new FilterArgument(EnumFieldEmployee.USERNAME_EMPLOYE.toString(), ECommonOperator.EQUALS)
    			};
    			List<User> userListCheck = iGenericManagerJpa.loadObjectsFilterArgument(User.class, filterArguments, new Object[]{findDataEmployee.get(EnumFieldEmployee.USERNAME_EMPLOYE.toString())}, null, null);
    			if(userListCheck == null || userListCheck.size() == 0){      
    				/**
    				 * Set User
    				 */
    				FilterArgument[] filterArgumentsRole = new FilterArgument[] { 
            				new FilterArgument("code", ECommonOperator.EQUALS) 
            		};
                    List<Role> roleList =  iGenericManagerJpa.loadObjectsFilterArgument(Role.class, filterArgumentsRole, new Object[] { datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.OCCUPATION_CODE.toString()) }, null, null);
                    if(roleList == null || roleList.isEmpty())
                    	roleList =  iGenericManagerJpa.loadObjectsFilterArgument(Role.class, filterArgumentsRole, new Object[] { RoleConstant.ROLE_SALESMAN }, null, null);
                    role = roleList.get(0);
                    user.setRole(role);
                    user.setUsername(username);
            	    passwordPlainText = randomGeneratorAlphanumeric(7);
                    user.setPassword(jasyptStringDigester.digest(passwordPlainText));
                    user.setEnabled(enabled);
                    user.setStatus(1);
                    iGenericManagerJpa.saveObject(user);
                    /**
                     * Set Profile
                     */
                	if(datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString()) != null){
                    	List<Profile> profileListCheckMobileEmail = iGenericManagerJpa.loadObjectsJQLStatement("FROM Profile WHERE email = ? OR phone = ?",  new Object[] { datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.EMAIL_EMPLOYE.toString()), 
                    			datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.PHONE_EMPLOYE.toString()) }, null);
                    	if(profileListCheckMobileEmail != null && !profileListCheckMobileEmail.isEmpty()){
            				wsEndpointFault.setMessage("Contact Email and Mobile Phone Already Exists");
            				throw new WSEndpointFaultException("4", wsEndpointFault);	
                    	}
                	}
                    profile.setUser(user);
                    profile.setName(name);
                    profile.setEmail(email);
                    profile.setGender(gender);
                    profile.setBirthplace(birthPlace);
                    profile.setBirthdate(birthDate);
                    profile.setAddress(address);
                    profile.setTelp(homePhone);
                    profile.setPhone(mobilePhone);
                    UUID uuid = UUID.randomUUID();
                    profile.setResourcesUUID(uuid.toString());
                    File tempDir = new File(rootPath.concat(profile.getResourcesUUID()));
                    if(!tempDir.exists())
        				tempDir.mkdirs();
                    profile.setCreatedBy(currentUserName);
                	if(profile.getCreatedIP() == null)
                		profile.setCreatedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
                	if(profile.getCreatedTime() == null)
                		profile.setCreatedTime(new Date());
                	if(profile.getCreatedPlatform() == null)
                		profile.setCreatedPlatform(ParameterConstant.PLATFORM_DEFAULT);
                    iGenericManagerJpa.saveObject(profile);            
                    /**
                     * Set Occupation
                     */
                    FilterArgument[] filterArgumentsOccupation = new FilterArgument[] { 
            				new FilterArgument(EnumFieldOccupation.CODE_OCCUPATION.toString(), ECommonOperator.EQUALS) 
            		};
                	occupation =  iGenericManagerJpa.loadObjectsFilterArgument(Occupation.class, filterArgumentsOccupation, new Object[] { datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.OCCUPATION_CODE.toString()) }, null, null).get(0);
                	/**
                	 * Set Employee Parent
                	 */
                	if(datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString()) != null){
                    	FilterArgument[] filterArgumentsParentEmployee = new FilterArgument[] { 
                				new FilterArgument(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString(), ECommonOperator.EQUALS) 
                		};
                    	List<Employee> employeeParentList = iGenericManagerJpa.loadObjectsFilterArgument(Employee.class, filterArgumentsParentEmployee, new Object[] { datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString()) }, null, null);
                    	if(employeeParentList != null && !employeeParentList.isEmpty())
                    		employee.setEmployeeParent(employeeParentList.get(0));
                	}
                	/**
                	 * Set Employee
                	 */
                	if(datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString()) != null){
                    	FilterArgument[] filterArgumentsNikEmployee = new FilterArgument[] { 
                				new FilterArgument(EnumFieldEmployee.NIK_EMPLOYE.toString(), ECommonOperator.EQUALS) 
                		};
                    	List<Employee> employeeListCheckNik = iGenericManagerJpa.loadObjectsFilterArgument(Employee.class, filterArgumentsNikEmployee, new Object[] { datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.NIK_EMPLOYE.toString()) }, null, null);
                    	if(employeeListCheckNik != null && !employeeListCheckNik.isEmpty()){
            				wsEndpointFault.setMessage("NIK Already Exists");
            				throw new WSEndpointFaultException("3", wsEndpointFault);	
                    	}else
                            employee.setNik(nik);
                	}
                	employee.setProfile(profile);
                	employee.setOccupation(occupation);
                    employee.setCode(nik);
                    employee.setRemarks(nik);
                    employee.setStatus(1);
                    employee.setCreatedBy(currentUserName);
                	if(employee.getCreatedIP() == null)
                		employee.setCreatedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
                	if(employee.getCreatedTime() == null)
                        employee.setCreatedTime(profile.getCreatedTime());
                	if(employee.getCreatedPlatform() == null)
                		employee.setCreatedPlatform(ParameterConstant.PLATFORM_DEFAULT);
                    iGenericManagerJpa.saveObject(employee);                   
                    
                    taskExecutor.execute(new Runnable() {			
	        			@Override
	        			public void run() {
	        				final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
	        				transactionTemplate.execute(new TransactionCallback<Object>() {
	        					@Override
	        					public Object doInTransaction(TransactionStatus arg0) {
	        						try {
	        		                    List<SystemParameter> systemParameters = systemParameterService.listValue(new Object[]{ParameterConstant.NEW_USER_SUBJECT, ParameterConstant.NEW_USER_BODY});
	        						    Map<String, String> mapSystemParamter = new HashMap<String, String>();
	        						    for(SystemParameter systemParameter : systemParameters)
	        						    	mapSystemParamter.put(systemParameter.getCode(), systemParameter.getValue());
	        						    String content = mapSystemParamter.get(ParameterConstant.NEW_USER_BODY);
	        						    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_FULLNAME, profile.getName());
	        						    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_USERNAME, profile.getUser().getUsername());
	        						    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_PASSWORD, passwordPlainText);
	        		                    iCoreMailSender.sendMailContent(emailFrom, employee.getProfile().getEmail(), mapSystemParamter.get(ParameterConstant.NEW_USER_SUBJECT), content);
	        		                    
	        							versionControlSystemTableService.insertValueAndSync(Employee.TABLE_NAME, new Long(1), "Table of ".concat(Employee.TABLE_NAME));
	        						} catch (Exception e) {
	        							LOGGER.error("Save Employee System Error : " + e.getLocalizedMessage(), e);
	        						}
	        						return null;
	        					}
	        				});
	        			}
	        		});
                    employeeTransferObject.setResponseCode("0");
                    employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
                    employeeTransferObject.setResponseDesc("Save Employee Data Success");
    			}else{
    				wsEndpointFault.setMessage("Username Already Exists");
    				throw new WSEndpointFaultException("2", wsEndpointFault);
    			}
        	}else
				throw new Exception();
        } catch (WSEndpointFaultException e) {
            employeeTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            employeeTransferObject.setResponseDesc(e.getFaultInfo().getMessage());	
            if("2".equals(e.getMessage()))
    			employeeTransferObject.setResponseCode("2");
        	else if("3".equals(e.getMessage()))
    			employeeTransferObject.setResponseCode("3");	
        	else if("4".equals(e.getMessage()))
    			employeeTransferObject.setResponseCode("4");
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
