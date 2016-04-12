package com.tripoin.dto.response.bca;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.dto.app.bca.ErrorMessageData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTOResponseOAuthBCA extends ErrorMessageData {

	@JsonProperty("access_token")
	String accessToken;

	@JsonProperty("token_type")
	String tokenType;

	@JsonProperty("expires_in")
	Integer expiresIn;

	@JsonProperty("scope")
	String scope;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "DTOResponseOAuthBCA [accessToken=" + accessToken
				+ ", tokenType=" + tokenType + ", expiresIn=" + expiresIn
				+ ", scope=" + scope + "]";
	}
}
