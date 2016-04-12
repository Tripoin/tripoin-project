package com.tripoin.core.rest.endpoint.api.bca;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.pojo.APIType;
import com.tripoin.core.rest.security.bca.SignatureBCA;
import com.tripoin.core.rest.template.IStateFullRest;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.dto.request.bca.DTORequestUserUpdateBCA;
import com.tripoin.dto.response.bca.DTOResponseOAuthBCA;
import com.tripoin.dto.response.bca.DTOResponseUserUpdateBCA;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("userUpdateBCAApi")
public class UserUpdateBCAApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserUpdateBCAApi.class);

	@Autowired
	private IStateFullRest stateFullRest;

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	@Autowired
	private OAuthBCAApi oauthBCAApi;	
	
	private String originHeader;
	private String bcaKeyHeader;
	private String bcaTimestampHeader;
	private String bcaSignatureHeader;

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

	public DTOResponseUserUpdateBCA doUserUpdate(DTORequestUserUpdateBCA dtoRequestUserUpdateBCA, String companyCode, String primaryID){
		DTOResponseUserUpdateBCA dtoResponseUserUpdateBCA = null;
		try {
			DTOResponseOAuthBCA dtoResponseOAuthBCA = oauthBCAApi.getTokenBCA();
			APIType apiType = oauthBCAApi.getApiType();

			ObjectMapper mapper = new ObjectMapper();
			String dataRequestJson = mapper.writeValueAsString(dtoRequestUserUpdateBCA);
			String pathString = WebServiceBCAConstant.HTTP_USER_UPDATE
					.concat("/").concat(companyCode).concat("/").concat(primaryID);
			String urlString = apiType.getProtocol().concat("://")
					.concat(apiType.getHost()).concat(":")
					.concat(apiType.getPort()).concat(pathString);
			String timesTampBCA = ParameterConstant.FORMAT_TIME_BCA.format(new Date());
			String signaturePlainText = "PUT"+":"+pathString
					+":"+dtoResponseOAuthBCA.getAccessToken()
					+":"+SignatureBCA.hexSha256(dataRequestJson)
					+":"+timesTampBCA;
			String signature = SignatureBCA.hmacSha256(apiType.getSecret(), signaturePlainText); 
			LOGGER.debug(bcaSignatureHeader+" : "+signature);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Authorization", dtoResponseOAuthBCA.getTokenType().concat(" ").concat(dtoResponseOAuthBCA.getAccessToken()));
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.add("Origin", originHeader);
			httpHeaders.add(bcaKeyHeader, apiType.getIdentifier());
			httpHeaders.add(bcaTimestampHeader, timesTampBCA);						
			httpHeaders.add(bcaSignatureHeader, signature);
			stateFullRest.setHeaders(httpHeaders);
			try {				
				dtoResponseUserUpdateBCA = stateFullRest.put(urlString, dataRequestJson, DTOResponseUserUpdateBCA.class);
			} catch (Exception e) {
				e.printStackTrace();				
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return dtoResponseUserUpdateBCA;
	}
	
}
