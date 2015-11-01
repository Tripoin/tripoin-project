package com.tripoin.web.common;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class WebServiceConstant {

	private static final String HTTP_CONTEXT_DEFAULT = "/wscontext";
	private static final String HTTP_CONTEXT_ANONYMOUS = "/anonymous";
	
	private static final String HTTP_ALL = "/all";
	private static final String HTTP_LOAD = "/load";
	private static final String HTTP_SAVE = "/save";
	private static final String HTTP_UPDATE = "/update";
	private static final String HTTP_VERIFY = "/verify";
	/*private static final String HTTP_DELETE = "/delete";*/
	
	public static final String HTTP_ONLY = "HttpOnly";

	public static final String HTTP_FORGOT_PASSWORD_PATH = "/forgotpassword";
	public static final String HTTP_FORGOT_PASSWORD = HTTP_CONTEXT_ANONYMOUS.concat(HTTP_FORGOT_PASSWORD_PATH);
	public static final String HTTP_FORGOT_PASSWORD_VERIFY = HTTP_CONTEXT_ANONYMOUS.concat("/forgotpassword").concat(HTTP_VERIFY);
	
	public static final String HTTP_CONNECTION = HTTP_CONTEXT_DEFAULT.concat("/connection");	
	public static final String HTTP_LOGIN = HTTP_CONTEXT_DEFAULT.concat("/login");	
	public static final String HTTP_LOGIN_MENU = HTTP_CONTEXT_DEFAULT.concat("/login-menu");
	public static final String HTTP_LOGOUT = HTTP_CONTEXT_DEFAULT.concat("/logout");
	public static final String HTTP_PROFILE = HTTP_CONTEXT_DEFAULT.concat("/profile").concat(HTTP_LOAD);
	public static final String HTTP_PROFILE_UPDATE = HTTP_CONTEXT_DEFAULT.concat("/profile").concat(HTTP_UPDATE);
	public static final String HTTP_USER = HTTP_CONTEXT_DEFAULT.concat("/user").concat(HTTP_LOAD);
	public static final String HTTP_USER_ALL = HTTP_CONTEXT_DEFAULT.concat("/user").concat(HTTP_LOAD).concat(HTTP_ALL);
	public static final String HTTP_USER_SAVE = HTTP_CONTEXT_DEFAULT.concat("/user").concat(HTTP_SAVE);
	public static final String HTTP_USER_UPDATE = HTTP_CONTEXT_DEFAULT.concat("/user").concat(HTTP_UPDATE);

}
