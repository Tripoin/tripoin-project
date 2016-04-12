package com.tripoin.core.rest.handler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

	private static transient final Logger LOGGER = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request,body);
        ClientHttpResponse response = execution.execute(request, body);
        LOGGER.debug("Interceptor Response Status Code",response.getRawStatusCode());
        if(response.getRawStatusCode() >= 400)
            return interceptNotSuccess(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        LOGGER.debug("Interceptor Request URL : "+request.getURI());
        LOGGER.debug("Interceptor Request Method : "+request.getMethod());
        LOGGER.debug("Interceptor Request Headers : "+request.getHeaders());
        LOGGER.debug("Interceptor Request Body : "+new String(body, StandardCharsets.UTF_8));
    }

    private ClientHttpResponse interceptNotSuccess(final ClientHttpResponse response) throws IOException {
    	final InputStream inputStreamData = response.getBody();
        final StringWriter writer = new StringWriter();
        IOUtils.copy(inputStreamData, writer, StandardCharsets.UTF_8);
        final String responseBody = writer.toString()
        		.replaceAll("\" , \"", "\",\"")
        		.replaceAll("\", \"", "\",\"")
        		.replaceAll("\" ,\"", "\",\"");
        LOGGER.debug("Interceptor Response Status Code: "+response.getRawStatusCode());
        LOGGER.debug("Interceptor Response Body : "+responseBody);
        return new ClientHttpResponse() {			
			@Override
			public HttpHeaders getHeaders() {
				return response.getHeaders();
			}			
			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream(responseBody.getBytes(StandardCharsets.UTF_8));
			}			
			@Override
			public String getStatusText() throws IOException {
				return response.getStatusText();
			}			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}			
			@Override
			public int getRawStatusCode() throws IOException {
				return response.getRawStatusCode();
			}			
			@Override
			public void close() {
				response.close();				
			}
		};
    }
    
}