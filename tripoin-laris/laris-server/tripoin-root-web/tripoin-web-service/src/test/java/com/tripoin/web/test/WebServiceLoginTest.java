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
import com.tripoin.dto.app.MenuData;
import com.tripoin.dto.request.DTORequestLogin;
import com.tripoin.dto.response.DTOResponseLogin;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-web-service-test.xml"})
public class WebServiceLoginTest implements ApplicationContextAware {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(WebServiceLoginTest.class);
	
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
		stateFullRestTest.setUsername("081221356663");
		stateFullRestTest.setPassword("admin");
		
		DTORequestLogin dtoRequestLogin = new DTORequestLogin();
		dtoRequestLogin.setViewType(ParameterConstant.VIEW_WEB);
		
		DTOResponseLogin dtoResponseLogin = stateFullRestTest.post(commonRest.getUrl(WebServiceConstant.HTTP_LOGIN_MENU), dtoRequestLogin, DTOResponseLogin.class);		
		LOGGER.debug("Response Message Menu : ".concat(dtoResponseLogin.getResponseMsg()));
		for(MenuData menuData : dtoResponseLogin.getMenuDatas())
			LOGGER.debug("Response Data Menu : ".concat(menuData.toString()));
	}

}
