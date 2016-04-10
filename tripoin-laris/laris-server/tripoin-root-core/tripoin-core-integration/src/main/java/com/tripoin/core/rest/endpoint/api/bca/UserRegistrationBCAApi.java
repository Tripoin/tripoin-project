package com.tripoin.core.rest.endpoint.api.bca;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.pojo.APIType;
import com.tripoin.core.rest.template.IStateFullRest;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.dto.app.bca.UserBCAData;
import com.tripoin.dto.response.bca.DTOResponseOAuthBCA;
import com.tripoin.dto.response.bca.DTOResponseUserRegistrationBCA;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("userRegistrationBCAApi")
public class UserRegistrationBCAApi {

	@Autowired
	private IStateFullRest stateFullRest;

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	@Autowired
	private OAuthBCAApi oauthBCAApi;	
	
	private String typeSSL;	
	private String originHeader;
	private String bcaKeyHeader;
	private String bcaTimestampHeader;
	private String bcaSignatureHeader;

	@Value("${tripoin.api.bca.ssl.type}")
	public void setTypeSSL(String typeSSL) {
		this.typeSSL = typeSSL;
	}

	@Value("${tripoin.api.bca.header.origin}")
	public void setOriginHeader(String originHeader) {
		this.originHeader = originHeader;
	}

	@Value("${tripoin.api.bca.key}")
	public void setBcaKeyHeader(String bcaKeyHeader) {
		this.bcaKeyHeader = bcaKeyHeader;
	}

	@Value("${tripoin.api.bca.timestamp}")
	public void setBcaTimestampHeader(String bcaTimestampHeader) {
		this.bcaTimestampHeader = bcaTimestampHeader;
	}

	@Value("${tripoin.api.bca.signature}")
	public void setBcaSignatureHeader(String bcaSignatureHeader) {
		this.bcaSignatureHeader = bcaSignatureHeader;
	}

	public DTOResponseUserRegistrationBCA doUserRegistration(UserBCAData userBCAData){
		DTOResponseUserRegistrationBCA dtoResponseUserRegistrationBCA = null;
		try {
			DTOResponseOAuthBCA dtoResponseOAuthBCA = oauthBCAApi.getTokenBCA();
			APIType apiType = oauthBCAApi.getApiType();
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Host", apiType.getHost());
			httpHeaders.add("Authorization", dtoResponseOAuthBCA.getToken_type().concat(" ").concat(dtoResponseOAuthBCA.getAccess_token()));
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.add("Origin", originHeader);
			httpHeaders.add(bcaKeyHeader, apiType.getIdentifier());
			httpHeaders.add(bcaTimestampHeader, ParameterConstant.FORMAT_TIME_BCA.format(new Date()));
			httpHeaders.add(bcaSignatureHeader, apiType.getSecret());
			
			try {
				String urlString = apiType.getProtocol().concat("://")
						.concat(apiType.getHost()).concat(":")
						.concat(apiType.getPort()).concat(WebServiceBCAConstant.HTTP_USER_REGISTRATION);
				stateFullRest.setHeaders(httpHeaders);
				stateFullRest.setTypeSSL(typeSSL);
				stateFullRest.setSSL(true);
				dtoResponseUserRegistrationBCA = stateFullRest.post(urlString, userBCAData, DTOResponseUserRegistrationBCA.class);
			} catch (Exception e) {
				e.printStackTrace();				
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return dtoResponseUserRegistrationBCA;
	}
}
