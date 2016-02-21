package com.tripoin.core.rest.endpoint.occupation;

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
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.core.dto.OccupationTransferObject.EnumFieldOccupation;
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

    @Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<GeneralTransferObject> updateOccupation(Message<OccupationTransferObject> inMessage) {
    	GeneralTransferObject generalTransferObject = new GeneralTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
        try {
        	OccupationTransferObject datasTransmit = inMessage.getPayload();
        	Occupation occupation = new Occupation();
        	if(datasTransmit.getFindOccupationData() != null){
                FilterArgument[] filterArguments = new FilterArgument[] { 
        				new FilterArgument(EnumFieldOccupation.CODE_OCCUPATION.toString(), ECommonOperator.EQUALS) 
        		};
                List<Occupation> occupationList = iGenericManagerJpa.loadObjectsFilterArgument(Occupation.class, filterArguments, new Object[] { datasTransmit.getFindOccupationData().get(EnumFieldOccupation.CODE_OCCUPATION.toString()) }, null, null);    		
        		occupation = occupationList.get(0);
        		occupation.setName((String)datasTransmit.getFindOccupationData().get(EnumFieldOccupation.NAME_OCCUPATION.toString()));
        		occupation.setRemarks((String)datasTransmit.getFindOccupationData().get(EnumFieldOccupation.DESCRIPTION_OCCUPATION.toString()));
        		occupation.setModifiedIP((String)datasTransmit.getFindOccupationData().get(ParameterConstant.IDENTIFIER_IP));
        		occupation.setModifiedTime(ParameterConstant.FORMAT_DEFAULT.parse((String)datasTransmit.getFindOccupationData().get(ParameterConstant.IDENTIFIER_TIME)));
        		occupation.setModifiedPlatform((String)datasTransmit.getFindOccupationData().get(ParameterConstant.IDENTIFIER_PLATFORM));
        	}
        	occupation.setModifiedBy(currentUserName);
        	if(occupation.getModifiedIP() == null)
        		occupation.setModifiedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
        	if(occupation.getModifiedTime() == null)
        		occupation.setModifiedTime(new Date());
        	if(occupation.getModifiedPlatform() == null)
        		occupation.setModifiedPlatform(ParameterConstant.PLATFORM_DEFAULT);    		
    		iGenericManagerJpa.updateObject(occupation);    		
            generalTransferObject.setResponseCode("0");
            generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
            generalTransferObject.setResponseDesc("Update Employee Data Success");
        } catch (Exception e) {
            LOGGER.error("Update Employee System Error : " + e.getMessage(), e);
            generalTransferObject.setResponseCode("1");
            generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            generalTransferObject.setResponseDesc("Update Employee System Error : " + e.getMessage());
        }
        setReturnStatusAndMessage(generalTransferObject, responseHeaderMap);
        Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(generalTransferObject, responseHeaderMap);
        return message;
    }

}
