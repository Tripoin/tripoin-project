package com.tripoin.web.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
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

import com.tripoin.core.dto.AvailabilityTransferObject;
import com.tripoin.core.dto.CategoryData;
import com.tripoin.core.dto.CategoryTransferObject;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.ProductData;
import com.tripoin.core.dto.ProductTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.WebServiceConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-web-service.xml" })
public class WebServiceGETTest implements ApplicationContextAware {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(WebServiceGETTest.class);
	
	@Autowired
	private ICommonRest commonRest;
	
	@Autowired
	private StateFullRestTest stateFullRest;
		
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
	/*@Test(expected=HttpClientErrorException.class)*/
	public void runResponseGetTest() {
		stateFullRest.setUsername("ridla");
		stateFullRest.setPassword("ridla");
		
		GeneralTransferObject generalConnectionDTO = stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_CONNECTION), GeneralTransferObject.class);		
		LOGGER.debug("Response Message Connection : ".concat(generalConnectionDTO.getResponseMsg()));		
	}
	
	@After
	public void runResponsePostTest() {
		stateFullRest.setUsername(null);
		stateFullRest.setPassword(null);
		
		AvailabilityTransferObject availabilityTransferObject = stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_AVAILABILITY), AvailabilityTransferObject.class);
		LOGGER.debug("Response Body Availabilities : "+availabilityTransferObject.getAvailabilityDatas());
		
		CategoryTransferObject categoryTransferObject = stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_CATEGORY), CategoryTransferObject.class);
		LOGGER.debug("Response Body Categories : "+categoryTransferObject.getCategoryDatas());
		
		ProductData productData = new ProductData();
		productData.setProductName("Indonesian Dictionary");
		productData.setPrice(new BigDecimal(2.4));
		productData.setStockCount(3);
		productData.setAvailabilityData(availabilityTransferObject.getAvailabilityDatas().get(0));
		List<CategoryData> categoryDatas = new ArrayList<CategoryData>();
		categoryDatas.add(categoryTransferObject.getCategoryDatas().get(0));
		productData.setCategoryDatas(categoryDatas);		
		ProductTransferObject producTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_PRODUCT_SAVE), productData, ProductTransferObject.class);		
		LOGGER.debug("Response Body Products : "+producTransferObject.getProductDatas());		
	}

}
