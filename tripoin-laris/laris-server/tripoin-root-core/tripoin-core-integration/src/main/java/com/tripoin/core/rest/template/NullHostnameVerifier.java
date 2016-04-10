package com.tripoin.core.rest.template;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class NullHostnameVerifier implements HostnameVerifier {
	
	public boolean verify(String hostname, SSLSession session) {
		return true;
	}
	
}