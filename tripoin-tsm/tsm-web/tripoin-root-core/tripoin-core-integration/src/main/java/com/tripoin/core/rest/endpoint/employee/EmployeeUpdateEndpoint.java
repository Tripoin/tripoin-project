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

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.EmployeeTransferObject.EnumFieldEmployee;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.OccupationTransferObject.EnumFieldOccupation;
import com.tripoin.core.dto.exception.WSEndpointFault;
import com.tripoin.core.pojo.Area;
import com.tripoin.core.pojo.Employee;
import com.tripoin.core.pojo.Occupation;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.Role;
import com.tripoin.core.pojo.User;
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
        try {
        	GeneralTransferObject datasTransmit = inMessage.getPayload();
        	Map<String, Object> findDataEmployee = datasTransmit.getParameterData();
        	Occupation occupation = new Occupation();
        	Area area = new Area();
        	Employee employeeParent = new Employee();
        	if(findDataEmployee != null && !findDataEmployee.isEmpty()){
            	String name = (String)findDataEmployee.get(EnumFieldEmployee.NAME_EMPLOYE.toString());
            	String nik = (String)findDataEmployee.get(EnumFieldEmployee.NIK_EMPLOYE.toString());
            	String employeeParentNik = (String)findDataEmployee.get(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString());
            	String username = (String)findDataEmployee.get(EnumFieldEmployee.USERNAME_EMPLOYE.toString());
            	String birthPlace = (String)findDataEmployee.get(EnumFieldEmployee.BIRTHPLACE_EMPLOYE.toString());
            	Date birthDate = ParameterConstant.FORMAT_DEFAULT.parse((String)findDataEmployee.get(EnumFieldEmployee.BIRTHDATE_EMPLOYE.toString()));
            	String gender = ((String)findDataEmployee.get(EnumFieldEmployee.GENDER_EMPLOYE.toString())).toUpperCase();
            	String mobilePhone = (String)findDataEmployee.get(EnumFieldEmployee.PHONE_EMPLOYE.toString());
            	String homePhone = (String)findDataEmployee.get(EnumFieldEmployee.TELP_EMPLOYE.toString());
            	String email = (String)findDataEmployee.get(EnumFieldEmployee.EMAIL_EMPLOYE.toString());
            	String address = (String)findDataEmployee.get(EnumFieldEmployee.ADDRESS_EMPLOYE.toString());
            	Integer enabled = ((Double)findDataEmployee.get(EnumFieldEmployee.ENABLE_EMPLOYE.toString())).intValue();
            	String occupationCode = (String)findDataEmployee.get(EnumFieldEmployee.OCCUPATION_CODE.toString());
            	String identifierIP = (String)findDataEmployee.get(ParameterConstant.IDENTIFIER_IP.toString());
            	String identifierTime = (String)findDataEmployee.get(ParameterConstant.IDENTIFIER_TIME.toString());
            	String identifierPlatform = (String)findDataEmployee.get(ParameterConstant.IDENTIFIER_PLATFORM.toString());
        		System.err.println(findDataEmployee);
            	/**
            	 * {
            	 * 		profile.name=Ridla Fadilah,
            	 * 		nik=TSM201511250001, 
            	 * 		employeeParent.nik=TSM201511240001, 
            	 * 		profile.user.username=ridla,
            	 * 		profile.birthplace=Bandung, 
            	 * 		profile.birthdate=27-12-1990 00:00:00.0, 
            	 * 		profile.gender=Male, 
            	 * 		profile.phone=081234567891,  
            	 * 		profile.telp=021234567891,
            	 * 		profile.email=ridla.fadilah@tripoin.co.id,
            	 * 		profile.address=Tangerang,  
            	 * 		profile.user.enabled=1.0, 
            	 * 		occupation.code=ROLE_SALESMAN, 
            	 * 		IDENTIFIER_IP=127.0.0.1, 
            	 * 		IDENTIFIER_TIME=06-03-2016 13:37:05.354, 
            	 * 		IDENTIFIER_PLATFORM=Computer | Mac OS X | Safari
            	 * }
            	 */
                // TODO Validate User
        		List<Employee> employeeList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT em FROM Employee em "
        				+ "WHERE em.code =? AND em.profile.user.username = ?", 
        				new Object[]{nik,username}, null);
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
                    	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
        				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
                 	}   
        			// TODO Validate Mobile Phone
        			if(mobilePhone != null){
                     	List<Profile> profileMobilePhoneist = iGenericManagerJpa.loadObjectsJQLStatement("SELECT em.profile FROM Employee em "
                				+ "WHERE em.profile.phone = ? AND em.profile.user.username != ?", 
                     			new Object[] { mobilePhone, username }, null);
                     	if(profileMobilePhoneist != null && !profileMobilePhoneist.isEmpty()){
                     		profileMobilePhoneist = null;
                        	wsEndpointFault.setMessage(EResponseCode.RC_PHONE_EXISTS.toString());
            				throw new WSEndpointFaultException(EResponseCode.RC_PHONE_EXISTS.getResponseCode(), wsEndpointFault);
 						}
                 	}else{
                    	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
        				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
                 	}   
        			// TODO Validate Email
        			if(email != null){
                     	List<Profile> profileEmaillist = iGenericManagerJpa.loadObjectsJQLStatement("SELECT em.profile FROM Employee em "
                				+ "WHERE em.profile.email = ? AND em.profile.user.username != ?", 
                     			new Object[] { email, username }, null);
                     	if(profileEmaillist != null && !profileEmaillist.isEmpty()){
                     		profileEmaillist = null;
                        	wsEndpointFault.setMessage(EResponseCode.RC_EMAIL_EXISTS.toString());
            				throw new WSEndpointFaultException(EResponseCode.RC_EMAIL_EXISTS.getResponseCode(), wsEndpointFault);
 						}
                 	}else{
                    	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
        				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
                 	}
        			// TODO Validate Employee Parent and Set Employee Parent
        			if(employeeParentNik != null && !RoleConstant.ROLE_NATIONALSALESMANAGER.equals(occupationCode)){
        				if(employeeParentNik != nik){
                         	List<Employee> employeeParentList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT em FROM Employee em "
                    				+ "WHERE em.nik = ?", 
                         			new Object[] { employeeParentNik }, null);
                         	if(employeeParentList != null && !employeeParentList.isEmpty()){
                         		employeeParent = employeeParentList.get(0);
                             	employeeParentList = null;
                             	if(occupationCode.equals(employeeParent.getOccupation().getCode()) ||
                             			(RoleConstant.ROLE_AREASALESMANAGER.equals(occupationCode) &&
                                     			RoleConstant.ROLE_SALESMAN.equals(employeeParent.getOccupation().getCode()))){
                                	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
                    				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
                             	}
                         	}else {
                            	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
                				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
     						}	
        				}else {
                        	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
            				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
 						}
                 	}else{
                    	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
        				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
                 	}
        			// TODO Validate Occupation/Role and Set Occupation/Role
        			if(occupationCode != null){
                     	List<Role> roleList = iGenericManagerJpa.loadObjectsJQLStatement("FROM Role WHERE code = ?", 
                     			new Object[] { occupationCode }, null);
                     	if(roleList!=null && !roleList.isEmpty()){
                     		Role role = roleList.get(0);
                         	roleList = null;
                         	List<Occupation> occupationList = iGenericManagerJpa.loadObjectsJQLStatement("FROM Occupation WHERE code = ?", 
                         			new Object[] { role.getCode() }, null);
                         	if(occupationList != null && !occupationList.isEmpty()){
                         		occupation = occupationList.get(0);
                             	occupationList = null;
                         	}else {
                            	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
                				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
     						}
                     	}else {
                        	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
            				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
 						}
                 	}
        			System.err.println(occupation);
        			System.err.println(area);
        			System.err.println(employeeParent);
        			
                    generalTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
                    generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
                    generalTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
                }else {
                	wsEndpointFault.setMessage(EResponseCode.RC_USERNAME_NOT_EXISTS.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_USERNAME_NOT_EXISTS.getResponseCode(), wsEndpointFault);
                }
        	} 
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
        return message;
    }

}
