package com.tripoin.core.rest.endpoint.employee;

import java.sql.Timestamp;
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
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.soap.handler.WSEndpointFaultException;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("employeeUpdateEndpoint")
public class EmployeeUpdateEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeUpdateEndpoint.class);

    @Autowired
    private IGenericManagerJpa iGenericManagerJpa;
    
	private String currentUserName;
	
	private WSEndpointFault wsEndpointFault = new WSEndpointFault();

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/employee/update</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<GeneralTransferObject> updateEmployee(Message<GeneralTransferObject> inMessage) {
		GeneralTransferObject generalTransferObject = new GeneralTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		authentication = null;
        try {
        	GeneralTransferObject datasTransmit = inMessage.getPayload();
        	Map<String, Object> findDataEmployee = datasTransmit.getParameterData();
        	Integer employeeParentId = null;
        	Integer occupationId = null;
        	Integer areaId = null;
        	if(findDataEmployee != null && !findDataEmployee.isEmpty()){
        		LOGGER.info(findDataEmployee.toString());
            	String name = (String)findDataEmployee.get(EnumFieldEmployee.NAME_EMPLOYE.toString());
            	String nik = (String)findDataEmployee.get(EnumFieldEmployee.NIK_EMPLOYE.toString());
            	String employeeParentNik = (String)findDataEmployee.get(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString());
            	String username = (String)findDataEmployee.get(EnumFieldEmployee.USERNAME_EMPLOYE.toString());
            	String birthPlace = (String)findDataEmployee.get(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString());
            	java.sql.Date birthDate = new java.sql.Date(ParameterConstant.FORMAT_DEFAULT.parse((String)findDataEmployee.get(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString())).getTime());
            	String gender = ((String)findDataEmployee.get(EnumFieldEmployee.GENDER_EMPLOYE.toString()));
            	String mobilePhone = (String)findDataEmployee.get(EnumFieldEmployee.PHONE_EMPLOYE.toString());
            	String homePhone = (String)findDataEmployee.get(EnumFieldEmployee.TELP_EMPLOYE.toString());
            	String email = (String)findDataEmployee.get(EnumFieldEmployee.EMAIL_EMPLOYE.toString());
            	String address = (String)findDataEmployee.get(EnumFieldEmployee.ADDRESS_EMPLOYE.toString());
            	Integer enabled = ((Double)findDataEmployee.get(EnumFieldEmployee.ENABLE_EMPLOYE.toString())).intValue();
            	String occupationCode = (String)findDataEmployee.get(EnumFieldEmployee.OCCUPATION_CODE.toString());
            	String areaCode = (String)findDataEmployee.get(EnumFieldEmployee.AREA_CODE.toString());
            	String identifierIP = (String)findDataEmployee.get(ParameterConstant.IDENTIFIER_IP.toString());
            	Timestamp identifierTime = new Timestamp(new Date().getTime());;
            	String identifierPlatform = (String)findDataEmployee.get(ParameterConstant.IDENTIFIER_PLATFORM.toString());
            	// TODO Validate Field NULL
            	if(name == null || birthDate == null || birthPlace == null || username == null ||
            			gender == null || mobilePhone == null || email == null || address == null || occupationCode == null){
                	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString()+"Field not null!");
    				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);            		
            	}   
    			// TODO Validate Gender  
    			if(!(ParameterConstant.FEMALE.equals(gender) || ParameterConstant.MALE.equals(gender))){
                	wsEndpointFault.setMessage(EResponseCode.RC_GENDER_NOT_DEFINE.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_GENDER_NOT_DEFINE.getResponseCode(), wsEndpointFault);
    			}
            	// TODO Validate User
        		List<Employee> employeeList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT em FROM Employee em "
        				+ "WHERE em.profile.user.username = ?", 
        				new Object[]{username}, null);
        		if (employeeList != null) {   
        			// TODO Validate NIK
        			if(nik != null){
                     	List<Employee> employeeNikList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT em FROM Employee em "
                				+ "WHERE em.nik = ? AND em.profile.user.username != ?", 
                     			new Object[] { nik, username }, null);
                     	if(employeeNikList != null && !employeeNikList.isEmpty()){
                     		employeeNikList = null;
                        	wsEndpointFault.setMessage(EResponseCode.RC_NIK_EXISTS.toString());
            				throw new WSEndpointFaultException(EResponseCode.RC_NIK_EXISTS.getResponseCode(), wsEndpointFault);
 						}
                 	}else{
                    	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString()+"NIK not null!");
        				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
                 	}
        			// TODO Validate Mobile Phone
        			if(mobilePhone != null){
                     	List<Profile> profileMobilePhoneList = iGenericManagerJpa.loadObjectsJQLStatement("FROM Profile "
                				+ "WHERE phone = ? AND user.username != ?", 
                     			new Object[] { mobilePhone, username }, null);
                     	if(profileMobilePhoneList != null && !profileMobilePhoneList.isEmpty()){
                     		profileMobilePhoneList = null;
                        	wsEndpointFault.setMessage(EResponseCode.RC_PHONE_EXISTS.toString());
            				throw new WSEndpointFaultException(EResponseCode.RC_PHONE_EXISTS.getResponseCode(), wsEndpointFault);
 						}
                 	}  
        			// TODO Validate Email
        			if(email != null){
                     	List<Profile> profileEmaillist = iGenericManagerJpa.loadObjectsJQLStatement("FROM Profile "
                				+ "WHERE email = ? AND user.username != ?", 
                     			new Object[] { email, username }, null);
                     	if(profileEmaillist != null && !profileEmaillist.isEmpty()){
                     		profileEmaillist = null;
                        	wsEndpointFault.setMessage(EResponseCode.RC_EMAIL_EXISTS.toString());
            				throw new WSEndpointFaultException(EResponseCode.RC_EMAIL_EXISTS.getResponseCode(), wsEndpointFault);
 						}
        			}
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
                         	roleList = null;
                         	// TODO Validate Occupation and Set Occupation
                         	List<Occupation> occupationList = iGenericManagerJpa.loadObjectsJQLStatement("FROM Occupation WHERE code = ?", 
                         			new Object[] { role.getCode() }, null);
                         	if(occupationList != null && !occupationList.isEmpty()){
                         		if(!RoleConstant.ROLE_SALESMAN.equals(employeeList.get(0).getOccupation().getCode())){
                         			List<Employee> employeeChildList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT em FROM Employee em "
                            				+ "WHERE em.employeeParent.nik = ?", 
                                 			new Object[] { nik }, null);
                         			if(employeeChildList != null && !employeeChildList.isEmpty()){
                                    	wsEndpointFault.setMessage(EResponseCode.RC_SALESMAN_REALLOCATE.toString());
                        				throw new WSEndpointFaultException(EResponseCode.RC_SALESMAN_REALLOCATE.getResponseCode(), wsEndpointFault);                         				
                         			}
                         		}
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
                	
        			ValueArgument[] valueArgumentsUser = new ValueArgument[]{
        					new ValueArgument("enabled", enabled),
        					new ValueArgument("username", username)
        			};
        			iGenericManagerJpa.execQueryNotCriteria("UPDATE sec_user SET "
        					+ "user_enabled = :enabled "
        					+ "WHERE user_username = :username ", valueArgumentsUser);
        			
        			ValueArgument[] valueArgumentsProfile = new ValueArgument[]{
        					new ValueArgument("name", name),
        					new ValueArgument("birthPlace", birthPlace),
        					new ValueArgument("birthDate", birthDate),
        					new ValueArgument("gender", gender),
        					new ValueArgument("mobilePhone", mobilePhone),
        					new ValueArgument("homePhone", homePhone),
        					new ValueArgument("email", email),
        					new ValueArgument("address", address),
        					new ValueArgument("modifiedBy", currentUserName),
        					new ValueArgument("modifiedIP", identifierIP),
        					new ValueArgument("modifiedTime", identifierTime),
        					new ValueArgument("modifiedPlatform", identifierPlatform),
        					new ValueArgument("id", employeeList.get(0).getProfile().getId())
        			};
        			iGenericManagerJpa.execQueryNotCriteria("UPDATE mst_profile SET "
        					+ "profile_name = :name, "
        					+ "profile_birthplace = :birthPlace, "
        					+ "profile_birthdate = :birthDate, "
        					+ "profile_gender = :gender, "
        					+ "profile_phone = :mobilePhone, "
        					+ "profile_telp = :homePhone, "
        					+ "profile_email = :email, "
        					+ "profile_address = :address, "
        					+ "modified_by = :modifiedBy, "
        					+ "modified_ip = :modifiedIP, "
        					+ "modified_time = :modifiedTime, "
        					+ "modified_platform = :modifiedPlatform "
        					+ "WHERE profile_id = :id ", valueArgumentsProfile);
        			
        			ValueArgument[] valueArgumentsEmployee = new ValueArgument[]{
        					new ValueArgument("code", nik),
        					new ValueArgument("nik", nik),
        					new ValueArgument("employeeParent", employeeParentId),
        					new ValueArgument("occupation", occupationId),
        					new ValueArgument("area", areaId),
        					new ValueArgument("modifiedBy", currentUserName),
        					new ValueArgument("modifiedIP", identifierIP),
        					new ValueArgument("modifiedTime", identifierTime),
        					new ValueArgument("modifiedPlatform", identifierPlatform),
        					new ValueArgument("id", employeeList.get(0).getId())
        			};
        			iGenericManagerJpa.execQueryNotCriteria("UPDATE mst_employee SET "
        					+ "employee_code = :code, "
        					+ "employee_nik = :nik, "
        					+ "employee_parent_id = :employeeParent, "
        					+ "occupation_id = :occupation, "
        					+ "area_id = :area, "
        					+ "modified_by = :modifiedBy, "
        					+ "modified_ip = :modifiedIP, "
        					+ "modified_time = :modifiedTime, "
        					+ "modified_platform = :modifiedPlatform "
        					+ "WHERE employee_id = :id", valueArgumentsEmployee);
        			
                    generalTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
                    generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
                    generalTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
                    employeeList = null;
                    valueArgumentsUser = null;
                    valueArgumentsProfile = null;
                    valueArgumentsEmployee = null;
                }else {
                	wsEndpointFault.setMessage(EResponseCode.RC_USERNAME_NOT_EXISTS.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_USERNAME_NOT_EXISTS.getResponseCode(), wsEndpointFault);
                }
        		name = null;
            	nik = null;
            	employeeParentNik = null;
            	username = null;
            	birthPlace = null;
            	birthDate = null;
            	gender = null;
            	mobilePhone = null;
            	homePhone = null;
            	email = null;
            	address = null;
            	enabled = null;
            	occupationCode = null;
            	areaCode = null;
            	identifierIP = null;
            	identifierTime = null;
            	identifierPlatform = null;
            	employeeParentId = null;
            	occupationId = null;
            	areaId = null;
        	} 
        	datasTransmit = null;
        	findDataEmployee = null;
        } catch (WSEndpointFaultException e) {
            generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            generalTransferObject.setResponseDesc(e.getFaultInfo().getMessage());	
            generalTransferObject.setResponseCode(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Update Employee System Error : " + e.getMessage(), e);
            generalTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
            generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            generalTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString() + e.getMessage());
        }
        setReturnStatusAndMessage(generalTransferObject, responseHeaderMap);
        Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(generalTransferObject, responseHeaderMap);
        generalTransferObject = null;
        this.currentUserName = null;
        return message;
    }

}
