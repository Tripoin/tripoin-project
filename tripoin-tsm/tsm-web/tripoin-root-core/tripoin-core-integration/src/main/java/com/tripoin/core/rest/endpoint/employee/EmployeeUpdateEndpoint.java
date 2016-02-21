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
import com.tripoin.core.dto.exception.WSEndpointFault;
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
        	Map<String, Object> findDataEmployee = datasTransmit.getFindEmployeeData();
        	Role role = new Role();
        	User user = new User();
        	Profile profile = new Profile();
        	Occupation occupation = new Occupation();
        	Employee employee = new Employee();
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
            	Integer enabled = (Integer)findDataEmployee.get(EnumFieldEmployee.ENABLE_EMPLOYE.toString());
                FilterArgument[] filterArguments = new FilterArgument[] { 
        				new FilterArgument(EnumFieldEmployee.USERNAME_EMPLOYE.toString(), ECommonOperator.EQUALS)
        		};
        		List<Employee> employeeList = iGenericManagerJpa.loadObjectsFilterArgument(Employee.class, filterArguments, new Object[] { 
        				username}, null, null);
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
                	if(datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString()) != null){
                    	FilterArgument[] filterArgumentsParentEmployee = new FilterArgument[] { 
                				new FilterArgument(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString(), ECommonOperator.EQUALS) 
                		};
                    	List<Employee> employeeParentList = iGenericManagerJpa.loadObjectsFilterArgument(Employee.class, filterArgumentsParentEmployee, new Object[] { datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.NIK_PARENT_EMPLOYE.toString()) }, null, null);
                    	if(employeeParentList != null && !employeeParentList.isEmpty())
                    		employee.setEmployeeParent(employeeParentList.get(0));
                	}
                	if(datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.NIK_EMPLOYE.toString()) != null){
                		FilterArgument[] filterArgumentsNik = new FilterArgument[] { 
                				new FilterArgument(EnumFieldEmployee.NIK_EMPLOYE.toString(), ECommonOperator.EQUALS)
                		};
                		List<Employee> employeeListCheckNik = iGenericManagerJpa.loadObjectsFilterArgument(Employee.class, filterArgumentsNik, new Object[] { 
                				nik}, null, null);
                		if(employeeListCheckNik != null && !employeeListCheckNik.isEmpty()){
            				wsEndpointFault.setMessage("NIK Already Exists");
            				throw new WSEndpointFaultException("3", wsEndpointFault);	
                    	}else
                            employee.setNik(nik);
                	}
                	if(datasTransmit.getFindEmployeeData().get(EnumFieldEmployee.PHONE_EMPLOYE.toString()) != null){
                    	List<Profile> profileListCheckMobileEmail = iGenericManagerJpa.loadObjectsJQLStatement("FROM Profile WHERE email = ? OR phone = ?",  new Object[] { 
                    			email, 
                    			mobilePhone }, null);
                    	if(profileListCheckMobileEmail != null && !profileListCheckMobileEmail.isEmpty()){
            				wsEndpointFault.setMessage("Contact Email and Mobile Phone Already Exists");
            				throw new WSEndpointFaultException("4", wsEndpointFault);	
                    	}
                	}
                    profile.setName(name);
                    profile.setEmail(email);
                    profile.setGender(gender);
                    profile.setBirthplace(birthPlace);
                    profile.setBirthdate(birthDate);
                    profile.setAddress(address);
                    profile.setTelp(homePhone);
                    profile.setPhone(mobilePhone);
                   	
                    user.setEnabled(enabled);
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
                }        		
        	} 
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
