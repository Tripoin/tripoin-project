package com.tripoin.core.rest.endpoint.area;

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
import com.tripoin.core.dto.AreaData;
import com.tripoin.core.pojo.Area;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("areaSaveEndpoint")
public class AreaSaveEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(AreaSaveEndpoint.class);

    @Autowired
    private IGenericManagerJpa iGenericManagerJpa;
    
	private String currentUserName;
	
    @Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<GeneralTransferObject> saveArea(Message<AreaData> inMessage) {
    	GeneralTransferObject generalTransferObject = new GeneralTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}

        try {
        	Area area = new Area(inMessage.getPayload());
        	LOGGER.debug("Area : "+area.toString());
        	FilterArgument[] filterArgumentsCheck = new FilterArgument[] { 
    				new FilterArgument("code", ECommonOperator.EQUALS) 
    		};
    		List<Area> areaListCheck = iGenericManagerJpa.loadObjectsFilterArgument(Area.class, filterArgumentsCheck, new Object[] { area.getCode() }, null, null);
    		if(areaListCheck == null || areaListCheck.size() == 0){
                area.setCode(area.getName().replace(" ", "").toUpperCase());
                area.setCreatedBy(currentUserName);
                area.setCreatedTime(new Date());
            	if(area.getCreatedIP() == null)
            		area.setCreatedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
            	if(area.getCreatedPlatform() == null)
            		area.setCreatedPlatform(ParameterConstant.PLATFORM_DEFAULT);
                iGenericManagerJpa.saveObjectAndSync(area);                
                generalTransferObject.setResponseCode("0");
                generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
                generalTransferObject.setResponseDesc("Save Area Data Success");    			
    		}else{                
                generalTransferObject.setResponseCode("2");
                generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
                generalTransferObject.setResponseDesc("Save Area Data Failure Duplicate");      			
    		}
        } catch (Exception e) {
            LOGGER.error("Save Area System Error : " + e.getLocalizedMessage(), e);
            generalTransferObject.setResponseCode("1");
            generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            generalTransferObject.setResponseDesc("Save Area System Error : " + e.getLocalizedMessage());
        }

        setReturnStatusAndMessage(generalTransferObject, responseHeaderMap);
        Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(generalTransferObject, responseHeaderMap);
        return message;
    }

}
