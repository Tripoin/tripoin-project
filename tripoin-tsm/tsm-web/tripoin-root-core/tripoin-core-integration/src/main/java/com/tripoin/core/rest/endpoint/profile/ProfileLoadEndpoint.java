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
import com.tripoin.core.dto.ProfileData;
import com.tripoin.core.dto.ProfileTransferObject;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("profileLoadEndpoint")
public class ProfileLoadEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(ProfileLoadEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	private String currentUserName;

	@Secured({RoleConstant.ROLE_SALESMAN, RoleConstant.ROLE_AREASALESMANAGER, RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<ProfileTransferObject> getProfile(Message<?> inMessage){	
		ProfileTransferObject profileTransferObject = new ProfileTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken))
		    this.currentUserName = authentication.getName();		
		
		try{
			List<Profile> profileList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT pr FROM Profile pr WHERE pr.user.username = ?", new Object[]{currentUserName}, null);
			List<ProfileData> profileDatas = new ArrayList<ProfileData>();
			if(profileList != null){
				for(Profile profile : profileList)
					profileDatas.add(new ProfileData(profile));
				profileTransferObject.setProfileDatas(profileDatas);
			}
			profileTransferObject.setResponseCode("0");
			profileTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			profileTransferObject.setResponseDesc("Load Profile Data Success");			
		}catch (Exception e){
			LOGGER.error("Load Profile System Error : "+e.getLocalizedMessage(), e);
			profileTransferObject.setResponseCode("1");
			profileTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			profileTransferObject.setResponseDesc("Load Profile System Error : "+e.getLocalizedMessage());
		}
		
		setReturnStatusAndMessage(profileTransferObject, responseHeaderMap);
		Message<ProfileTransferObject> message = new GenericMessage<ProfileTransferObject>(profileTransferObject, responseHeaderMap);
		return message;		
	}
	
}
