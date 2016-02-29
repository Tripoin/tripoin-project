package com.tripoin.core.rest.endpoint.profile;

import java.util.ArrayList;
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
import com.tripoin.core.dto.ProfileData;
import com.tripoin.core.dto.ProfileTransferObject;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;

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

        try {
        	ProfileData profileData = inMessage.getPayload();
        	if(profileData != null && profileData.getId() == null){
                FilterArgument[] filterArguments = new FilterArgument[] { 
        				new FilterArgument("profile.user.username", ECommonOperator.EQUALS) 
        		};
        		List<Profile> profileList = iGenericManagerJpa.loadObjectsFilterArgument(Profile.class, filterArguments, new Object[] { currentUserName }, null, null);    		
        		profileData.setId(profileList.get(0).getId());        		
        	}else
        		throw new Exception();
    		
    		iGenericManagerJpa.updateObject(new Profile(profileData));
            List<ProfileData> profileDatas = new ArrayList<ProfileData>();
            profileDatas.add(profileData);
            profileTransferObject.setProfileDatas(profileDatas);
            profileTransferObject.setResponseCode("0");
            profileTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
            profileTransferObject.setResponseDesc("Update Profile Data Success");
        } catch (Exception e) {
            LOGGER.error("Update Profile System Error : " + e.getMessage(), e);
            profileTransferObject.setResponseCode("1");
            profileTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            profileTransferObject.setResponseDesc("Update Profile System Error : " + e.getMessage());
        }

        setReturnStatusAndMessage(profileTransferObject, responseHeaderMap);
        Message<ProfileTransferObject> message = new GenericMessage<ProfileTransferObject>(profileTransferObject, responseHeaderMap);
        return message;
    }

}
