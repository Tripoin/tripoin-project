package com.tripoin.core.rest.template.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import com.tripoin.core.rest.template.ABaseHttpRest;
import com.tripoin.core.rest.template.IStateFullRest;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("stateFullRest")
public class StateFullRestImpl extends ABaseHttpRest implements IStateFullRest {
	
	private boolean isSSL = false;
	private String typeSSL;
	
	@Override
	public <T> T get(String url, Class<T> clazz) {
		return getObject(HttpMethod.GET, url, null, clazz);
	}

	@Override
	public <T> T delete(String url, Object data, Class<T> clazz) {
		return getObject(HttpMethod.DELETE, url, data, clazz);
	}

	@Override
	public <T> T post(String url, Object data, Class<T> clazz) {
		return getObject(HttpMethod.POST, url, data, clazz);
	}

	@Override
	public <T> T options(String url, Object data, Class<T> clazz) {
		return getObject(HttpMethod.OPTIONS, url, data, clazz);
	}

	@Override
	public <T> T put(String url, Object data, Class<T> clazz) {
		return getObject(HttpMethod.PUT, url, data, clazz);
	}

	@Override
	public HttpHeaders getHeaders() {	
		/*httpHeaders.put("Cookie", Arrays.asList(new String[]{getCookiesString()}));*/
		return httpHeaders;
	}

	@Override
	public void setHeaders(HttpHeaders httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

	@Override
	public void setSSL(boolean isSSL) {
		this.isSSL = isSSL;
	}

	@Override
	public boolean isSSL() {
		return isSSL;
	}

	@Override
	public HttpHeaders encodeUserCredentials(HttpHeaders headers, String username, String password){
		String combinedUsernamePassword = username.concat(":").concat(password);
		byte[] base64Token = Base64.encode(combinedUsernamePassword.getBytes());
		String base64EncodedToken = new String (base64Token);
		headers.add("Authorization","Basic ".concat(base64EncodedToken));
		return headers;
	}

	@Override
	public HttpHeaders encodeUserCredentials(HttpHeaders headers, String usernamePassword){
		String combinedUsernamePassword = usernamePassword;
		byte[] base64Token = Base64.encode(combinedUsernamePassword.getBytes());
		String base64EncodedToken = new String (base64Token);
		headers.add("Authorization","Basic ".concat(base64EncodedToken));
		return headers;
	}

	@Override
	public String typeSSL() {
		return typeSSL;
	}

	public void setTypeSSL(String typeSSL) {
		this.typeSSL = typeSSL;
	}
	
}
