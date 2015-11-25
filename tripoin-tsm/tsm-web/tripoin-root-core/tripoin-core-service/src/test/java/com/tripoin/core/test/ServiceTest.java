package com.tripoin.core.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.pojo.Employee;
import com.tripoin.core.pojo.Menu;
import com.tripoin.core.pojo.Occupation;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.SystemParameter;
import com.tripoin.core.pojo.User;
import com.tripoin.core.pojo.UserRoute;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.IStanGenerator;
import com.tripoin.core.service.util.ISystemParameterService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath*:/META-INF/spring/datasource/dataSourceContext-mysql.xml",
		"classpath*:/META-INF/spring/applicationContext-jpa.xml"})
public class ServiceTest implements ApplicationContextAware  {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(ServiceTest.class);
	
	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private ISystemParameterService systemParameterService;
	
	@Autowired
	private IStanGenerator stanManager;
	
	private ApplicationContext applicationContext;
	
	public <T> T getBean(Class<T> beanType) {
		return applicationContext.getBean(beanType);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	@Test
	public void runtTestMain() throws Exception{
		runTestUserRoute();
	}
	
	public void runTestUser() throws Exception {
		String username = "tripoin.app.web";	
		
		List<User> users = iGenericManagerJpa.loadObjectsJQLStatement("FROM User WHERE username = ?", new Object[]{username}, null);
		for(User user : users) LOGGER.debug("User Data : "+user);
		
		FilterArgument[] filterArguments = new FilterArgument[] { 
				new FilterArgument("user.username", ECommonOperator.EQUALS) 
		};
		List<Profile> profileList = iGenericManagerJpa.loadObjectsFilterArgument(Profile.class, filterArguments, new Object[] { username }, null, null);
		for(Profile profile : profileList) {
			LOGGER.debug("Profile Data : "+profile);
		}
	}
	
	public void runTestMenu() throws Exception {
		List<Menu> menus = iGenericManagerJpa.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ? ORDER BY mn.tree ASC", new Object[]{"ROLE_ADMIN"}, null);
		for(Menu menu : menus) {
			LOGGER.debug("Menu Data : "+menu);
		}
	}
	
	public void runTestSystemParameter() throws Exception {		
		List<SystemParameter> systemParameters = systemParameterService.listValue(new Object[]{ParameterConstant.FORGOT_PASSWORD_SUBJECT, ParameterConstant.FORGOT_PASSWORD_BODY});
		for(SystemParameter systemParameter : systemParameters) {
			LOGGER.debug("System Parameter : "+systemParameter);
		}
	}
	
	public void runTestOccupation() throws Exception {		
		List<Occupation> occupationList = iGenericManagerJpa.loadObjects(Occupation.class);
		for(Occupation occupation : occupationList) {
			LOGGER.debug("Occupation Data : "+occupation);
		}
	}
	
	public void runTestEmployee() throws Exception {
		String username = "ridla";
		
		FilterArgument[] filterArguments = new FilterArgument[] { 
				new FilterArgument("user.username", ECommonOperator.EQUALS) 
		};
		List<Employee> employeeList = iGenericManagerJpa.loadObjectsFilterArgument(Employee.class, filterArguments, new Object[] { username }, null, null);
		for(Employee employee : employeeList) {
			LOGGER.debug("Employee Data : "+employee);
		}
	}
	
	public void runTestUserRoute() throws Exception {
		String nik = "TSM201511250001";
		
		FilterArgument[] filterArguments = new FilterArgument[] { 
				new FilterArgument("employee.nik", ECommonOperator.EQUALS) 
		};
		List<UserRoute> userRouteList = iGenericManagerJpa.loadObjectsFilterArgument(UserRoute.class, filterArguments, new Object[] { nik }, null, null);
		for(UserRoute userRoute : userRouteList) {
			LOGGER.debug("User Route Data : "+userRoute);
		}
	}

}
