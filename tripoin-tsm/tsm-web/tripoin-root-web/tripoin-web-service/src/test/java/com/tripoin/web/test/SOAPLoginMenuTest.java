package com.tripoin.web.test;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
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
import com.tripoin.core.dto.UserData;
import com.tripoin.core.dto.UserMenuTransferObject;
import com.tripoin.core.service.soap.endpoint.ILoginMenuServiceEndpoint;
import com.tripoin.core.service.soap.handler.WSEndpointFaultException;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.WebServiceConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-web-service.xml",
		"classpath:META-INF/spring/applicationContext-web-service-test.xml"})
public class SOAPLoginMenuTest implements ApplicationContextAware {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(SOAPLoginMenuTest.class);
	
	@Autowired
	private ICommonRest commonRest;
		
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
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ILoginMenuServiceEndpoint.class);
		factory.setAddress(commonRest.getUrl(WebServiceConstant.HTTP_LOGIN_MENU_SOAP));
		factory.setUsername("admin");
		factory.setPassword("admin");
		ILoginMenuServiceEndpoint loginMenuService = (ILoginMenuServiceEndpoint) factory.create();
		try {
			UserMenuTransferObject userMenuTransferObject = loginMenuService.getUserMenu(ParameterConstant.VIEW_WEB);
			LOGGER.debug(userMenuTransferObject.getResponseDesc());
			for(UserData userData : userMenuTransferObject.getUserDatas()){
				LOGGER.debug(userData.toString());
			}
		} catch (WSEndpointFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
