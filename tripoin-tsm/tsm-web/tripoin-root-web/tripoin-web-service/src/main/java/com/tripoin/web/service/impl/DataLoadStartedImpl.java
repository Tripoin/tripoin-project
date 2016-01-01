package com.tripoin.web.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.web.common.ABaseHttpRest;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IDataLoadStarted;
import com.vaadin.data.util.BeanItemContainer;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DataLoadStartedImpl extends ABaseHttpRest implements InitializingBean, IDataLoadStarted {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataLoadStartedImpl.class);

	@Autowired
	private ICommonRest commonRest;
	
	private boolean isOAuth;
	private String username;
	private String password;	

	@Value("${tripoin.is.oauth}")
	public void setIsOAuth(boolean isOAuth) {this.isOAuth = isOAuth;}
	@Value("${tripoin.web.app.username}")	
	public void setUsername(String username) {this.username = username;}
	@Value("${tripoin.web.app.password}")
	public void setPassword(String password) {this.password = password;}
	
	private BeanItemContainer<OccupationData> occupationContainer = new BeanItemContainer<>(OccupationData.class);

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			setOccupationContainer(getObject(HttpMethod.GET, commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_ALL), null, OccupationTransferObject.class).getOccupationDatas());
		} catch (Exception e) {
			if(HttpStatus.NOT_FOUND.equals(getStatusCode())){
				LOGGER.warn("Response : ".concat(getStatusCode().value()+" ").concat(getStatusCode().getReasonPhrase()));
				throw new Exception("Please check Web Service, and restart this Web Container");
			}else LOGGER.error("Response : ".concat(getStatusCode().value()+" ").concat(getStatusCode().getReasonPhrase()),e);			
		}				 
	}

	@Override
	public BeanItemContainer<OccupationData> getOccupationContainer() {
		return occupationContainer;
	}

	public void setOccupationContainer(List<OccupationData> occupationDatas) {
		this.occupationContainer.removeAllItems();
		this.occupationContainer.addAll(occupationDatas);
    	this.occupationContainer.removeContainerProperty("id");
    	this.occupationContainer.removeContainerProperty("code");
    	this.occupationContainer.removeContainerProperty("status");
    	this.occupationContainer.removeContainerProperty("remarks");
    	this.occupationContainer.removeContainerProperty("createdBy");
    	this.occupationContainer.removeContainerProperty("createdIP");
    	this.occupationContainer.removeContainerProperty("createdTime");
    	this.occupationContainer.removeContainerProperty("createdPlatform");
    	this.occupationContainer.removeContainerProperty("modifiedBy");
    	this.occupationContainer.removeContainerProperty("modifiedIP");
    	this.occupationContainer.removeContainerProperty("modifiedTime");
    	this.occupationContainer.removeContainerProperty("modifiedPlatform");
	}
	
	@Override
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		if(isOAuth) return encodeUserCredentials(headers, username, password);	
		else return headers;
	}	

}
