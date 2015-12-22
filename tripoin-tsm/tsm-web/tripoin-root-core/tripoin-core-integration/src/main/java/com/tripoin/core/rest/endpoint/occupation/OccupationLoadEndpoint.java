package com.tripoin.core.rest.endpoint.occupation;

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
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dao.filter.PageArgument;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.core.pojo.Occupation;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("occupationLoadEndpoint")
public class OccupationLoadEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(OccupationLoadEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	private Integer minRow = 0;
	private Integer maxRow = 1;

	@Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<OccupationTransferObject> loadOccupation(Message<OccupationData> inMessage){	
		OccupationTransferObject occupationTransferObject = new OccupationTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		
		try{
        	OccupationData occupationDataPayload = inMessage.getPayload();
        	FilterArgument[] filterArguments = new FilterArgument[] { 
    				new FilterArgument("code", ECommonOperator.EQUALS) 
    		};
    		List<Occupation> occupationList = iGenericManagerJpa.loadObjectsFilterArgument(Occupation.class, filterArguments, new Object[] { occupationDataPayload.getCode() }, null, null);
			List<OccupationData> occupationDatas = new ArrayList<OccupationData>();
			if(occupationList != null){
				for(Occupation occupation : occupationList)
					occupationDatas.add(new OccupationData(occupation));
				occupationTransferObject.setOccupationDatas(occupationDatas);
			}
			occupationTransferObject.setResponseCode("0");
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			occupationTransferObject.setResponseDesc("Load Occupation Data Success");			
		}catch (Exception e){
			LOGGER.error("Load All Occupation System Error : "+e.getLocalizedMessage(), e);
			occupationTransferObject.setResponseCode("1");
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			occupationTransferObject.setResponseDesc("Load Occupation System Error : "+e.getLocalizedMessage());
		}
		
		setReturnStatusAndMessage(occupationTransferObject, responseHeaderMap);
		Message<OccupationTransferObject> message = new GenericMessage<OccupationTransferObject>(occupationTransferObject, responseHeaderMap);
		return message;		
	}

	@Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<OccupationTransferObject> loadAllOccupations(Message<?> inMessage){	
		OccupationTransferObject occupationTransferObject = new OccupationTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		
		try{
			List<Occupation> occupationList = iGenericManagerJpa.loadObjects(Occupation.class);
			List<OccupationData> occupationDatas = new ArrayList<OccupationData>();
			if(occupationList != null){
				for(Occupation occupation : occupationList)
					occupationDatas.add(new OccupationData(occupation));
				occupationTransferObject.setOccupationDatas(occupationDatas);
			}
			occupationTransferObject.setResponseCode("0");
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			occupationTransferObject.setResponseDesc("Load All Occupation Data Success");			
		}catch (Exception e){
			LOGGER.error("Load All Occupation System Error : "+e.getLocalizedMessage(), e);
			occupationTransferObject.setResponseCode("1");
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			occupationTransferObject.setResponseDesc("Load All Occupation System Error : "+e.getLocalizedMessage());
		}
		
		setReturnStatusAndMessage(occupationTransferObject, responseHeaderMap);
		Message<OccupationTransferObject> message = new GenericMessage<OccupationTransferObject>(occupationTransferObject, responseHeaderMap);
		return message;		
	}

	@Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<OccupationTransferObject> loadOccupationPaging(Message<?> inMessage){	
		OccupationTransferObject occupationTransferObject = new OccupationTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		
		try{			
			if(inMessage.getPayload() != null){
				String[] params = ((String)inMessage.getPayload()).split("&");
				try {
					minRow = Integer.parseInt(params[0].replaceAll(ParameterConstant.PAGING_MIN_ROW, ""));
					maxRow = Integer.parseInt(params[1].replaceAll(ParameterConstant.PAGING_MAX_ROW, ""));					
				} catch (Exception e) {
					LOGGER.error("Load Paging Occupation System Error : "+e.getLocalizedMessage(), e);
					minRow = 0;
					maxRow = 1;
				}
			}
			List<Occupation> occupationList = iGenericManagerJpa.loadObjectsFilterArgument(Occupation.class, null, null, null, new PageArgument(minRow, maxRow));
			List<OccupationData> occupationDatas = new ArrayList<OccupationData>();
			if(occupationList != null){
				for(Occupation occupation : occupationList)
					occupationDatas.add(new OccupationData(occupation));
				occupationTransferObject.setOccupationDatas(occupationDatas);
			}
			occupationTransferObject.setResponseCode("0");
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			occupationTransferObject.setResponseDesc("Load Paging Occupation Data Success");			
		}catch (Exception e){
			LOGGER.error("Load Paging Occupation System Error : "+e.getLocalizedMessage(), e);
			occupationTransferObject.setResponseCode("1");
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			occupationTransferObject.setResponseDesc("Load Paging Occupation System Error : "+e.getLocalizedMessage());
		}
		
		setReturnStatusAndMessage(occupationTransferObject, responseHeaderMap);
		Message<OccupationTransferObject> message = new GenericMessage<OccupationTransferObject>(occupationTransferObject, responseHeaderMap);
		return message;		
	}
	
}
