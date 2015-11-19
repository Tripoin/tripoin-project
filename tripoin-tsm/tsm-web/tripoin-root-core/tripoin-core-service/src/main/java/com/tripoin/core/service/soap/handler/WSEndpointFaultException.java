package com.tripoin.core.service.soap.handler;

import javax.xml.ws.WebFault;

import com.tripoin.core.dto.exception.WSEndpointFault;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@WebFault(name = "wsEndpointFaultException")
public class WSEndpointFaultException extends Exception {

	private static final long serialVersionUID = -9109658078538437386L;
	
	private WSEndpointFault faultInfo;

    public WSEndpointFaultException(String message, WSEndpointFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public WSEndpointFaultException(String message, Throwable cause, WSEndpointFault faultInfo) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public WSEndpointFault getFaultInfo() {
        return faultInfo;
    }
    
}
