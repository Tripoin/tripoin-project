package com.tripoin.web.common.impl;

import com.tripoin.web.common.ICommonRest;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class CommonRestImpl implements ICommonRest {
	
	private String protocolHTTP;	
	private String hostHTTP;
	private String portHTTP;
	private String homeContextHTTP;	
	private String hostNameHTTP;
	
	public void setProtocolHTTP(String protocolHTTP) {this.protocolHTTP = protocolHTTP;}
	public void setHostHTTP(String hostHTTP) {this.hostHTTP = hostHTTP;}
	public void setPortHTTP(String portHTTP) {this.portHTTP = portHTTP;}
	public void setHomeContextHTTP(String homeContextHTTP) {this.homeContextHTTP = homeContextHTTP;}
	public void setHostNameHTTP(String hostNameHTTP) {this.hostNameHTTP = hostNameHTTP;}
	
	public String getUrl(String paramContext) {
		return protocolHTTP.concat(hostHTTP).concat(portHTTP).concat(homeContextHTTP).concat(paramContext);
	}
	
	public String getUrlHostName(String paramContext) {
		return protocolHTTP.concat(hostNameHTTP).concat(portHTTP).concat(homeContextHTTP).concat(paramContext);
	}

}
