package com.tripoin.core.rest.endpoint.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import com.tripoin.core.common.APIConstant;
import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.pojo.APIType;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.rest.template.IStateFullRest;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.dto.app.GeneralTransferObject;
import com.tripoin.util.api.instagram.dto.TokenInstagramTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("instagramAuthenticationEndpoint")
public class InstagramAuthenticationEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(InstagramAuthenticationEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private IStateFullRest stateFullRest;
	
	private String tokenPath;

	@Value("${api.instagram.token}")
	public void setTokenPath(String tokenPath) {this.tokenPath = tokenPath;}

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/api/secret/instagram{authentication}</code><br>
	 * @param inMessage
	 * @return
	 */
	public Message<GeneralTransferObject> getAuthentication(Message<LinkedMultiValueMap<String, String>> inMessage){
		GeneralTransferObject generalTransferObject = new GeneralTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		try {
			LinkedMultiValueMap<String, String> dataPayload = inMessage.getPayload();
			FilterArgument[] filterArgument = new FilterArgument[]{
				new FilterArgument("code", ECommonOperator.EQUALS)	
			};
			APIType apiType = iGenericManagerJpa.loadObjectsFilterArgument(APIType.class, filterArgument, 
					new Object[]{APIConstant.INSTAGRAM.getOperator()}, null, null).get(0);
			String url = apiType.getHost().concat(tokenPath);
			String data = APIConstant.CLIENT_ID.toString().concat("=").concat(apiType.getIdentifier()).concat("&")
					.concat(APIConstant.CLIENT_SECRET.toString()).concat("=").concat(apiType.getSecret()).concat("&")
					.concat(APIConstant.REDIRECT_URI.toString()).concat("=").concat(apiType.getAdditional()).concat("&")
					.concat(APIConstant.GRANT_TYPE.toString()).concat("=").concat(APIConstant.AUTHORIZATION_CODE.toString()).concat("&")
					.concat(APIConstant.CODE.toString()).concat("=").concat(dataPayload.getFirst(APIConstant.CODE.toString()));
			TokenInstagramTransferObject responseInstagram = stateFullRest.post(url, data, TokenInstagramTransferObject.class);
			System.err.println(responseInstagram);
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
