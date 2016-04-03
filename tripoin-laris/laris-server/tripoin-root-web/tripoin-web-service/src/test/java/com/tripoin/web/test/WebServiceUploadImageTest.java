package com.tripoin.web.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
public class WebServiceUploadImageTest implements ApplicationContextAware {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(WebServiceUploadImageTest.class);
	
	@Autowired
	private ICommonRest commonRest;
	
	@Autowired
	private IStateFullRest stateFullRestTest;
	
	private static String resourcePath = "C:\\Temps\\tomcat.gif";
		
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
		
		Resource resourceStar = new FileSystemResource(resourcePath);
		MultiValueMap<String, Object> multipartMap = new LinkedMultiValueMap<String, Object>();
		multipartMap.add(ParameterConstant.TRIPOIN_UPLOAD_IMAGE, resourceStar);
		stateFullRestTest.setMultipart(true);
		GeneralTransferObject generalTransferObject = stateFullRestTest.post(commonRest.getUrl(WebServiceConstant.HTTP_PROFILE_IMAGE), multipartMap, GeneralTransferObject.class);		
		LOGGER.debug("General Transfer Object Message Menu : ".concat(generalTransferObject.getResponseMsg()));
		
	}

}
