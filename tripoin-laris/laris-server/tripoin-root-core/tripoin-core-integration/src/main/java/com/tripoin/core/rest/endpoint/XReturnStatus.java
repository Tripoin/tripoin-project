package com.tripoin.core.rest.endpoint;

import java.util.Map;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class XReturnStatus {

	public void setReturnStatusAndMessage(com.tripoin.dto.app.GeneralTransferObject generalTransferObject, Map<String, Object> responseHeaderMap){		
		responseHeaderMap.put("Return-Status", generalTransferObject.getResponseCode());
		responseHeaderMap.put("Return-Status-Msg", generalTransferObject.getResponseDesc());
	}
	
}
