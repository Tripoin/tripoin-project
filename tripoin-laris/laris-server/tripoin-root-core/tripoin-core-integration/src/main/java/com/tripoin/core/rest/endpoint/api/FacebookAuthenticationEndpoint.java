package com.tripoin.core.rest.endpoint.api;

import java.util.HashMap;
import java.util.Map;

import org.scribe.builder.api.FacebookApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import com.tripoin.core.common.APIConstant;
import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.pojo.APIType;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.rest.template.IStateFullRest;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.util.api.facebook.OAuthServiceProvider;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("facebookAuthenticationEndpoint")
public class FacebookAuthenticationEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(FacebookAuthenticationEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private IStateFullRest stateFullRest;

	@Autowired
	private OAuthServiceProvider<FacebookApi> facebookServiceProvider;
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>/api/secret/facebook{authentication}</code><br>
	 * @param inMessage
	 * @return
	 */
	public Message<GeneralTransferObject> getAuthentication(Message<LinkedMultiValueMap<String, String>> inMessage){
		GeneralTransferObject generalTransferObject = new GeneralTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		try {
			LinkedMultiValueMap<String, String> dataPayload = inMessage.getPayload();
			OAuthService oAuthService = facebookServiceProvider.getService();
			Verifier verifier = new Verifier(dataPayload.getFirst(APIConstant.CODE.toString()));
			Token accessToken = oAuthService
					.getAccessToken(Token.empty(), verifier);
			FilterArgument[] filterArgument = new FilterArgument[]{
					new FilterArgument("code", ECommonOperator.EQUALS)	
			};
			APIType apiType = iGenericManagerJpa.loadObjectsFilterArgument(APIType.class, filterArgument, 
					new Object[]{APIConstant.FACEBOOK.getOperator()}, null, null).get(0);

			Facebook facebook = new FacebookTemplate(accessToken.getToken());
			String userId = facebook.userOperations().getUserProfile().getId();

			LOGGER.info("Logged in User Id : {}", userId);
			LOGGER.info("Access Token : {}", accessToken.getToken());
			generalTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
			generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			generalTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
		}  catch (Exception e) {
			LOGGER.error("Request Token System Error : "+e.getMessage(), e);
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
