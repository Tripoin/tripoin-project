package com.tripoin.web.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.AreaData;
import com.tripoin.core.dto.AreaTransferObject;
import com.tripoin.core.dto.EmployeePrivateData;
import com.tripoin.core.dto.EmployeePrivateTransferObject;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.EmployeeTransferObject.EnumFieldEmployee;
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
public class DataLoadStartedImpl extends ABaseHttpRest implements IDataLoadStarted {

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
	private BeanItemContainer<AreaData> areaContainer = new BeanItemContainer<>(AreaData.class);
	private BeanItemContainer<EmployeePrivateData> employeeContainer = new BeanItemContainer<>(EmployeePrivateData.class);
	
	@Override
	public List<OccupationData> loadOccupationData() {
    	return getObject(HttpMethod.GET, commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_ALL), null, OccupationTransferObject.class).getOccupationDatas();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BeanItemContainer<OccupationData> getOccupationContainer(ServletContext servletContext) {
		this.occupationContainer.removeAllItems();
		this.occupationContainer.addAll((List<OccupationData>) servletContext.getAttribute(WebServiceConstant.CONTEXT_CONSTANT_OCCUPATION));
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
		return occupationContainer;
	}
	
	@Override
	public List<AreaData> loadAreaData() {
    	return getObject(HttpMethod.GET, commonRest.getUrl(WebServiceConstant.HTTP_AREA_ALL), null, AreaTransferObject.class).getAreaDatas();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BeanItemContainer<AreaData> getAreaContainer(ServletContext servletContext) {
		this.areaContainer.removeAllItems();
		this.areaContainer.addAll((List<AreaData>) servletContext.getAttribute(WebServiceConstant.CONTEXT_CONSTANT_AREA));
    	this.areaContainer.removeContainerProperty("id");
    	this.areaContainer.removeContainerProperty("code");
    	this.areaContainer.removeContainerProperty("status");
    	this.areaContainer.removeContainerProperty("remarks");
    	this.areaContainer.removeContainerProperty("createdBy");
    	this.areaContainer.removeContainerProperty("createdIP");
    	this.areaContainer.removeContainerProperty("createdTime");
    	this.areaContainer.removeContainerProperty("createdPlatform");
    	this.areaContainer.removeContainerProperty("modifiedBy");
    	this.areaContainer.removeContainerProperty("modifiedIP");
    	this.areaContainer.removeContainerProperty("modifiedTime");
    	this.areaContainer.removeContainerProperty("modifiedPlatform");
		return areaContainer;
	}	

	@Override
	public List<EmployeePrivateData> loadEmployeeAreaSalesManagerData() {
		EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
		HashMap<String, Object> findEmployeeData = new HashMap<String, Object>();
		findEmployeeData.put(EnumFieldEmployee.ROLE_EMPLOYE.toString(), RoleConstant.ROLE_AREASALESMANAGER);
		employeeTransferObject.setParameterData(findEmployeeData);
		return getObject(HttpMethod.POST, commonRest.getUrl(WebServiceConstant.HTTP_EMPLOYEE_ALL), employeeTransferObject, EmployeePrivateTransferObject.class).getEmployeePrivateDatas();		
	}
	
	@Override
	public List<EmployeePrivateData> loadEmployeeNationalSalesManagerData() {
		EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
		HashMap<String, Object> findEmployeeData = new HashMap<String, Object>();
		findEmployeeData.put(EnumFieldEmployee.ROLE_EMPLOYE.toString(), RoleConstant.ROLE_NATIONALSALESMANAGER);
		employeeTransferObject.setParameterData(findEmployeeData);
		return getObject(HttpMethod.POST, commonRest.getUrl(WebServiceConstant.HTTP_EMPLOYEE_ALL), employeeTransferObject, EmployeePrivateTransferObject.class).getEmployeePrivateDatas();		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BeanItemContainer<EmployeePrivateData> employeeAreaSalesManagerContainer(ServletContext servletContext) {
		this.employeeContainer.removeAllItems();
		this.employeeContainer.addAll((List<EmployeePrivateData>) servletContext.getAttribute(WebServiceConstant.CONTEXT_CONSTANT_EMPLOYEE_AREASALESMANAGER));
		this.employeeContainer.removeContainerProperty("code");
		this.employeeContainer.removeContainerProperty("nik");
		this.employeeContainer.removeContainerProperty("occupationCode");
		return employeeContainer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BeanItemContainer<EmployeePrivateData> employeeNationalSalesManagerContainer(ServletContext servletContext) {
		this.employeeContainer.removeAllItems();
		this.employeeContainer.addAll((List<EmployeePrivateData>) servletContext.getAttribute(WebServiceConstant.CONTEXT_CONSTANT_EMPLOYEE_NATIONALSALESMANAGER));
		this.employeeContainer.removeContainerProperty("code");
		this.employeeContainer.removeContainerProperty("nik");
		this.employeeContainer.removeContainerProperty("occupationCode");
		return employeeContainer;
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
