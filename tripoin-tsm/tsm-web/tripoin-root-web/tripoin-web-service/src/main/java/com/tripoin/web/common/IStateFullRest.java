package com.tripoin.web.common;

import java.util.Map;

import org.springframework.http.HttpStatus;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IStateFullRest { 

	/**
	 * <b>Description:</b><br>
	 * Load all Menu but generated on first login<br><br>
	 * <b>Sample Code:</b><br>
	 * <code>getAdditionalDataMenu()</code><br>
	 * @return
	 */
	public Map<String, String> getAdditionalDataMenu();

	/**
	 * <b>Description:</b><br>
	 * Render all Menu on first login<br><br>
	 * <b>Sample Code:</b><br>
	 * <code>setAdditionalDataMenu(additionalDataMenu)</code><br>
	 */
	public void setAdditionalDataMenu(Map<String, String> additionalDataMenu);

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
	 * <code>setUsername(username)</code><br>
	 * @param username
	 */
	public void setUsername(String username);
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>setPassword(password)</code><br>
	 * @param password
	 */
	public void setPassword(String password);
	
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
	 * <code>put(commonRest.getUrl(WebServiceConstant.HTTP_CONNECTION), data, UserDTO.class)</code><br>
	 * @param url
	 * @param data
	 * @param clazz
	 * @return
	 */
	public <T> T put(String url, Object data, Class<T> clazz);
	
}
