package com.tripoin.core.rest.endpoint.area;

import java.util.Date;
import java.util.HashMap;
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

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ValueArgument;
import com.tripoin.core.dto.AreaTransferObject;
import com.tripoin.core.dto.AreaTransferObject.EnumFieldArea;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.pojo.Area;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("areaUpdateEndpoint")
public class AreaUpdateEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(AreaUpdateEndpoint.class);

    @Autowired
    private IGenericManagerJpa iGenericManagerJpa;
    
	private String currentUserName;

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/area/update</code><br>
	 * @param inMessage
	 * @return
	 */
    @Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<GeneralTransferObject> updateArea(Message<AreaTransferObject> inMessage) {
    	GeneralTransferObject generalTransferObject = new GeneralTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		authentication = null;
        try {
        	AreaTransferObject datasTransmit = inMessage.getPayload();
        	if(datasTransmit != null && !datasTransmit.getFindAreaData().isEmpty()){
            	Area area = new Area();
        		area.setName((String)datasTransmit.getFindAreaData().get(EnumFieldArea.NAME_AREA.toString()));
        		area.setCode((String)datasTransmit.getFindAreaData().get(EnumFieldArea.CODE_AREA.toString()));
        		area.setRemarks((String)datasTransmit.getFindAreaData().get(EnumFieldArea.DESCRIPTION_AREA.toString()));
        		area.setModifiedIP((String)datasTransmit.getFindAreaData().get(ParameterConstant.IDENTIFIER_IP));
        		area.setModifiedTime(ParameterConstant.FORMAT_DEFAULT.parse((String)datasTransmit.getFindAreaData().get(ParameterConstant.IDENTIFIER_TIME)));
        		area.setModifiedPlatform((String)datasTransmit.getFindAreaData().get(ParameterConstant.IDENTIFIER_PLATFORM));
            	area.setModifiedBy(currentUserName);
            	if(area.getModifiedIP() == null)
            		area.setModifiedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
            	if(area.getModifiedTime() == null)
                	area.setModifiedTime(new Date());
            	if(area.getModifiedPlatform() == null)
            		area.setModifiedPlatform(ParameterConstant.PLATFORM_DEFAULT);
            	ValueArgument[] valueArguments = new ValueArgument[]{
                		new ValueArgument("name", area.getName()),
                		new ValueArgument("remarks", area.getRemarks()),
                		new ValueArgument("modifiedBy", area.getModifiedBy()),
                		new ValueArgument("modifiedIP", area.getModifiedIP()),
                		new ValueArgument("modifiedTime", area.getModifiedTime()),
                		new ValueArgument("modifiedPlatform", area.getModifiedPlatform()),
                		new ValueArgument("code", area.getCode()),
            	};
        		iGenericManagerJpa.execQueryNotCriteria("UPDATE mst_area SET area_name = :name, remarks = :remarks, "
        				+ "modified_by = :modifiedBy, modified_ip = :modifiedIP, modified_time = :modifiedTime, "
        				+ "modified_platform = :modifiedPlatform WHERE area_code = :code", valueArguments);    		
                generalTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
                generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
                generalTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
                area = null;
                valueArguments = null;
        	}
        } catch (Exception e) {
            LOGGER.error("Update Area System Error : " + e.getMessage(), e);
            generalTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
            generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            generalTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString() + e.getMessage());
        }
        setReturnStatusAndMessage(generalTransferObject, responseHeaderMap);
        Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(generalTransferObject, responseHeaderMap);
        generalTransferObject = null;
        this.currentUserName = null;
        return message;
    }

}
