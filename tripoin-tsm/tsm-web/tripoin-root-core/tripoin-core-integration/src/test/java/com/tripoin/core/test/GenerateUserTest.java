package com.tripoin.core.test;

import org.jasypt.digest.StandardStringDigester;
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

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-mail.xml",
		"classpath:META-INF/spring/datasource/dataSourceContext-mysql.xml",
		"classpath:META-INF/spring/applicationContext-jpa.xml",
		"classpath*:WEB-INF/config/security-integration-config.xml" })
public class GenerateUserTest implements ApplicationContextAware {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(GenerateUserTest.class);
	
	@Autowired
	private StandardStringDigester jasyptStringDigester;
	
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
	public void runTest() throws Exception {
		LOGGER.debug("Password spring : ".concat(jasyptStringDigester.digest("72jsoH!=jn3oskqPHJ#@")));		
	}

}
