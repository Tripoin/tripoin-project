package com.tripoin.web.common;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class WebServiceConstant {

	private static final String HTTP_CONTEXT_REST = "/wscontext";
	private static final String HTTP_CONTEXT_ANONYMOUS = "/anonymous";
	private static final String HTTP_CONTEXT_API = "/api";
	private static final String HTTP_CONTEXT_RESOURCES = "/resources";
	private static final String HTTP_CONTEXT_SOAP = "/soapcontext";
	
	private static final String HTTP_ALL = "/all";
	private static final String HTTP_LOAD = "/load";
	private static final String HTTP_PAGE = "/paging";
	private static final String HTTP_SAVE = "/save";
	private static final String HTTP_UPDATE = "/update";
	private static final String HTTP_VERIFY = "/verify";
	private static final String HTTP_IMAGE = "/image";
	private static final String HTTP_DELETE = "/delete";
	private static final String HTTP_SELECT = "/select";
	private static final String HTTP_SECRET = "/secret";
	private static final String HTTP_SIGNUP = "/signup";

	public static final String CONTEXT_CONSTANT_ROLES = "ROLES";
	public static final String CONTEXT_CONSTANT_OCCUPATION = "OCCUPATION";
	public static final String CONTEXT_CONSTANT_EMPLOYEE_AREASALESMANAGER = "EMPLOYEE_AREASALESMANAGER";
	public static final String CONTEXT_CONSTANT_EMPLOYEE_NATIONALSALESMANAGER = "EMPLOYEE_NATIONALSALESMANAGER";
	
	public static final String CONTEXT_CONSTANT_AREA = "AREA";
	
	public static final String HTTP_ONLY = "HttpOnly";
	
	public static final String HTTP_RESOURCES_IMAGES = HTTP_CONTEXT_RESOURCES.concat("/images");

	public static final String HTTP_FORGOT_PASSWORD_PATH = "/forgotpassword";
	public static final String HTTP_FORGOT_PASSWORD = HTTP_CONTEXT_ANONYMOUS.concat(HTTP_FORGOT_PASSWORD_PATH);
	public static final String HTTP_FORGOT_PASSWORD_VERIFY = HTTP_CONTEXT_ANONYMOUS.concat("/forgotpassword").concat(HTTP_VERIFY);
	
	public static final String HTTP_LOGIN_MENU_SOAP = HTTP_CONTEXT_SOAP.concat("/loginMenuService");

	public static final String HTTP_CALLBACK_FACEBOOK = HTTP_CONTEXT_API.concat(HTTP_SECRET).concat("/facebook");
	
	public static final String HTTP_SIGNUP_FACEBOOK = HTTP_CONTEXT_REST.concat(HTTP_SIGNUP).concat("/facebook");	
	public static final String HTTP_CONNECTION = HTTP_CONTEXT_REST.concat("/connection");
	public static final String HTTP_LOGIN_MENU = HTTP_CONTEXT_REST.concat("/login-menu");
	public static final String HTTP_LOGOUT = HTTP_CONTEXT_REST.concat("/logout");
	public static final String HTTP_REPORT_SELECTED = HTTP_CONTEXT_REST.concat("/report").concat(HTTP_SELECT);
	public static final String HTTP_USER = HTTP_CONTEXT_REST.concat("/user").concat(HTTP_LOAD);
	public static final String HTTP_USER_UPDATE = HTTP_CONTEXT_REST.concat("/user").concat(HTTP_UPDATE);
	public static final String HTTP_PROFILE = HTTP_CONTEXT_REST.concat("/profile").concat(HTTP_LOAD);
	public static final String HTTP_PROFILE_EMPLOYEE = HTTP_CONTEXT_REST.concat("/profile/employee").concat(HTTP_LOAD);
	public static final String HTTP_PROFILE_UPDATE = HTTP_CONTEXT_REST.concat("/profile").concat(HTTP_UPDATE);
	public static final String HTTP_PROFILE_IMAGE = HTTP_CONTEXT_REST.concat("/profile").concat(HTTP_IMAGE);
	public static final String HTTP_AREA_ALL = HTTP_CONTEXT_REST.concat("/area").concat(HTTP_LOAD).concat(HTTP_ALL);
	public static final String HTTP_AREA_ALL_PAGE = HTTP_CONTEXT_REST.concat("/area").concat(HTTP_LOAD).concat(HTTP_PAGE);
	public static final String HTTP_AREA_SAVE = HTTP_CONTEXT_REST.concat("/area").concat(HTTP_SAVE);
	public static final String HTTP_AREA_UPDATE = HTTP_CONTEXT_REST.concat("/area").concat(HTTP_UPDATE);
	public static final String HTTP_AREA_DELETE = HTTP_CONTEXT_REST.concat("/area").concat(HTTP_DELETE);
	public static final String HTTP_EMPLOYEE_ALL = HTTP_CONTEXT_REST.concat("/employee").concat(HTTP_LOAD).concat(HTTP_ALL);
	public static final String HTTP_EMPLOYEE_SAVE = HTTP_CONTEXT_REST.concat("/employee").concat(HTTP_SAVE);
	public static final String HTTP_EMPLOYEE_UPDATE = HTTP_CONTEXT_REST.concat("/employee").concat(HTTP_UPDATE);
	public static final String HTTP_EMPLOYEE_ALL_PAGE = HTTP_CONTEXT_REST.concat("/employee").concat(HTTP_LOAD).concat(HTTP_PAGE);
	public static final String HTTP_OCCUPATION_ALL = HTTP_CONTEXT_REST.concat("/occupation").concat(HTTP_LOAD).concat(HTTP_ALL);
	public static final String HTTP_OCCUPATION_SAVE = HTTP_CONTEXT_REST.concat("/occupation").concat(HTTP_SAVE);
	public static final String HTTP_OCCUPATION_UPDATE = HTTP_CONTEXT_REST.concat("/occupation").concat(HTTP_UPDATE);
	public static final String HTTP_OCCUPATION_DELETE = HTTP_CONTEXT_REST.concat("/occupation").concat(HTTP_DELETE);
	public static final String HTTP_OCCUPATION_ALL_PAGE = HTTP_CONTEXT_REST.concat("/occupation").concat(HTTP_LOAD).concat(HTTP_PAGE);	
	
}
