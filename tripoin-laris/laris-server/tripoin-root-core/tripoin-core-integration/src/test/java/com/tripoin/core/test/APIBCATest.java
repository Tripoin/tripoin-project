package com.tripoin.core.test;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tripoin.core.common.APIConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.pojo.APIType;
import com.tripoin.core.rest.endpoint.api.bca.WebServiceBCAConstant;
import com.tripoin.core.rest.template.IStateFullRest;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.dto.response.bca.DTOResponseOAuthBCA;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {})
public class APIBCATest implements ApplicationContextAware {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(APIBCATest.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private IStateFullRest stateFullRest;
	
	private String grantType;
	private String userCredentials;

	@Value("${tripoin.api.bca.granttype}")	
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	@Value("${tripoin.api.bca.clientcredentials}")
	public void setUserCredentials(String userCredentials) {
		this.userCredentials = userCredentials;
	}
	
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
		getTokenStateFullRest();
	}

	public void getTokenStateFullRest() {
		FilterArgument[] filterArgument = new FilterArgument[]{
				new FilterArgument("code", ECommonOperator.EQUALS)	
		};
		try {
			APIType apiType = iGenericManagerJpa.loadObjectsFilterArgument(APIType.class, filterArgument, 
					new Object[]{APIConstant.BCA.getOperator()}, null, null).get(0);

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			httpHeaders.add("Content-type","application/x-www-form-urlencoded; charset=UTF-8");
			httpHeaders.add("Accept-Encoding", "gzip, deflate");
			httpHeaders.add("Accept-Language", "en-US,en;q=0.5");
			httpHeaders.add("Host", "api.finhacks.id");
			httpHeaders.add("Connection", "keep-alive");
			httpHeaders = stateFullRest.encodeUserCredentials(httpHeaders, "tripoin", "tripoin");
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add(grantType, userCredentials);
			
			DTOResponseOAuthBCA dTOResponseOAuthBCA = new DTOResponseOAuthBCA();
			try {
				String urlString = apiType.getHost().concat(WebServiceBCAConstant.HTTP_OAUTH_TOKEN);
				System.out.println(urlString);
				stateFullRest.setHeaders(httpHeaders);
				stateFullRest.setSSL(true);
				dTOResponseOAuthBCA = stateFullRest.post(urlString, map, DTOResponseOAuthBCA.class);
				LOGGER.debug(dTOResponseOAuthBCA.toString());
				/*String as = stateFullRest.post(urlString, map, String.class);
				LOGGER.debug(as);*/
			} catch (Exception e) {
				LOGGER.error("Error Rest",e);
			}
		} catch (Exception e1) {
			LOGGER.error("Error",e1);
		}
	}
}
