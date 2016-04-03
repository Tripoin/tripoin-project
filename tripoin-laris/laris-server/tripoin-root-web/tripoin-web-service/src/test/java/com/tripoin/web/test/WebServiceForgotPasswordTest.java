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
import com.tripoin.dto.app.GeneralTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-web-service.xml",
		"classpath:META-INF/spring/applicationContext-web-service-test.xml"})
public class WebServiceForgotPasswordTest implements ApplicationContextAware {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(WebServiceForgotPasswordTest.class);
	
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
	public void runResponsePostTest() {
		String email = "ridla.fadilah@gmail.com";
		stateFullRestTest.clearAllCookies();
		stateFullRestTest.setUsername("tripoin.app.web");
		stateFullRestTest.setPassword("72jsoH!=jn3oskqPHJ#@");
		GeneralTransferObject generalTransferObject = stateFullRestTest.post(commonRest.getUrl(WebServiceConstant.HTTP_FORGOT_PASSWORD), ParameterConstant.FORGOT_PASSWORD_EMAIL.concat(email), GeneralTransferObject.class);
		stateFullRestTest.clearAllCookies();
		LOGGER.debug("General Transfer Object : ".concat(generalTransferObject.toString()));
	}

}
