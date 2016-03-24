package com.tripoin.core.rest.endpoint.profile;

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
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.ProfileTransferObject.EnumFieldProfile;
import com.tripoin.core.dto.exception.WSEndpointFault;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.soap.handler.WSEndpointFaultException;

/**
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("profileUpdateEndpoint")
public class ProfileUpdateEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(ProfileUpdateEndpoint.class);

    @Autowired
    private IGenericManagerJpa iGenericManagerJpa;

	private String currentUserName;
	
	private WSEndpointFault wsEndpointFault = new WSEndpointFault();

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/profile/update</code><br>
	 * @param inMessage
	 * @return
	 */
    @Secured({RoleConstant.ROLE_SALESMAN, RoleConstant.ROLE_AREASALESMANAGER, RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<GeneralTransferObject> updateProfile(Message<GeneralTransferObject> inMessage) {
    	GeneralTransferObject generalTransferObject = new GeneralTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken))
		    this.currentUserName = authentication.getName();		
		authentication = null;
        try {
        	GeneralTransferObject datasTransmit = inMessage.getPayload();
        	Map<String, Object> findDataProfile = datasTransmit.getParameterData();
        	if(findDataProfile != null && !findDataProfile.isEmpty()){
        		LOGGER.info(findDataProfile.toString());
            	String name = (String)findDataProfile.get(EnumFieldProfile.NAME_PROFILE.toString());
            	String email = (String)findDataProfile.get(EnumFieldProfile.EMAIL_PROFILE.toString());
            	String mobilePhone = (String)findDataProfile.get(EnumFieldProfile.PHONE_PROFILE.toString());
            	String homePhone = (String)findDataProfile.get(EnumFieldProfile.TELP_PROFILE.toString());
            	String address = (String)findDataProfile.get(EnumFieldProfile.ADDRESS_PROFILE.toString());
            	String gender = ((String)findDataProfile.get(EnumFieldProfile.GENDER_PROFILE.toString()));
            	String birthPlace = (String)findDataProfile.get(EnumFieldProfile.BIRTHPLACE_PROFILE.toString());
            	java.sql.Date birthDate = new java.sql.Date(ParameterConstant.FORMAT_DEFAULT.parse((String)findDataProfile.get(EnumFieldProfile.BIRTHDATE_PROFILE.toString())).getTime());
            	String bio = (String)findDataProfile.get(EnumFieldProfile.BIO_PROFILE.toString());
            	String identifierIP = (String)findDataProfile.get(ParameterConstant.IDENTIFIER_IP.toString());
            	Timestamp identifierTime = new Timestamp(new Date().getTime());;
            	String identifierPlatform = (String)findDataProfile.get(ParameterConstant.IDENTIFIER_PLATFORM.toString());
            	// TODO Validate Field NULL
            	if(name == null || birthDate == null || birthPlace == null ||
            			gender == null || mobilePhone == null || email == null || address == null){
                	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString()+"Field not null!");
    				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);        		
            	}  
    			// TODO Validate Gender  
    			if(!(ParameterConstant.FEMALE.equals(gender) || ParameterConstant.MALE.equals(gender))){
                	wsEndpointFault.setMessage(EResponseCode.RC_GENDER_NOT_DEFINE.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_GENDER_NOT_DEFINE.getResponseCode(), wsEndpointFault);
    			}
    			// TODO Validate Mobile Phone / Email
            	List<Profile> profileValidateList = iGenericManagerJpa.loadObjectsJQLStatement("FROM Profile WHERE "
            			+ "(email = ? OR phone = ?) AND "
            			+ "user.username != ?", new Object[] { email, mobilePhone, currentUserName }, null);
            	if(profileValidateList != null && !profileValidateList.isEmpty()){
            		if(mobilePhone.equals(profileValidateList.get(0).getPhone())){
        				wsEndpointFault.setMessage(EResponseCode.RC_PHONE_EXISTS.toString());
        				throw new WSEndpointFaultException(EResponseCode.RC_PHONE_EXISTS.getResponseCode(), wsEndpointFault);	
            		}	
            		if(email.equals(profileValidateList.get(0).getEmail())){
        				wsEndpointFault.setMessage(EResponseCode.RC_EMAIL_EXISTS.toString());
        				throw new WSEndpointFaultException(EResponseCode.RC_EMAIL_EXISTS.getResponseCode(), wsEndpointFault);	
            		}
            		profileValidateList = null;
            	}
            	if(identifierIP == null)
            		identifierIP = ParameterConstant.IP_ADDRESSV4_DEFAULT;
            	if(identifierPlatform == null)
            		identifierPlatform = ParameterConstant.PLATFORM_DEFAULT;
            	
            	List<Profile> getProfileId = iGenericManagerJpa.loadObjectsJQLStatement("FROM Profile WHERE user.username = ?", 
    					new Object[]{currentUserName}, null);
            	ValueArgument[] valueArgumentsProfile = new ValueArgument[]{
    					new ValueArgument("name", name),
    					new ValueArgument("birthPlace", birthPlace),
    					new ValueArgument("birthDate", birthDate),
    					new ValueArgument("gender", gender),
    					new ValueArgument("mobilePhone", mobilePhone),
    					new ValueArgument("homePhone", homePhone),
    					new ValueArgument("email", email),
    					new ValueArgument("address", address),
    					new ValueArgument("bio", bio),
    					new ValueArgument("modifiedBy", currentUserName),
    					new ValueArgument("modifiedIP", identifierIP),
    					new ValueArgument("modifiedTime", identifierTime),
    					new ValueArgument("modifiedPlatform", identifierPlatform),
    					new ValueArgument("profileId", getProfileId.get(0).getId())
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
    					+ "profile_bio = :bio, "
    					+ "modified_by = :modifiedBy, "
    					+ "modified_ip = :modifiedIP, "
    					+ "modified_time = :modifiedTime, "
    					+ "modified_platform = :modifiedPlatform "
    					+ "WHERE profile_id = :profileId", valueArgumentsProfile);
    			
                generalTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
                generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
                generalTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString()); 
                findDataProfile = null;           	
                valueArgumentsProfile = null;
        	}else{
            	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);
			}
            datasTransmit = null;
        } catch (WSEndpointFaultException e) {	
        	generalTransferObject.setResponseCode(e.getMessage());
        	generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
        	generalTransferObject.setResponseDesc(e.getFaultInfo().getMessage());
        } catch (Exception e) {
            LOGGER.error("Update Profile System Error : " + e.getMessage(), e);
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
