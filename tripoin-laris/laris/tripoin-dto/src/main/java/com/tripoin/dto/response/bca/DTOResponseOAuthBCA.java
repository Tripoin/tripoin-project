package com.tripoin.dto.response.bca;

import com.tripoin.dto.app.bca.ErrorMessageData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTOResponseOAuthBCA extends ErrorMessageData {

	String access_token;
	String token_type;
	Integer expires_in;
	String scope;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "OAuthBCAData [access_token=" + access_token + ", token_type="
				+ token_type + ", expires_in=" + expires_in + ", scope="
				+ scope + "]";
	}
}
