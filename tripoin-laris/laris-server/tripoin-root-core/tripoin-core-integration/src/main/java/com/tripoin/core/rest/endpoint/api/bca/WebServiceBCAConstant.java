package com.tripoin.core.rest.endpoint.api.bca;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class WebServiceBCAConstant {

	private static final String HTTP_API = "/api";
	private static final String HTTP_EWALLET = "/ewallet";
	
	private static final String HTTP_OAUTH = "/oauth";
	private static final String HTTP_CUSTOMER_BASE = "/customers";
	private static final String HTTP_PAYMENT_BASE = "/payments";
	private static final String HTTP_TOPUP_BASE = "/topup";
	private static final String HTTP_TRANSACTION_BASE = "/transactions";

	public static final String HTTP_OAUTH_TOKEN = HTTP_API.concat(HTTP_OAUTH).concat("/token");	
	
	public static final String HTTP_USER_REGISTRATION = HTTP_EWALLET.concat(HTTP_CUSTOMER_BASE);	
	public static final String HTTP_USER_INQUIRY = HTTP_EWALLET.concat(HTTP_CUSTOMER_BASE);	
	public static final String HTTP_USER_UPDATE = HTTP_EWALLET.concat(HTTP_CUSTOMER_BASE);

	public static final String HTTP_PAYMENT = HTTP_EWALLET.concat(HTTP_PAYMENT_BASE);
	public static final String HTTP_PAYMENT_STATUS = HTTP_EWALLET.concat(HTTP_PAYMENT_BASE);

	public static final String HTTP_TOPUP = HTTP_EWALLET.concat(HTTP_TOPUP_BASE);

	public static final String HTTP_TRANSACTION_INQUIRY = HTTP_EWALLET.concat(HTTP_TRANSACTION_BASE);
	
}
