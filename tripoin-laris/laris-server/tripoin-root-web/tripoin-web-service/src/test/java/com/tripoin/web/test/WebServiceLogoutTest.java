package com.tripoin.web.test;

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
import com.tripoin.core.dto.MenuData;
import com.tripoin.core.dto.MenuTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-web-service.xml",
		"classpath:META-INF/spring/applicationContext-web-service-test.xml"})
public class WebServiceLogoutTest implements ApplicationContextAware {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(WebServiceLogoutTest.class);
	
	@Autowired
	private ICommonRest commonRest;
	
	@Autowired
	private IStateFullRest stateFullRestTest;
		
	private ApplicationContext applicationContext;
	
	public <T> T getBean(Class<T> beanType) {
		return applicationContext.getBean(beanType);
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Test
	public void runResponseGetTest() {
		stateFullRestTest.setUsername("admin");
		stateFullRestTest.setPassword("admin");
		
		MenuTransferObject menuTransferObject = stateFullRestTest.post(commonRest.getUrl(WebServiceConstant.HTTP_LOGIN_MENU), ParameterConstant.VIEW_TYPE.concat(ParameterConstant.VIEW_WEB), MenuTransferObject.class);		
		LOGGER.debug("Response Message Menu : ".concat(menuTransferObject.getResponseMsg()));
		for(MenuData menuData : menuTransferObject.getMenuDatas())
			LOGGER.debug("Response Data Menu : ".concat(menuData.toString()));
	}

}
