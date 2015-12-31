package com.tripoin.web.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.tripoin.core.dto.MenuData;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.common.impl.StateFullRestImpl;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class StateFullRestTest implements IStateFullRest {

	private static final Logger LOGGER = LoggerFactory.getLogger(StateFullRestImpl.class);

	private Map<String, String> additionalDataMenu = new LinkedHashMap<String, String>();
	private List<MenuData> menuDatas = new ArrayList<MenuData>();
	private final Map<String, String> cookies = new HashMap<>();
	private String username;
	private String password;
	private boolean isOAuth;
	private final RestTemplate template = new RestTemplate();
	private HttpStatus statusCode;
	private boolean isMultipart = false;
	private boolean isDownloadedFile = false;

	@Override
	public Map<String, String> getAdditionalDataMenu() {
		return this.additionalDataMenu;
	}

	@Override
	public void setAdditionalDataMenu(Map<String, String> additionalDataMenu) {
		this.additionalDataMenu = additionalDataMenu;
	}

	@Override
	public List<MenuData> getMenuDatas() {
		return menuDatas;
	}

	@Override
	public void setMenuDatas(List<MenuData> menuDatas) {
		this.menuDatas = menuDatas;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void clearAllCookies() {
		cookies.clear();
	}

	public void setCookie(String key, String value) {
		cookies.put(key, value);
	}	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public HttpStatus getStatusCode(){
		return statusCode;
	}	
	
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public void setMultipart(boolean isMultipart) {
		this.isMultipart = isMultipart;
	}

	@Override
	public void setDownloadedFile(boolean isDownloadedFile) {
		this.isDownloadedFile = isDownloadedFile;
	}

	@Value("${tripoin.is.oauth}")
	public void setIsOAuth(boolean isOAuth) {this.isOAuth = isOAuth;}

	public RestTemplate getTemplate() {
		return template;
	}

	public void removeCookie(String key) {
		cookies.remove(key);
	}

	public <T> T get(String url, Class<T> clazz) {
		return getObject(HttpMethod.GET, url, null, clazz);
	}

	public <T> T delete(String url, Object data, Class<T> clazz) {
		return getObject(HttpMethod.DELETE, url, data, clazz);
	}

	public <T> T post(String url, Object data, Class<T> clazz) {
		return getObject(HttpMethod.POST, url, data, clazz);
	}

	public <T> T put(String url, Object data, Class<T> clazz) {
		return getObject(HttpMethod.PUT, url, data, clazz);
	}

	public <T> T getObject(HttpMethod method, String url, Object data, Class<T> clazz) {
		HttpEntity<?> request = new HttpEntity<Object>(data, getHeaders());
		ResponseEntity<T> response;
		try{
			response = template.exchange(url, method, request, clazz);
			extractCookies(response.getHeaders());
		}catch(HttpClientErrorException hcee){
			response = new ResponseEntity<>(hcee.getStatusCode());
			LOGGER.warn("HttpClientErrorException : ".concat(hcee.getMessage()));
			clearAllCookies();
		}catch(Exception e){
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error("Exception : ".concat(e.getMessage()), e);
			clearAllCookies();			
		}
		setStatusCode(response.getStatusCode());
		return response.getBody();
	}

	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		if(isDownloadedFile){
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_XHTML_XML, MediaType.TEXT_HTML, MediaType.ALL));
			isDownloadedFile = false;
		}else
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		if(isMultipart){
			headers.setContentType(new MediaType("multipart", "form-data"));
			isMultipart = false;
		}else
			headers.setContentType(MediaType.APPLICATION_JSON);
		if(cookies.isEmpty() || cookies == null) {
			if(isOAuth){
				return encodeUserCredentials(headers, username, password);	
			}
		}
		headers.put("Cookie", Arrays.asList(new String[]{getCookiesString()}));
		
		return headers;
	}
	
	private HttpHeaders encodeUserCredentials(HttpHeaders headers, String username, String password){
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
	protected void extractCookies(HttpHeaders headers) {
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
	protected String getCookiesString() {
		StringBuilder sb = new StringBuilder();
		if (!cookies.isEmpty()) {
			for(Entry<String, String> entry : cookies.entrySet()){
				sb.append(entry.getKey());
				sb.append("=");
				sb.append(entry.getValue());
				sb.append(";");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
}
