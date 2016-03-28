package com.tripoin.core.rest.endpoint.user;

import java.util.HashMap;
import java.util.Map;

import org.jasypt.digest.StandardStringDigester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ValueArgument;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.exception.WSEndpointFault;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.soap.handler.WSEndpointFaultException;

/**
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("userUpdateEndpoint")
public class UserUpdateEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(UserUpdateEndpoint.class);

    @Autowired
    private IGenericManagerJpa iGenericManagerJpa;

	@Autowired
	private StandardStringDigester jasyptStringDigester;
	
	private WSEndpointFault wsEndpointFault = new WSEndpointFault();

	private String currentUserName;
	private String newPassword;

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/user/update</code><br>
	 * @param inMessage
	 * @return
	 */
    @Secured({RoleConstant.ROLE_BUYER, RoleConstant.ROLE_SELLER, RoleConstant.ROLE_ADMIN})
    public Message<GeneralTransferObject> updateUser(Message<?> inMessage) {
    	GeneralTransferObject generalTransferObject = new GeneralTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken))
		    this.currentUserName = authentication.getName();
		authentication = null;
        try {
			if(inMessage.getPayload() != null){
				String basicAuthorization = ((String)inMessage.getPayload());
	        	byte[] oauth = Base64.decode(basicAuthorization.getBytes());
	        	if(new String(oauth).startsWith(ParameterConstant.TRIPOIN_AUTHORIZATION)){
		        	newPassword = new String(oauth).replaceAll(ParameterConstant.TRIPOIN_AUTHORIZATION, "");
		        	String[] splitOauth = newPassword.split(":");
		        	if(splitOauth != null && splitOauth.length == 2){
		        		if(currentUserName.equals(splitOauth[0]))
		        			newPassword = jasyptStringDigester.digest(splitOauth[1]);
		        		else{
			        		wsEndpointFault.setMessage(EResponseCode.RC_FAIL_PASSWORD.toString());
		    				throw new WSEndpointFaultException(EResponseCode.RC_FAIL_PASSWORD.getResponseCode(), wsEndpointFault);
		        		}
		        	}else{
		        		wsEndpointFault.setMessage(EResponseCode.RC_FAIL_PASSWORD.toString());
	    				throw new WSEndpointFaultException(EResponseCode.RC_FAIL_PASSWORD.getResponseCode(), wsEndpointFault);
		        	}
	        	}else{
    				wsEndpointFault.setMessage(EResponseCode.RC_FAIL_PASSWORD.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_FAIL_PASSWORD.getResponseCode(), wsEndpointFault);	        		
	        	}
	        	basicAuthorization = null;
	        	oauth = null;
			}        	
			ValueArgument[] valueArguments = new ValueArgument[]{
					new ValueArgument("username", currentUserName),
					new ValueArgument("password", newPassword)
			};
            iGenericManagerJpa.execQueryNotCriteria("UPDATE sec_user SET user_password = :password WHERE user_username = :username", valueArguments);
            SecurityContextHolder.clearContext();
            LOGGER.debug(currentUserName.concat(" has been logout."));	
            generalTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
            generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
            generalTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
            valueArguments = null;
        } catch (WSEndpointFaultException e) {	
        	generalTransferObject.setResponseCode(e.getMessage());
        	generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
        	generalTransferObject.setResponseDesc(e.getFaultInfo().getMessage());
        } catch (Exception e) {
            LOGGER.error("Update User System Error : " + e.getLocalizedMessage(), e);
            generalTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
            generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            generalTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString() + e.getLocalizedMessage());
        }
        setReturnStatusAndMessage(generalTransferObject, responseHeaderMap);
        Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(generalTransferObject, responseHeaderMap);
        generalTransferObject = null;
        this.currentUserName = null;
        this.newPassword = null;
        return message;
    }

}
