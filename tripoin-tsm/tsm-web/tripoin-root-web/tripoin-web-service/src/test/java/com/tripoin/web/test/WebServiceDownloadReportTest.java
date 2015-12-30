package com.tripoin.web.test;

import java.util.ArrayList;
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

import com.tripoin.core.dto.GeneralReportTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-web-service.xml",
		"classpath:META-INF/spring/applicationContext-web-service-test.xml"})
public class WebServiceDownloadReportTest implements ApplicationContextAware {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(WebServiceDownloadReportTest.class);
	
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
		GeneralReportTransferObject generalReportTransferObject = new GeneralReportTransferObject();
		List<OccupationData> oc = new ArrayList<OccupationData>();
		OccupationData o = new OccupationData();
		o.setName("Occupation Test");
		o.setRemarks("Occupation Test");
		generalReportTransferObject.setDataSelection(oc);
		generalReportTransferObject.setTemplateReportName("Occupation.jasper");
		stateFullRestTest.setDownloadedFile(true);
		stateFullRestTest.setUsername("tripoin.app.web");
		stateFullRestTest.setPassword("72jsoH!=jn3oskqPHJ#@");
		Byte[] generalTransferObject = stateFullRestTest.get(commonRest.getUrl(WebServiceConstant.HTTP_REPORT_SELECTED), Byte[].class);
		stateFullRestTest.clearAllCookies();
		LOGGER.debug("General Transfer Object : ".concat(generalTransferObject.toString()));
	}

}
