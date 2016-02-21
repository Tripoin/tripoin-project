package com.tripoin.web.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.tripoin.core.dto.MenuData;
import com.tripoin.web.common.ABaseHttpRest;
import com.tripoin.web.common.IStateFullRest;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class StateFullRestTest extends ABaseHttpRest implements IStateFullRest {
	
	private Map<String, String> additionalDataMenu = new LinkedHashMap<String, String>();
	private List<MenuData> menuDatas = new ArrayList<MenuData>();
	private String username;
	private String password;
	private boolean isOAuth;
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

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
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
	public <T> T put(String url, Object data, Class<T> clazz) {
		return getObject(HttpMethod.PUT, url, data, clazz);
	}

	@Override
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
		if(getCookies().isEmpty() || getCookies() == null) {
			if(isOAuth){
				return encodeUserCredentials(headers, username, password);	
			}
		}
		headers.put("Cookie", Arrays.asList(new String[]{getCookiesString()}));		
		return headers;
	}
	
}
