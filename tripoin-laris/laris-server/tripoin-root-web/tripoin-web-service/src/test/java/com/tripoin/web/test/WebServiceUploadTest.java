package com.tripoin.web.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class WebServiceUploadTest {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(WebServiceUploadTest.class);
	
	private static String uri = "http://localhost:8081/tripoin/wscontext/upload";
	private static String resourcePath = "com/tripoin/web/test/star.png";

	@Test
	public void runResponseTest() {
		RestTemplate template = new RestTemplate();
		Resource resourceStar = new ClassPathResource(resourcePath);
		MultiValueMap<String, Object> multipartMap = new LinkedMultiValueMap<String, Object>();
		multipartMap.add("tripoin", "Tripoin Upload");
		multipartMap.add("tripoin-upload", resourceStar);
		LOGGER.info("Created multipart request: " + multipartMap);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("multipart", "form-data"));
		headers.set("Authorization", "Basic cmlkbGE6cmlkbGE=");
		HttpEntity<Object> request = new HttpEntity<Object>(multipartMap, headers);
		LOGGER.info("Posting request to : " + uri);
		ResponseEntity<?> httpResponse = template.exchange(uri, HttpMethod.POST, request, Object.class);
		if (!httpResponse.getStatusCode().equals(HttpStatus.OK)){
			LOGGER.error("Problems with the request. Http status: " + httpResponse.getStatusCode());
		}
		LOGGER.info("Response body : " + httpResponse.getBody().toString());
	}
	
}
