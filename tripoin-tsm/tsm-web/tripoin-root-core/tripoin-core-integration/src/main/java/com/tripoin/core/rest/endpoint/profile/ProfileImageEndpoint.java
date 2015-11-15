package com.tripoin.core.rest.endpoint.profile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.http.multipart.UploadedMultipartFile;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("profileImageEndpoint")
public class ProfileImageEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(ProfileImageEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Value("${path.image}")
	private String rootPath;
	
	private String fileName;

	private String currentUserName;

	@Secured({RoleConstant.ROLE_SALESMAN, RoleConstant.ROLE_SALESSUPERVISOR, RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<GeneralTransferObject> updatePhotoProfile(LinkedMultiValueMap<String, Object> multipartRequest){		
		GeneralTransferObject generalTransferObject = new GeneralTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken))
		    this.currentUserName = authentication.getName();
		
		try{
			List<Profile> profileList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT pr FROM Profile pr WHERE pr.user.username = ?", new Object[]{currentUserName}, null);
			if(profileList != null){
				Profile profile = profileList.get(0);
				for (String elementName : multipartRequest.keySet()) {
					if (ParameterConstant.TRIPOIN_UPLOAD_IMAGE.equals(elementName)){
						fileName = ((UploadedMultipartFile) multipartRequest.getFirst(ParameterConstant.TRIPOIN_UPLOAD_IMAGE)).getOriginalFilename();
						((UploadedMultipartFile) multipartRequest.getFirst(ParameterConstant.TRIPOIN_UPLOAD_IMAGE)).transferTo(new File(rootPath.concat(profile.getResourcesUUID()), fileName));
					}
				}				
			}else{
				generalTransferObject.setResponseCode("2");
				generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
				generalTransferObject.setResponseDesc("Update Photo Profile User Not Found : ".concat(this.currentUserName));				
			}
			generalTransferObject.setResponseCode("0");
			generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			generalTransferObject.setResponseDesc("Update Photo Profile Success : ".concat(fileName));			
		}catch (Exception e){
			LOGGER.error("Update Photo Profile System Error : "+e.getLocalizedMessage(), e);
			generalTransferObject.setResponseCode("1");
			generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			generalTransferObject.setResponseDesc("Update Photo Profile System Error : "+e.getMessage());
		}
		
		setReturnStatusAndMessage(generalTransferObject, responseHeaderMap);
		Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(generalTransferObject, responseHeaderMap);
		return message;	
	}
	
}
