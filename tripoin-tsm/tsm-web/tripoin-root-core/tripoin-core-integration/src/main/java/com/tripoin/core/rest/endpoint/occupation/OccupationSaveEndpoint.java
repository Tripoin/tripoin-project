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
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.pojo.Occupation;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("occupationSaveEndpoint")
public class OccupationSaveEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(OccupationSaveEndpoint.class);

    @Autowired
    private IGenericManagerJpa iGenericManagerJpa;
    
	private String currentUserName;
	
    @Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<GeneralTransferObject> saveOccupation(Message<OccupationData> inMessage) {
    	GeneralTransferObject generalTransferObject = new GeneralTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}

        try {
        	Occupation occupation = new Occupation(inMessage.getPayload());
        	LOGGER.debug("Occupation : "+occupation.toString());
        	FilterArgument[] filterArgumentsCheck = new FilterArgument[] { 
    				new FilterArgument("code", ECommonOperator.EQUALS) 
    		};
    		List<Occupation> occupationListCheck = iGenericManagerJpa.loadObjectsFilterArgument(Occupation.class, filterArgumentsCheck, new Object[] { occupation.getCode() }, null, null);
    		if(occupationListCheck == null || occupationListCheck.size() == 0){
                occupation.setCode(occupation.getName().replace(" ", "").toUpperCase());
                occupation.setCreatedBy(currentUserName);
                occupation.setCreatedTime(new Date());
            	if(occupation.getCreatedIP() == null)
            		occupation.setCreatedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
            	if(occupation.getCreatedPlatform() == null)
            		occupation.setCreatedPlatform(ParameterConstant.PLATFORM_DEFAULT);
                iGenericManagerJpa.saveObjectAndSync(occupation);                
                generalTransferObject.setResponseCode("0");
                generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
                generalTransferObject.setResponseDesc("Save Occupation Data Success");    			
    		}else{                
                generalTransferObject.setResponseCode("2");
                generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
                generalTransferObject.setResponseDesc("Save Occupation Data Failure Duplicate");      			
    		}
        } catch (Exception e) {
            LOGGER.error("Save Occupation System Error : " + e.getLocalizedMessage(), e);
            generalTransferObject.setResponseCode("1");
            generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            generalTransferObject.setResponseDesc("Save Occupation System Error : " + e.getLocalizedMessage());
        }

        setReturnStatusAndMessage(generalTransferObject, responseHeaderMap);
        Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(generalTransferObject, responseHeaderMap);
        return message;
    }

}
