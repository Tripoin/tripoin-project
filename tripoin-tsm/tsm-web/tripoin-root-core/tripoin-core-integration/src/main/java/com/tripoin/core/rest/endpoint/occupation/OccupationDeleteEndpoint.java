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
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.core.pojo.Occupation;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("occupationDeleteEndpoint")
public class OccupationDeleteEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(OccupationDeleteEndpoint.class);

    @Autowired
    private IGenericManagerJpa iGenericManagerJpa;

    @Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<OccupationTransferObject> deleteOccupation(Message<OccupationData> inMessage) {
    	OccupationTransferObject occupationTransferObject = new OccupationTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

        try {
        	Occupation occupation = new Occupation(inMessage.getPayload());
        	if(occupation != null && occupation.getId() == null){
            	FilterArgument[] filterArguments = new FilterArgument[] { 
        				new FilterArgument("code", ECommonOperator.EQUALS) 
        		};
        		List<Occupation> occupationList = iGenericManagerJpa.loadObjectsFilterArgument(Occupation.class, filterArguments, new Object[] { occupation.getCode() }, null, null);
        		occupation.setId(occupationList.get(0).getId());        		
        	}    		
            iGenericManagerJpa.deleteObject(occupation);
            
            List<Occupation> occupationList = iGenericManagerJpa.loadObjects(Occupation.class);
            List<OccupationData> occupationDatas = new ArrayList<OccupationData>();
            if (occupationList != null) {
                for (Occupation occupationTemp : occupationList)
                    occupationDatas.add(new OccupationData(occupationTemp));
                occupationTransferObject.setOccupationDatas(occupationDatas);
            }
            occupationTransferObject.setResponseCode("0");
            occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
            occupationTransferObject.setResponseDesc("Delete Occupation Data Success");
        } catch (Exception e) {
            LOGGER.error("Delete Occupation System Error : " + e.getLocalizedMessage(), e);
            occupationTransferObject.setResponseCode("1");
            occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            occupationTransferObject.setResponseDesc("Delete Occupation System Error : " + e.getLocalizedMessage());
        }

        setReturnStatusAndMessage(occupationTransferObject, responseHeaderMap);
        Message<OccupationTransferObject> message = new GenericMessage<OccupationTransferObject>(occupationTransferObject, responseHeaderMap);
        return message;
    }

}
