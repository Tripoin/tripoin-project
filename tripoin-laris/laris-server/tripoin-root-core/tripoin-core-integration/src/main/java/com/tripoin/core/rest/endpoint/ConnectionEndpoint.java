package com.tripoin.core.rest.endpoint;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.dto.app.GeneralTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("connectionEndpoint")
public class ConnectionEndpoint extends XReturnStatus {
	
	@Secured({RoleConstant.ROLE_BUYER, RoleConstant.ROLE_SELLER, RoleConstant.ROLE_GATEWAY, RoleConstant.ROLE_ADMIN})
	public Message<GeneralTransferObject> getConnection(Message<?> inMessage){	
		GeneralTransferObject connect = new GeneralTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{			
			connect.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
			connect.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			connect.setResponseDesc(EResponseCode.RC_SUCCESS.toString());			
		}catch (Exception e){
			connect.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			connect.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			connect.setResponseDesc(EResponseCode.RC_FAILURE.toString()+e.getMessage());
		}
		
		setReturnStatusAndMessage(connect, responseHeaderMap);
		Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(connect, responseHeaderMap);
		return message;		
	}
	
}
