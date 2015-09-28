package com.tripoin.core.common;

import java.text.SimpleDateFormat;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class ParameterConstant {

	public static final String HOST_SERVER = "tripoin.host.server";
	
	private static final String formatDefault = "dd-MM-yyyy HH:mm:ss.S";	
	public static SimpleDateFormat FORMAT_DEFAULT = new SimpleDateFormat(formatDefault);
	
	public static final String RESPONSE_SUCCESS = "SUCCESS";
	public static final String RESPONSE_FAILURE = "FAILURE";
	
}
