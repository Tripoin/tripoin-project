package com.tripoin.core.rest.endpoint.profile;

import java.util.ArrayList;
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
import com.tripoin.core.dto.ProfileData;
import com.tripoin.core.dto.ProfileTransferObject;
import com.tripoin.core.dto.exception.WSEndpointFault;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.User;
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
    public Message<ProfileTransferObject> updateProfile(Message<ProfileData> inMessage) {
    	ProfileTransferObject profileTransferObject = new ProfileTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken))
		    this.currentUserName = authentication.getName();		
		authentication = null;
        try {
        	ProfileData profileData = inMessage.getPayload();
        	Profile profile = new Profile();
        	if(profileData != null){
            	if(profileData.getAddress() == null || profileData.getBirthdate() == null ||
            			profileData.getBirthplace() == null || profileData.getEmail() == null ||
            			profileData.getPhone() == null || profileData.getGender() == null ||
            			profileData.getName() == null){
                	wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString()+"Field not null!");
    				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);        		
            	}
            	profile = new Profile(profileData);
            	List<Profile> profileValidateList = iGenericManagerJpa.loadObjectsJQLStatement("FROM Profile WHERE (email = ? OR phone = ?) AND user.username != ?", new Object[] { profile.getEmail(), profile.getPhone(), currentUserName }, null);
            	if(profileValidateList != null && !profileValidateList.isEmpty()){
            		if(profile.getPhone().equals(profileValidateList.get(0).getPhone())){
        				wsEndpointFault.setMessage(EResponseCode.RC_PHONE_EXISTS.toString());
        				throw new WSEndpointFaultException(EResponseCode.RC_PHONE_EXISTS.getResponseCode(), wsEndpointFault);	
            		}	
            		if(profile.getEmail().equals(profileValidateList.get(0).getEmail())){
        				wsEndpointFault.setMessage(EResponseCode.RC_EMAIL_EXISTS.toString());
        				throw new WSEndpointFaultException(EResponseCode.RC_EMAIL_EXISTS.getResponseCode(), wsEndpointFault);	
            		}
            	}
            	FilterArgument[] filterArguments = new FilterArgument[] { 
        				new FilterArgument("user.username", ECommonOperator.EQUALS) 
        		};
                List<Profile> profileList = iGenericManagerJpa.loadObjectsFilterArgument(Profile.class, filterArguments, new Object[] { currentUserName }, null, null);
                List<User> userList = iGenericManagerJpa.loadObjectsJQLStatement("FROM User WHERE username = ?", new Object[] { currentUserName }, null);
                if(userList != null && !userList.isEmpty())
                	profile.setUser(userList.get(0));
                profile.setId(profileList.get(0).getId());
                profile.setCreatedBy(profileList.get(0).getCreatedBy());
                profile.setCreatedIP(profileList.get(0).getCreatedIP());
                profile.setCreatedTime(profileList.get(0).getCreatedTime());
                profile.setCreatedPlatform(profileList.get(0).getCreatedPlatform());
        		filterArguments = null;
        		profileList = null;
        		profileValidateList = null;
        	}else
        		throw new Exception();
        	profile.setModifiedBy(currentUserName);
        	if(profile.getModifiedIP() == null)
        		profile.setModifiedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
        	if(profile.getModifiedTime() == null)
        		profile.setModifiedTime(new Date());
        	if(profile.getModifiedPlatform() == null)
        		profile.setModifiedPlatform(ParameterConstant.PLATFORM_DEFAULT);
        	
    		iGenericManagerJpa.updateObject(profile);
            List<ProfileData> profileDatas = new ArrayList<ProfileData>();
            profileDatas.add(new ProfileData(profile));
            profileTransferObject.setProfileDatas(profileDatas);
            profileTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
            profileTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
            profileTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
            profile = null;
            profileData = null;
            profileDatas = null;
        } catch (WSEndpointFaultException e) {	
        	profileTransferObject.setResponseCode(e.getMessage());
        	profileTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
        	profileTransferObject.setResponseDesc(e.getFaultInfo().getMessage());
        } catch (Exception e) {
            LOGGER.error("Update Profile System Error : " + e.getMessage(), e);
            profileTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
            profileTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            profileTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString() + e.getMessage());
        }
        setReturnStatusAndMessage(profileTransferObject, responseHeaderMap);
        Message<ProfileTransferObject> message = new GenericMessage<ProfileTransferObject>(profileTransferObject, responseHeaderMap);
        profileTransferObject = null;
        this.currentUserName = null;
        return message;
    }

}
