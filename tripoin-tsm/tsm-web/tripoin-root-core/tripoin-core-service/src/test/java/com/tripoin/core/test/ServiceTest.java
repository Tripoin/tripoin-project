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

import com.tripoin.core.dao.filter.PageArgument;
import com.tripoin.core.pojo.Menu;
import com.tripoin.core.pojo.Product;
import com.tripoin.core.pojo.User;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.IStanGenerator;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath*:/META-INF/spring/datasource/dataSourceContext-mysql.xml",
		"classpath*:/META-INF/spring/applicationContext-jpa.xml"})
public class ServiceTest implements ApplicationContextAware  {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(ServiceTest.class);
	
	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
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
	public void runTest() throws Exception {
		String value = "ridla";
		List<User> users = iGenericManagerJpa.loadObjectsJQLStatement("FROM User WHERE username = ?", new Object[]{value}, new PageArgument(0,2));
		for(User user : users) LOGGER.debug("User Data : "+user);
		
		List<Product> products = iGenericManagerJpa.loadObjects(Product.class);
		for(Product product : products) {
			LOGGER.debug("Product Data : "+product);
		}

		List<Menu> menus = iGenericManagerJpa.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ?", new Object[]{"ROLE_SUPERADMIN"}, null);
		for(Menu menu : menus) {
			LOGGER.debug("Menu Data : "+menu);
		}
	}

}
