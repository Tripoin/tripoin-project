package com.tripoin.core.rest.endpoint.employee;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

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

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ValueArgument;
import com.tripoin.core.dto.EmployeeTransferObject.EnumFieldEmployee;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.exception.WSEndpointFault;
import com.tripoin.core.pojo.Area;
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
	
	@Autowired
    private ServletContext context;
    
	private String currentUserName;	
	
	private WSEndpointFault wsEndpointFault = new WSEndpointFault();

	@Value("${tripoin.email.from}")
	private String emailFrom;
	
	@Value("${path.image}")
	private String rootPath;
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/employee/save</code><br>
	 * @param inMessage
	 * @return
	 */
    @Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<GeneralTransferObject> saveEmployee(Message<GeneralTransferObject> inMessage) {
    	GeneralTransferObject generalTransferObject = new GeneralTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		authentication = null;
        try {
        	GeneralTransferObject datasTransmit = inMessage.getPayload();
        	final Map<String, Object> findDataEmployee = datasTransmit.getParameterData();
        	Integer employeeParentId = null;
        	Integer roleId = null;
        	Integer occupationId = null;
        	Integer areaId = null;
        	if(findDataEmployee != null && !findDataEmployee.isEmpty()){
        		LOGGER.info(findDataEmployee.toString());
            	final String name = (String)findDataEmployee.get(EnumFieldEmployee.NAME_EMPLOYE.toString());
            	final String nik = (String)findDataEmployee.get(EnumFieldEmployee.NIK_EMPLOYE.toString());
            	String employeeParentNik = (String)findDataEmployee.get(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString());
            	final String username = (String)findDataEmployee.get(EnumFieldEmployee.USERNAME_EMPLOYE.toString());
            	String birthPlace = (String)findDataEmployee.get(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString());
            	java.sql.Date birthDate = new java.sql.Date(ParameterConstant.FORMAT_DEFAULT.parse((String)findDataEmployee.get(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString())).getTime());
            	String gender = ((String)findDataEmployee.get(EnumFieldEmployee.GENDER_EMPLOYE.toString()));
            	final String mobilePhone = (String)findDataEmployee.get(EnumFieldEmployee.PHONE_EMPLOYE.toString());
            	String homePhone = (String)findDataEmployee.get(EnumFieldEmployee.TELP_EMPLOYE.toString());
            	final String email = (String)findDataEmployee.get(EnumFieldEmployee.EMAIL_EMPLOYE.toString());
            	String address = (String)findDataEmployee.get(EnumFieldEmployee.ADDRESS_EMPLOYE.toString());
            	String occupationCode = (String)findDataEmployee.get(EnumFieldEmployee.OCCUPATION_CODE.toString());
            	String areaCode = (String)findDataEmployee.get(EnumFieldEmployee.AREA_CODE.toString());
            	String identifierIP = (String)findDataEmployee.get(ParameterConstant.IDENTIFIER_IP.toString());
            	Timestamp identifierTime = new Timestamp(new Date().getTime());;
            	String identifierPlatform = (String)findDataEmployee.get(ParameterConstant.IDENTIFIER_PLATFORM.toString());
            	// TODO Validate Field NULL
            	if(nik == null || name == null || birthDate == null || birthPlace == null || username == null ||
            			gender == null || mobilePhone == null || email == null || address == null || occupationCode == null){
                	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString()+"Field not null!");
    				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);            		
            	}
            	// TODO Validate Gender  
    			if(!(ParameterConstant.FEMALE.equals(gender) || ParameterConstant.MALE.equals(gender))){
                	wsEndpointFault.setMessage(EResponseCode.RC_GENDER_NOT_DEFINE.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_GENDER_NOT_DEFINE.getResponseCode(), wsEndpointFault);
    			}
            	// TODO Validate User/NIK
        		List<Employee> employeeList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT em FROM Employee em "
        				+ "WHERE em.profile.user.username = ? OR em.nik = ? OR em.profile.email = ?", 
        				new Object[]{username, nik, email}, null);
            	if(employeeList == null || employeeList.size() == 0){
            		
            		/**
        			 *  TODO Validate Employee Parent/Role/Occupation/Area and Set Employee Parent/Role/Occupation/Area
        			 */
        			if(occupationCode != null){
        				// TODO Validate Employee Parent and Set Employee Parent
        				if(employeeParentNik != null && !RoleConstant.ROLE_NATIONALSALESMANAGER.equals(occupationCode)){
            				if(employeeParentNik != nik){
                             	List<Employee> employeeParentList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT em FROM Employee em "
                        				+ "WHERE em.nik = ?", 
                             			new Object[] { employeeParentNik }, null);
                             	if(employeeParentList != null && !employeeParentList.isEmpty()){
                             		Employee employeeParent = employeeParentList.get(0);
                             		employeeParentId = employeeParent.getId();
                                 	employeeParentList = null;
                                 	if(occupationCode.equals(employeeParent.getOccupation().getCode()) ||
                                 			(RoleConstant.ROLE_AREASALESMANAGER.equals(occupationCode) &&
                                         			RoleConstant.ROLE_SALESMAN.equals(employeeParent.getOccupation().getCode()))){
                                    	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString()+"Head Occupation must be higher");
                        				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
                                 	}
                                 	employeeParent = null;
                             	}else {
                                	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
                    				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
         						}	
            				}else {
                            	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString()+" NIK Current and NIK Head not be same!");
                				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
     						}
                     	}else{
        					if(!RoleConstant.ROLE_NATIONALSALESMANAGER.equals(occupationCode)){
                            	wsEndpointFault.setMessage(EResponseCode.RC_EMPLOYEE_PARENT_NOTNULL.toString());
                				throw new WSEndpointFaultException(EResponseCode.RC_EMPLOYEE_PARENT_NOTNULL.getResponseCode(), wsEndpointFault);	
        					}
                     	}
        				// TODO Validate Role and Set Role
                     	List<Role> roleList = iGenericManagerJpa.loadObjectsJQLStatement("FROM Role WHERE code = ?", 
                     			new Object[] { occupationCode }, null);
                     	if(roleList!=null && !roleList.isEmpty()){
                     		Role role = roleList.get(0);
                     		roleId = role.getId();
                         	roleList = null;
                         	// TODO Validate Occupation and Set Occupation
                         	List<Occupation> occupationList = iGenericManagerJpa.loadObjectsJQLStatement("FROM Occupation WHERE code = ?", 
                         			new Object[] { role.getCode() }, null);
                         	if(occupationList != null && !occupationList.isEmpty()){
                         		occupationId = occupationList.get(0).getId();
                             	occupationList = null;
                             	role = null;
                         	}else{
                            	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
                				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
     						}
                     	}else{
                        	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
            				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
 						}
                     	// TODO Validate Area and Set Area
                     	if(RoleConstant.ROLE_AREASALESMANAGER.equals(occupationCode)){
                     		if(areaCode != null){
                     			List<Area> areaList = iGenericManagerJpa.loadObjectsJQLStatement("FROM Area WHERE code = ?", 
                             			new Object[] { areaCode }, null);
                     			if(areaList.get(0) != null && !areaCode.isEmpty()){
                     				areaId = areaList.get(0).getId();
                         			areaList = null;
                     			}else{
                                	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
                    				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
                     			}
                     		}else{
                            	wsEndpointFault.setMessage(EResponseCode.RC_AREA_NOTNULL.toString());
                				throw new WSEndpointFaultException(EResponseCode.RC_AREA_NOTNULL.getResponseCode(), wsEndpointFault);
                     		}
                     	}
                 	}
        			if(identifierIP == null)
                		identifierIP = ParameterConstant.IP_ADDRESSV4_DEFAULT;
                	if(identifierPlatform == null)
                		identifierPlatform = ParameterConstant.PLATFORM_DEFAULT;
            	    final String passwordPlainText = randomGeneratorAlphanumeric(7);
            		String passwordEncrypt = jasyptStringDigester.digest(passwordPlainText);
            		final UUID uuid = UUID.randomUUID();
            		
                	ValueArgument[] valueArgumentsUser = new ValueArgument[]{
        					new ValueArgument("username", username),
        					new ValueArgument("password", passwordEncrypt),
        					new ValueArgument("enabled", 1),
        					new ValueArgument("status", 1),
        					new ValueArgument("role", roleId)
        			};
                	iGenericManagerJpa.execQueryNotCriteria("INSERT INTO sec_user "
                			+ "(user_username, user_password, user_enabled, user_status, role_id) "
                			+ "VALUES(:username, :password, :enabled, :status, :role)", valueArgumentsUser);
                	
                	List<User> getUserId = iGenericManagerJpa.loadObjectsJQLStatement("FROM User WHERE username = ?", new Object[]{username}, null);
                	ValueArgument[] valueArgumentsProfile = new ValueArgument[]{
        					new ValueArgument("email", email),
        					new ValueArgument("name", name),
        					new ValueArgument("gender", gender),
        					new ValueArgument("birthPlace", birthPlace),
        					new ValueArgument("birthDate", birthDate),
        					new ValueArgument("address", address),
        					new ValueArgument("homePhone", homePhone),
        					new ValueArgument("user", getUserId.get(0).getId()),
        					new ValueArgument("mobilePhone", mobilePhone),
        					new ValueArgument("resources", uuid.toString()),
        					new ValueArgument("createdBy", currentUserName),
        					new ValueArgument("createdIP", identifierIP),
        					new ValueArgument("createdTime", identifierTime),
        					new ValueArgument("createdPlatform", identifierPlatform)
        			};
                	iGenericManagerJpa.execQueryNotCriteria("INSERT INTO mst_profile "
                			+ "(profile_email, profile_name, profile_gender, profile_birthplace, profile_birthdate, profile_address, "
                			+ "profile_telp, user_id, profile_phone, profile_resources_uuid, "
                			+ "created_by, created_ip, created_time, created_platform) "
                			+ "VALUES(:email, :name, :gender, :birthPlace, :birthDate, :address, "
                			+ ":homePhone, :user, :mobilePhone, :resources, "
                			+ ":createdBy, :createdIP, :createdTime, :createdPlatform)", valueArgumentsProfile);
                	
                	List<Profile> getProfileId = iGenericManagerJpa.loadObjectsJQLStatement("FROM Profile WHERE email = ?", new Object[]{email}, null);
                	ValueArgument[] valueArgumentsEmployee = new ValueArgument[]{
        					new ValueArgument("code", nik),
        					new ValueArgument("nik", nik),
        					new ValueArgument("profile", getProfileId.get(0).getId()),
        					new ValueArgument("occupation", occupationId),
        					new ValueArgument("area", areaId),
        					new ValueArgument("employeeParent", employeeParentId),
        					new ValueArgument("status", 1),
        					new ValueArgument("remarks", nik),
        					new ValueArgument("createdBy", currentUserName),
        					new ValueArgument("createdIP", identifierIP),
        					new ValueArgument("createdTime", identifierTime),
        					new ValueArgument("createdPlatform", identifierPlatform)
        			};
                	iGenericManagerJpa.execQueryNotCriteria("INSERT INTO mst_employee "
                			+ "(employee_code, employee_nik, profile_id, occupation_id, area_id, employee_parent_id, "
                			+ "status, remarks, created_by, created_ip, created_time, created_platform) "
                			+ "VALUES(:code, :nik, :profile, :occupation, :area, :employeeParent, "
                			+ ":status, :remarks, :createdBy, :createdIP, :createdTime, :createdPlatform)", valueArgumentsEmployee);
                	
                    taskExecutor.execute(new Runnable() {			
	        			@Override
	        			public void run() {
	        				final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
	        				transactionTemplate.execute(new TransactionCallback<Object>() {
	        					@Override
	        					public Object doInTransaction(TransactionStatus arg0) {
	        						try {
	        							versionControlSystemTableService.insertValueAndSync(Employee.TABLE_NAME, new Long(1), "Table of ".concat(Employee.TABLE_NAME));
	        							
	        							File tempDir = new File(context.getRealPath(rootPath.concat(uuid.toString())));
	        		                    if(!tempDir.exists())
	        		        				tempDir.mkdirs();
	        		                    
	        		                    List<SystemParameter> systemParameters = systemParameterService.listValue(new Object[]{ParameterConstant.NEW_USER_SUBJECT, ParameterConstant.NEW_USER_BODY});
	        						    Map<String, String> mapSystemParamter = new HashMap<String, String>();
	        						    for(SystemParameter systemParameter : systemParameters)
	        						    	mapSystemParamter.put(systemParameter.getCode(), systemParameter.getValue());
	        						    String content = mapSystemParamter.get(ParameterConstant.NEW_USER_BODY);
	        						    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_FULLNAME, name);
	        						    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_USERNAME, username);
	        						    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_PASSWORD, passwordPlainText);
	        		                    iCoreMailSender.sendMailContent(emailFrom, email, mapSystemParamter.get(ParameterConstant.NEW_USER_SUBJECT), content);
	        						} catch (Exception e) {
	        							LOGGER.error("Save Employee System Error : " + e.getLocalizedMessage(), e);
	        						}
	        						return null;
	        					}
	        				});
	        			}
	        		});
                    generalTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
                    generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
                    generalTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());

                	employeeParentNik = null;
                	birthPlace = null;
                	birthDate = null;
                	gender = null;
                	homePhone = null;
                	occupationCode = null;
                	areaCode = null;
                	identifierIP = null;
                	identifierTime = null;
                	identifierPlatform = null;
    			}else{
    				employeeList = null;
                	wsEndpointFault.setMessage(EResponseCode.RC_USERNAME_NIK_EMAIL_EXISTS.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_USERNAME_NIK_EMAIL_EXISTS.getResponseCode(), wsEndpointFault);
    			}
        	}else
				throw new Exception();
        } catch (WSEndpointFaultException e) {
            generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            generalTransferObject.setResponseDesc(e.getFaultInfo().getMessage());	
            generalTransferObject.setResponseCode(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Save Employee System Error : " + e.getLocalizedMessage(), e);
            generalTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
            generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            generalTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString() + e.getLocalizedMessage());
        }

        setReturnStatusAndMessage(generalTransferObject, responseHeaderMap);
        Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(generalTransferObject, responseHeaderMap);
        generalTransferObject = null;
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
