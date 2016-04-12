package com.tripoin.core.rest.endpoint.api.bca;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tripoin.core.common.APIConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.response.bca.DTOResponseOAuthBCA;
import com.tripoin.core.pojo.APIType;
import com.tripoin.core.rest.template.IStateFullRest;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("oauthBCAApi")
public class OAuthBCAApi {

	@Autowired
	private IStateFullRest stateFullRest;

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	private APIType apiType;
	
	private String grantType;
	private String userCredentials;
	private String typeSSL;

	@Value("${tripoin.api.bca.granttype}")	
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	@Value("${tripoin.api.bca.clientcredentials}")
	public void setUserCredentials(String userCredentials) {
		this.userCredentials = userCredentials;
	}

	@Value("${tripoin.api.bca.ssl.type}")
	public void setTypeSSL(String typeSSL) {
		this.typeSSL = typeSSL;
	}

	public APIType getApiType() {
		return apiType;
	}

	public DTOResponseOAuthBCA getTokenBCA(){
		DTOResponseOAuthBCA dTOResponseOAuthBCA = null;
		try {
			FilterArgument[] filterArgument = new FilterArgument[]{
					new FilterArgument("code", ECommonOperator.EQUALS)	
			};
			apiType = iGenericManagerJpa.loadObjectsFilterArgument(APIType.class, filterArgument, 
						new Object[]{APIConstant.BCA.getOperator()}, null, null).get(0);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			httpHeaders.add("Host", apiType.getHost());
			httpHeaders = stateFullRest.encodeUserCredentials(httpHeaders, apiType.getAdditional());
			MultiValueMap<String, String> dataMap = new LinkedMultiValueMap<String, String>();
			dataMap.add(grantType, userCredentials);
			try {
				String urlString = apiType.getProtocol().concat("://")
						.concat(apiType.getHost()).concat(":")
						.concat(apiType.getPort()).concat(WebServiceBCAConstant.HTTP_OAUTH_TOKEN);
				stateFullRest.setHeaders(httpHeaders);
				stateFullRest.setTypeSSL(typeSSL);
				stateFullRest.setSSL(true);
				dTOResponseOAuthBCA = stateFullRest.post(urlString, dataMap, DTOResponseOAuthBCA.class);
			} catch (Exception e) {
				e.printStackTrace();				
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return dTOResponseOAuthBCA;
	}
}
