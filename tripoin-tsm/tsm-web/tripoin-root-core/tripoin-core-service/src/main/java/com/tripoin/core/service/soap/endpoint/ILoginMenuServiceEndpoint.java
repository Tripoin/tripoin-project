package com.tripoin.core.service.soap.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.tripoin.core.dto.UserMenuTransferObject;
import com.tripoin.core.service.soap.handler.WSEndpointFaultException;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@WebService
public interface ILoginMenuServiceEndpoint {

	/**
	 * <b>Sample XML:</b><br>
	 * <code></code><br>
	 * @param viewType
	 * @return
	 * @throws WSEndpointFaultException
	 */
	@WebMethod(operationName="GetUserMenu")
	public UserMenuTransferObject getUserMenu(
			@WebParam(name = "ViewType")String viewType) throws WSEndpointFaultException;
	
}
