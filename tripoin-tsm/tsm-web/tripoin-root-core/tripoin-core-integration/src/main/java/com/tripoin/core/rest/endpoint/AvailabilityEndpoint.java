package com.tripoin.core.rest.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.AvailabilityData;
import com.tripoin.core.dto.AvailabilityTransferObject;
import com.tripoin.core.pojo.Availability;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("availabilityEndpoint")
public class AvailabilityEndpoint {

    private static Logger LOGGER = LoggerFactory.getLogger(AvailabilityEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	@Secured({RoleConstant.ROLE_SUPERADMIN, RoleConstant.ROLE_ADMIN, RoleConstant.ROLE_USER})
	public Message<AvailabilityTransferObject> getAllAvailability(Message<?> inMessage){	
		AvailabilityTransferObject availabilityTransferObject = new AvailabilityTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{
			List<Availability> availabilityList = iGenericManagerJpa.loadObjects(Availability.class);
			List<AvailabilityData> availabilityDatas = new ArrayList<AvailabilityData>();
			if(availabilityList != null){
				for(Availability availability : availabilityList){
					AvailabilityData availabilityData = new AvailabilityData(availability);
					availabilityDatas.add(availabilityData);
				}
				availabilityTransferObject.setAvailabilityDatas(availabilityDatas);
			}
			availabilityTransferObject.setResponseCode("0");
			availabilityTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			availabilityTransferObject.setResponseDesc("Load Availability Data Success");			
		}catch (Exception e){
			LOGGER.error("Availability System Error : "+e.getLocalizedMessage(), e);
			availabilityTransferObject.setResponseCode("1");
			availabilityTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			availabilityTransferObject.setResponseDesc("Availability System Error : "+e.getLocalizedMessage());
		}
		
		setReturnStatusAndMessage(availabilityTransferObject, responseHeaderMap);
		Message<AvailabilityTransferObject> message = new GenericMessage<AvailabilityTransferObject>(availabilityTransferObject, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(AvailabilityTransferObject availabilityTransferObject, Map<String, Object> responseHeaderMap){		
		responseHeaderMap.put("Return-Status", availabilityTransferObject.getResponseCode());
		responseHeaderMap.put("Return-Status-Msg", availabilityTransferObject.getResponseDesc());
	}
	
}
