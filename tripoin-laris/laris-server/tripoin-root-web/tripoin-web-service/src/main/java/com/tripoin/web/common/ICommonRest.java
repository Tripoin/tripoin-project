package com.tripoin.web.common;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface ICommonRest {
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>getUrl(WebServiceConstant.HTTP_CONNECTION)</code><br>
	 * @param paramContext
	 * @return
	 */
	public String getUrl(String paramContext);
	
	/**
	 * <b>Description:</b><br>
	 * Development Mode : tripoin.integration.host.name=${IP Public / Computer Name, localhost, 127.0.0.1} <br>
	 * Production Mode : tripoin.integration.host.name=${IP Public / hostname DNS}<br><br>
	 * <b>Sample Code:</b><br>
	 * <code>getUrlHostName(WebServiceConstant.HTTP_CONNECTION)</code><br>
	 * @param paramContext
	 * @return
	 */
	public String getUrlHostName(String paramContext);

}
