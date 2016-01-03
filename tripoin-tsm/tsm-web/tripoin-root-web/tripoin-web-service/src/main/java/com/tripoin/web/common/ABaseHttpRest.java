package com.tripoin.web.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class ABaseHttpRest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ABaseHttpRest.class);

	private final RestTemplate template = new RestTemplate();
	private HttpStatus statusCode;
	private final Map<String, String> cookies = new HashMap<>();

	protected RestTemplate getTemplate() {
		return template;
	}	

	protected void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	
	public HttpStatus getStatusCode(){
		return statusCode;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookie(String key, String value) {
		cookies.put(key, value);
	}	

	public void clearAllCookies() {
		cookies.clear();
	}

	public void removeCookie(String key) {
		cookies.remove(key);
	}
	
	protected <T> T getObject(HttpMethod method, String url, Object data, Class<T> clazz) {
		HttpEntity<?> request = new HttpEntity<Object>(data, getHeaders());
		ResponseEntity<T> response;
		try{
			response = template.exchange(url, method, request, clazz);
			extractCookies(response.getHeaders());
		}catch(HttpClientErrorException hcee){
			response = new ResponseEntity<>(hcee.getStatusCode());
			LOGGER.warn("HttpClientErrorException : ".concat(hcee.getMessage()));
			clearAllCookies();
		}catch(ResourceAccessException e){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			LOGGER.warn("Exception : ".concat("Connection refused, please check Web Service"));
			clearAllCookies();
		}catch(Exception e){
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error("Exception : ".concat(e.getMessage()), e);
			clearAllCookies();		
		}
		setStatusCode(response.getStatusCode());
		return response.getBody();
	}

	protected HttpHeaders encodeUserCredentials(HttpHeaders headers, String username, String password){
		String combinedUsernamePassword = username.concat(":").concat(password);
		byte[] base64Token = Base64.encode(combinedUsernamePassword.getBytes());
		String base64EncodedToken = new String (base64Token);
		headers.add("Authorization","Basic ".concat(base64EncodedToken));
		return headers;
	}
	
	/**
	 * <b>Description :</b><br>
	 * Extract cookies from a response header. Will overight previous cookies if new one arrive with
	 * the same key.
	 *
	 * @param headers response header
	 */
	public void extractCookies(HttpHeaders headers) {
		List<String> cookiesList = headers.get("Set-Cookie");
		if (cookiesList != null && !cookiesList.isEmpty()) {
			String cookiesStr = cookiesList.get(0);
			String[] cookiesSplit = cookiesStr.split("; ");
			for (String cookieStr : cookiesSplit) {
				String[] keyValueSplit = cookieStr.split("=");
				if(!WebServiceConstant.HTTP_ONLY.equals(cookieStr) || keyValueSplit.length > 1)
					cookies.put(keyValueSplit[0], keyValueSplit[1]);
			}
		}
	}

	/**
	 * <b>Description :</b><br>
	 * Get the cookies string to write in the request header.
	 *
	 * @return cookies as string
	 */
	public String getCookiesString() {
		StringBuilder sb = new StringBuilder();
		if (!getCookies().isEmpty()) {
			for(Entry<String, String> entry : getCookies().entrySet()){
				sb.append(entry.getKey());
				sb.append("=");
				sb.append(entry.getValue());
				sb.append(";");
			}
			sb = new StringBuilder(sb.toString().replaceAll("Path;", ""));
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
	public abstract HttpHeaders getHeaders();
	
}
