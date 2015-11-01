package com.tripoin.core.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.pojo.SystemParameter;
import com.tripoin.core.service.util.ISystemParameterService;
import com.tripoin.util.mail.ICoreMailSender;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-mail.xml",
		"classpath:META-INF/spring/datasource/dataSourceContext-mysql.xml",
		"classpath:META-INF/spring/applicationContext-jpa.xml" })
public class SendMailTest implements ApplicationContextAware {
	
	@Autowired
	private ISystemParameterService systemParameterService;

	@Autowired
	private ICoreMailSender iCoreMailSender;
	
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
		List<SystemParameter> systemParameters = systemParameterService.listValue(new Object[]{ParameterConstant.FORGOT_PASSWORD_SUBJECT, ParameterConstant.FORGOT_PASSWORD_BODY});
	    Map<String, String> mapSystemParamter = new HashMap<String, String>();
	    for(SystemParameter systemParameter : systemParameters)
	    	mapSystemParamter.put(systemParameter.getCode(), systemParameter.getValue());
		iCoreMailSender.sendMailContent("noreply@tripoin.co.id", "ridla.fadilah@gmail.com", mapSystemParamter.get(ParameterConstant.FORGOT_PASSWORD_SUBJECT), mapSystemParamter.get(ParameterConstant.FORGOT_PASSWORD_BODY));		
	}

}
