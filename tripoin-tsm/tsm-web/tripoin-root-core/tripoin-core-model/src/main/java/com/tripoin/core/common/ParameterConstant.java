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

	public static final String FEMALE = "Female";
	public static final String MALE = "Male";

	public static final String VIEW_TYPE = "VIEW-TYPE=";
	public static final String VIEW_WEB_MOBILE = "WEB-MOBILE";
	public static final String VIEW_WEB = "WEB";
	public static final String VIEW_MOBILE = "MOBILE"; 

	public static final String MENU_PAGE = "MENU-PAGE";
	public static final String MENU_NON_PAGE = "MENU-NON-PAGE";

	public static final String FORGOT_PASSWORD_EMAIL = "EMAIL-FORGOT-PASSWORD=";
	public static final String FORGOT_PASSWORD_SUBJECT = "TRIPOIN.EMAIL.FORGOT.PASSWORD.SUBJECT";
	public static final String FORGOT_PASSWORD_BODY = "TRIPOIN.EMAIL.FORGOT.PASSWORD.BODY.MESSAGE";

	public static final String TRIPOIN_CONTENT_FULLNAME = "\\$\\{TRIPOIN.CONTENT.FULLNAME\\}";
	public static final String TRIPOIN_CONTENT_USERNAME = "\\$\\{TRIPOIN.CONTENT.USERNAME\\}";
	public static final String TRIPOIN_CONTENT_URL = "\\$\\{TRIPOIN.CONTENT.URL\\}";	
	
}
