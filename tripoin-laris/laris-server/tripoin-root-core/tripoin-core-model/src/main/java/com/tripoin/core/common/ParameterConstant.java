package com.tripoin.core.common;

import java.text.SimpleDateFormat;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class ParameterConstant {

	public static final String HOST_SERVER = "tripoin.host.server";

	private static final String formatDefault = "dd-MM-yyyy HH:mm:ss.S";
	private static final String formatISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";	
	
	public static SimpleDateFormat FORMAT_DEFAULT = new SimpleDateFormat(formatDefault);
	public static SimpleDateFormat FORMAT_TIME_BCA = new SimpleDateFormat(formatISO8601);

	public static final String IP_ADDRESSV4_DEFAULT = "127.0.0.1";
	public static final String PLATFORM_DEFAULT = "Computer | Unknown Operating System | Unknown Browser";

	public static final String IDENTIFIER_TIME = "IDENTIFIER_TIME";
	public static final String IDENTIFIER_IP = "IDENTIFIER_IP";
	public static final String IDENTIFIER_PLATFORM = "IDENTIFIER_PLATFORM";
	
	public static final String RESPONSE_SUCCESS = "SUCCESS";
	public static final String RESPONSE_FAILURE = "FAILURE";

	public static final String FEMALE = "Female";
	public static final String MALE = "Male";

	public static final String SELLER = "Seller";
	public static final String BUYER = "Buyer";
	
	public static final Integer ROW_PER_PAGE = 20;

	public static final String VIEW_TYPE = "VIEW-TYPE=";
	public static final String VIEW_WEB_MOBILE = "WEB-MOBILE";
	public static final String VIEW_WEB = "WEB";
	public static final String VIEW_MOBILE = "MOBILE"; 

	public static final String MENU_PAGE = "MENU-PAGE";
	public static final String MENU_NON_PAGE = "MENU-NON-PAGE";

	public static final String FORGOT_PASSWORD_EMAIL = "EMAIL-FORGOT-PASSWORD=";
	public static final String FORGOT_PASSWORD_USERNAME = "USERNAME-FORGOT-PASSWORD=";
	public static final String FORGOT_PASSWORD_UUID = "UUID-FORGOT-PASSWORD=";
	public static final String FORGOT_PASSWORD_SUBJECT = "TRIPOIN.EMAIL.FORGOT.PASSWORD.SUBJECT";
	public static final String FORGOT_PASSWORD_BODY = "TRIPOIN.EMAIL.FORGOT.PASSWORD.BODY.MESSAGE";
	public static final String FORGOT_PASSWORD_VERIFY_SUBJECT = "TRIPOIN.EMAIL.FORGOT.PASSWORD.VERIFY.SUBJECT";
	public static final String FORGOT_PASSWORD_VERIFY_BODY = "TRIPOIN.EMAIL.FORGOT.PASSWORD.VERIFY.BODY.MESSAGE";

	public static final String NEW_USER_SUBJECT = "TRIPOIN.EMAIL.NEW.USER.SUBJECT";
	public static final String NEW_USER_BODY = "TRIPOIN.EMAIL.NEW.USER.BODY.MESSAGE";

	public static final String TRIPOIN_CONTENT_FULLNAME = "\\$\\{TRIPOIN.CONTENT.FULLNAME\\}";
	public static final String TRIPOIN_CONTENT_USERNAME = "\\$\\{TRIPOIN.CONTENT.USERNAME\\}";
	public static final String TRIPOIN_CONTENT_PASSWORD = "\\$\\{TRIPOIN.CONTENT.PASSWORD\\}";
	public static final String TRIPOIN_CONTENT_URL = "\\$\\{TRIPOIN.CONTENT.URL\\}";	

	public static final String TRIPOIN_UPLOAD_IMAGE = "TRIPOIN-UPLOAD-IMAGE";
	public static final String TRIPOIN_UPLOAD_IMAGE_CREATED_BY = "TRIPOIN-UPLOAD-IMAGE-CREATED-BY";
	public static final String TRIPOIN_UPLOAD_IMAGE_CREATED_IP = "TRIPOIN-UPLOAD-IMAGE-CREATED-IP";
	public static final String TRIPOIN_UPLOAD_IMAGE_CREATED_PLATFORM = "TRIPOIN-UPLOAD-IMAGE-CREATED-PLATFORM";
	public static final String TRIPOIN_UPLOAD_DATA = "TRIPOIN-UPLOAD-DATA";
	
	public static final String TRIPOIN_AUTHORIZATION = "TRIPOIN-AUTHORIZATION=";
	
}
