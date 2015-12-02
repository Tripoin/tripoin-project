package com.tripoin.core.rest.endpoint.occupation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@Component("occupationUpdateEndpoint")
public class OccupationUpdateEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(OccupationUpdateEndpoint.class);

    @Autowired
    private IGenericManagerJpa iGenericManagerJpa;
    
	private String currentUserName;

    @Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<OccupationTransferObject> updateOccupation(Message<OccupationData> inMessage) {
    	OccupationTransferObject occupationTransferObject = new OccupationTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}

        try {
        	OccupationData occupationData = inMessage.getPayload();
        	if(occupationData != null && occupationData.getId() == null){
                FilterArgument[] filterArguments = new FilterArgument[] { 
        				new FilterArgument("code", ECommonOperator.EQUALS) 
        		};
        		List<Occupation> occupationList = iGenericManagerJpa.loadObjectsFilterArgument(Occupation.class, filterArguments, new Object[] { occupationData.getCode() }, null, null);    		
        		occupationData.setId(occupationList.get(0).getId());        		
        	}
        	Occupation occupation = new Occupation(occupationData);
        	occupation.setModifiedBy(currentUserName);
        	occupation.setModifiedTime(new Date());
        	if(occupation.getModifiedIP() == null)
        		occupation.setModifiedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
        	if(occupation.getModifiedPlatform() == null)
        		occupation.setModifiedPlatform(ParameterConstant.PLATFORM_DEFAULT);    		
    		iGenericManagerJpa.updateObject(occupation);
    		
            List<OccupationData> occupationDatas = new ArrayList<OccupationData>();
            occupationDatas.add(new OccupationData(occupation));
            occupationTransferObject.setOccupationDatas(occupationDatas);
            occupationTransferObject.setResponseCode("0");
            occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
            occupationTransferObject.setResponseDesc("Update Employee Data Success");
        } catch (Exception e) {
            LOGGER.error("Update Employee System Error : " + e.getMessage(), e);
            occupationTransferObject.setResponseCode("1");
            occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            occupationTransferObject.setResponseDesc("Update Employee System Error : " + e.getMessage());
        }

        setReturnStatusAndMessage(occupationTransferObject, responseHeaderMap);
        Message<OccupationTransferObject> message = new GenericMessage<OccupationTransferObject>(occupationTransferObject, responseHeaderMap);
        return message;
    }

}
