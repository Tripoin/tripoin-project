package com.tripoin.core.rest.template;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class ABaseHttpRest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ABaseHttpRest.class);

	@Autowired
	private RestTemplate restTemplate;
	private HttpStatus statusCode;
	private final Map<String, String> cookies = new HashMap<>();
	protected HttpHeaders httpHeaders = new HttpHeaders();
	
	private static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[]{
        new X509TrustManager() {
			
        	 public java.security.cert.X509Certificate[] getAcceptedIssuers(){
 				return null;
             }
             public void checkClientTrusted( X509Certificate[] certs, String authType ){}
             public void checkServerTrusted( X509Certificate[] certs, String authType ){}
		}
    };

	protected RestTemplate getTemplate() throws Exception {
		if(isSSL()){
			final  SSLContext sslContext = SSLContext.getInstance(typeSSL());
			sslContext.init( null, UNQUESTIONING_TRUST_MANAGER, new java.security.SecureRandom() );
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
	        			
			restTemplate.setRequestFactory( new SimpleClientHttpRequestFactory() {
			    @Override
			    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
			        if(connection instanceof HttpsURLConnection ){
			            ((HttpsURLConnection) connection).setHostnameVerifier(new NullHostnameVerifier());
			        }
			        super.prepareConnection(connection, httpMethod);
			    }
			});
		}
		return restTemplate;
	}

	protected void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	
	public HttpStatus getStatusCode(){
		return statusCode;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookie(String key, String value) {
		cookies.put(key, value);
	}	

	public void clearAllCookies() {
		cookies.clear();
	}

	public void removeCookie(String key) {
		cookies.remove(key);
	}
	
	protected <T> T getObject(HttpMethod method, String url, Object data, Class<T> clazz) {
		HttpEntity<?> request = new HttpEntity<Object>(data, httpHeaders);
		ResponseEntity<T> response = null;
		try{
			response = getTemplate().exchange(url, method, request, clazz);
			extractCookies(response.getHeaders());
		}catch(HttpClientErrorException hcee){
			hcee.printStackTrace();
			LOGGER.warn("HttpClientErrorException : ".concat(hcee.getMessage()));
			clearAllCookies();
		}catch(ResourceAccessException e){
			e.printStackTrace();
			LOGGER.warn("Exception : ".concat("Connection refused, please check Web Service"));
			clearAllCookies();
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error("Exception : ".concat(e.getMessage()), e);
			clearAllCookies();		
		}
		httpHeaders = new HttpHeaders();
		setStatusCode(response.getStatusCode());
		LOGGER.debug("Response Status Code : "+response.getStatusCode());
		LOGGER.debug("Response Headers : "+response.getHeaders());
		return response.getBody();
	}

	public abstract HttpHeaders encodeUserCredentials(HttpHeaders headers, String username, String password);
	
	/**
	 * <b>Description :</b><br>
	 * Extract cookies from a response header. Will overight previous cookies if new one arrive with
	 * the same key.
	 *
	 * @param headers response header
	 */
	public void extractCookies(HttpHeaders headers) {
		List<String> cookiesList = headers.get("Set-Cookie");
		if (cookiesList != null && !cookiesList.isEmpty()) {
			String cookiesStr = cookiesList.get(0);
			String[] cookiesSplit = cookiesStr.split("; ");
			for (String cookieStr : cookiesSplit) {
				String[] keyValueSplit = cookieStr.split("=");
				if(!"HttpOnly".equals(cookieStr) || keyValueSplit.length > 1)
					cookies.put(keyValueSplit[0], keyValueSplit[1]);
			}
		}
	}

	/**
	 * <b>Description :</b><br>
	 * Get the cookies string to write in the request header.
	 *
	 * @return cookies as string
	 */
	public String getCookiesString() {
		StringBuilder sb = new StringBuilder();
		if (!getCookies().isEmpty()) {
			for(Entry<String, String> entry : getCookies().entrySet()){
				sb.append(entry.getKey());
				sb.append("=");
				sb.append(entry.getValue());
				sb.append(";");
			}
			sb = new StringBuilder(sb.toString().replaceAll("Path;", ""));
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
	public abstract HttpHeaders getHeaders(); 

	public abstract boolean isSSL();
	
	public abstract String typeSSL();
	
}
