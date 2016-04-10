package com.tripoin.core.rest.endpoint.api;

import java.net.URI;
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
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import com.tripoin.core.common.APIConstant;
import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.rest.template.IStateFullRest;
import com.tripoin.dto.app.FacebookProfileData;
import com.tripoin.dto.response.DTOResponseCallbackFacebook;
import com.tripoin.dto.response.DTOResponsePhotoFacebookData;
import com.tripoin.util.api.facebook.OAuthServiceProvider;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("facebookAuthenticationEndpoint")
public class FacebookAuthenticationEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(FacebookAuthenticationEndpoint.class);
	
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
	public Message<DTOResponseCallbackFacebook> getAuthentication(Message<LinkedMultiValueMap<String, String>> inMessage){
		DTOResponseCallbackFacebook dtoResponseCallbackFacebook = new DTOResponseCallbackFacebook();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		try {
			LinkedMultiValueMap<String, String> dataPayload = inMessage.getPayload();
			String state = dataPayload.getFirst(APIConstant.STATE.toString());
			String code = dataPayload.getFirst(APIConstant.CODE.toString());
			OAuthService oAuthService = facebookServiceProvider.getService();
			Verifier verifier = new Verifier(code);
			Token accessToken = oAuthService
					.getAccessToken(Token.empty(), verifier);

			Facebook facebook = new FacebookTemplate(accessToken.getToken());
			FacebookProfileData facebookProfileData = new FacebookProfileData();
			String userId = facebook.userOperations().getUserProfile().getId();
			facebookProfileData.setId(facebook.userOperations().getUserProfile().getId());
			facebookProfileData.setName(facebook.userOperations().getUserProfile().getName());
			facebookProfileData.setFirstName(facebook.userOperations().getUserProfile().getFirstName());
			facebookProfileData.setLastName(facebook.userOperations().getUserProfile().getLastName());
			facebookProfileData.setEmail(facebook.userOperations().getUserProfile().getEmail());
			facebookProfileData.setGender(facebook.userOperations().getUserProfile().getGender());
			String urlPhoto = fetchPictureUrl(facebook.userOperations().getUserProfile().getId(), ImageType.NORMAL, facebook.getBaseGraphApiUrl());
			facebookProfileData.setUrlPhoto(urlPhoto);

			dtoResponseCallbackFacebook.setFacebookProfileData(facebookProfileData);
			dtoResponseCallbackFacebook.setAccessToken(accessToken.getToken());
			dtoResponseCallbackFacebook.setState(state);
			LOGGER.info("Logged in User Id : {}", userId);
			LOGGER.info("Access Token : {}", accessToken.getToken());
			dtoResponseCallbackFacebook.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
			dtoResponseCallbackFacebook.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			dtoResponseCallbackFacebook.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
		}  catch (Exception e) {
			LOGGER.error("Request Token System Error : "+e.getMessage(), e);
			dtoResponseCallbackFacebook.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			dtoResponseCallbackFacebook.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			dtoResponseCallbackFacebook.setResponseDesc(EResponseCode.RC_FAILURE.toString() + e.getMessage());
		}
		setReturnStatusAndMessage(dtoResponseCallbackFacebook, responseHeaderMap);
		Message<DTOResponseCallbackFacebook> message = new GenericMessage<DTOResponseCallbackFacebook>(dtoResponseCallbackFacebook, responseHeaderMap);
		dtoResponseCallbackFacebook = null;
		return message;	
	}
	
	public String fetchPictureUrl(String userId, ImageType imageType, String GRAPH_API_URL) {
	    URI uri = URIBuilder.fromUri(GRAPH_API_URL + userId + "/picture" +
	            "?type=" + imageType.toString().toLowerCase() + "&redirect=false").build();	    
	    DTOResponsePhotoFacebookData dtoResponsePhotoFacebookData = stateFullRest.get(uri.toString(), DTOResponsePhotoFacebookData.class);
	    return dtoResponsePhotoFacebookData.getData().getUrl();
	}
}
