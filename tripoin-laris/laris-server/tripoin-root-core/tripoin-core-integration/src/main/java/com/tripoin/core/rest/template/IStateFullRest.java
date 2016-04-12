package com.tripoin.core.rest.template;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IStateFullRest { 

	/**
	 * <b>Sample Code:</b><br>
	 * <code>getCookies()</code><br>
	 * @return
	 */
	public Map<String, String> getCookies();
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>clearAllCookies()</code><br>
	 */
	public void clearAllCookies();
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>getStatusCode()</code><br>
	 * @return
	 */
	public HttpStatus getStatusCode();

	/**
	 * <b>Sample Code:</b><br>
	 * <code>get(commonRest.getUrl(WebServiceConstant.HTTP_CONNECTION), UserDTO.class)</code><br>
	 * @param url
	 * @param clazz
	 * @return
	 */
	public <T> T get(String url, Class<T> clazz);

	/**
	 * <b>Sample Code:</b><br>
	 * <code>delete(commonRest.getUrl(WebServiceConstant.HTTP_CONNECTION), data, UserDTO.class)</code><br>
	 * @param url
	 * @param data
	 * @param clazz
	 * @return
	 */
	public <T> T delete(String url, Object data, Class<T> clazz);

	/**
	 * <b>Sample Code:</b><br>
	 * <code>post(commonRest.getUrl(WebServiceConstant.HTTP_CONNECTION), data, UserDTO.class)</code><br>
	 * @param url
	 * @param data
	 * @param clazz
	 * @return
	 */
	public <T> T post(String url, Object data, Class<T> clazz);

	/**
	 * <b>Sample Code:</b><br>
	 * <code>options(commonRest.getUrl(WebServiceConstant.HTTP_CONNECTION), data, UserDTO.class)</code><br>
	 * @param url
	 * @param data
	 * @param clazz
	 * @return
	 */
	public <T> T options(String url, Object data, Class<T> clazz);
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>put(commonRest.getUrl(WebServiceConstant.HTTP_CONNECTION), data, UserDTO.class)</code><br>
	 * @param url
	 * @param data
	 * @param clazz
	 * @return
	 */
	public <T> T put(String url, Object data, Class<T> clazz);
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>stateFullRest.getHeaders()</code><br>
	 * @return
	 */
	public HttpHeaders getHeaders();
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>setHeaders</code><br>
	 * @param httpHeaders
	 */
	public void setHeaders(HttpHeaders httpHeaders);
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>setSSL</code><br>
	 * @param ssl
	 */
	public void setSSL(boolean ssl);
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>encodeUserCredentials</code><br>
	 * @param headers
	 * @param username
	 * @param password
	 * @return
	 */
	public HttpHeaders encodeUserCredentials(HttpHeaders headers, String username, String password);
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>encodeUserCredentials</code><br>
	 * @param headers
	 * @param usernamePassword
	 * @return
	 */
	public HttpHeaders encodeUserCredentials(HttpHeaders headers, String usernamePassword);

	/**
	 * <b>Sample Code:</b><br>
	 * <code>typeSSL</code><br>
	 * @return
	 */
	public String typeSSL();
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>setTypeSSL</code><br>
	 * @param typeSSL
	 */
	public void setTypeSSL(String typeSSL);

}
