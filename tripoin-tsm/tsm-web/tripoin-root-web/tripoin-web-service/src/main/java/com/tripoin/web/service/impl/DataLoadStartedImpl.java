package com.tripoin.web.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.AreaData;
import com.tripoin.core.dto.AreaTransferObject;
import com.tripoin.core.dto.EmployeeData;
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
	private BeanItemContainer<EmployeeData> employeeContainer = new BeanItemContainer<>(EmployeeData.class);
	
	@Override
	public List<OccupationData> loadOccupationData() {
    	return getObject(HttpMethod.GET, commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_ALL), null, OccupationTransferObject.class).getOccupationDatas();
	}
	
	@Override
	public List<AreaData> loadAreaData() {
    	return getObject(HttpMethod.GET, commonRest.getUrl(WebServiceConstant.HTTP_AREA_ALL), null, AreaTransferObject.class).getAreaDatas();
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
	public List<EmployeeData> loadEmployeeAreaSalesManagerData() {
		EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
		Map<String, Object> findEmployeeData = new HashMap<String, Object>();
		findEmployeeData.put(EnumFieldEmployee.ROLE_EMPLOYE.toString(), RoleConstant.ROLE_AREASALESMANAGER);
		employeeTransferObject.setFindEmployeeData(findEmployeeData);
		return getObject(HttpMethod.POST, commonRest.getUrl(WebServiceConstant.HTTP_EMPLOYEE_ALL), employeeTransferObject, EmployeeTransferObject.class).getEmployeeDatas();		
	}
	
	@Override
	public List<EmployeeData> loadEmployeeNationalSalesManagerData() {
		EmployeeTransferObject employeeTransferObject = new EmployeeTransferObject();
		Map<String, Object> findEmployeeData = new HashMap<String, Object>();
		findEmployeeData.put(EnumFieldEmployee.ROLE_EMPLOYE.toString(), RoleConstant.ROLE_NATIONALSALESMANAGER);
		employeeTransferObject.setFindEmployeeData(findEmployeeData);
		return getObject(HttpMethod.POST, commonRest.getUrl(WebServiceConstant.HTTP_EMPLOYEE_ALL), employeeTransferObject, EmployeeTransferObject.class).getEmployeeDatas();		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BeanItemContainer<EmployeeData> employeeAreaSalesManagerContainer(ServletContext servletContext) {
		this.employeeContainer.removeAllItems();
		this.employeeContainer.addAll((List<EmployeeData>) servletContext.getAttribute(WebServiceConstant.CONTEXT_CONSTANT_EMPLOYEE_AREASALESMANAGER));
		this.employeeContainer.removeContainerProperty("id");
		this.employeeContainer.removeContainerProperty("code");
		this.employeeContainer.removeContainerProperty("nik");
		this.employeeContainer.removeContainerProperty("status");
		this.employeeContainer.removeContainerProperty("remarks");
		this.employeeContainer.removeContainerProperty("createdBy");
		this.employeeContainer.removeContainerProperty("createdIP");
		this.employeeContainer.removeContainerProperty("createdTime");
		this.employeeContainer.removeContainerProperty("createdPlatform");
		this.employeeContainer.removeContainerProperty("modifiedBy");
		this.employeeContainer.removeContainerProperty("modifiedIP");
		this.employeeContainer.removeContainerProperty("modifiedTime");
		this.employeeContainer.removeContainerProperty("modifiedPlatform");
		this.employeeContainer.removeContainerProperty("profileData");
		this.employeeContainer.removeContainerProperty("occupationData");
		this.employeeContainer.removeContainerProperty("employeeDataParent");
		this.employeeContainer.addNestedContainerProperty("profileData.name");
		return employeeContainer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BeanItemContainer<EmployeeData> employeeNationalSalesManagerContainer(ServletContext servletContext) {
		this.employeeContainer.removeAllItems();
		this.employeeContainer.addAll((List<EmployeeData>) servletContext.getAttribute(WebServiceConstant.CONTEXT_CONSTANT_EMPLOYEE_NATIONALSALESMANAGER));
		this.employeeContainer.removeContainerProperty("id");
		this.employeeContainer.removeContainerProperty("code");
		this.employeeContainer.removeContainerProperty("nik");
		this.employeeContainer.removeContainerProperty("status");
		this.employeeContainer.removeContainerProperty("remarks");
		this.employeeContainer.removeContainerProperty("createdBy");
		this.employeeContainer.removeContainerProperty("createdIP");
		this.employeeContainer.removeContainerProperty("createdTime");
		this.employeeContainer.removeContainerProperty("createdPlatform");
		this.employeeContainer.removeContainerProperty("modifiedBy");
		this.employeeContainer.removeContainerProperty("modifiedIP");
		this.employeeContainer.removeContainerProperty("modifiedTime");
		this.employeeContainer.removeContainerProperty("modifiedPlatform");
		this.employeeContainer.removeContainerProperty("profileData");
		this.employeeContainer.removeContainerProperty("occupationData");
		this.employeeContainer.removeContainerProperty("employeeDataParent");
		this.employeeContainer.addNestedContainerProperty("profileData.name");
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
