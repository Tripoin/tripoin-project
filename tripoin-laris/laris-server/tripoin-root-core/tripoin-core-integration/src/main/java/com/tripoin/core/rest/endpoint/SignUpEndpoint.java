package com.tripoin.core.rest.endpoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.exception.WSEndpointFault;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.rest.endpoint.api.bca.OAuthBCAApi;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.soap.handler.WSEndpointFaultException;
import com.tripoin.dto.app.CustomerData;
import com.tripoin.dto.app.FacebookProfileData;
import com.tripoin.dto.app.GeneralTransferObject;
import com.tripoin.dto.request.DTORequestSignUp;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("signUpEndpoint")
public class SignUpEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(SignUpEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private OAuthBCAApi oauthBCAApi;
	
	private WSEndpointFault wsEndpointFault = new WSEndpointFault();

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/api/signup</code><br>
	 * @param inMessage
	 * @return
	 */
	public Message<GeneralTransferObject> doRegisterAccount(Message<?> inMessage){
		GeneralTransferObject generalTransferObject = new GeneralTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		try {
			/*DTORequestSignUp dtoRequestSignUp = inMessage.getPayload();*/
			System.out.println(oauthBCAApi.getTokenBCA());
			/*if(dtoRequestSignUp != null){
				FacebookProfileData facebookProfileData = dtoRequestSignUp.getFacebookProfileData();
				CustomerData customerData = dtoRequestSignUp.getCustomerData();
				FilterArgument[] filterArguments = new FilterArgument[] { 
						new FilterArgument("username", ECommonOperator.EQUALS) 
				};
				List<Profile> userList = iGenericManagerJpa.loadObjectsJQLStatement(
						"SELECT usr FROM Profile usr WHERE usr.username = ? OR usr.profile.",
						new Object[] { customerData.getPhoneNumber(), customerData.getEmail() }, null);
				if (userList != null) {
					wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_PHONE_EXISTS.getResponseCode(), wsEndpointFault);
				}*/
				generalTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
				generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
				generalTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
				/*userList = null;
				filterArguments = null;
			}*/
		/*} catch (WSEndpointFaultException e) {	
			generalTransferObject.setResponseCode(e.getMessage());
			generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			generalTransferObject.setResponseDesc(e.getFaultInfo().getMessage());*/
        }  catch (Exception e) {
			LOGGER.error("Login Menu System Error : "+e.getMessage(), e);
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
