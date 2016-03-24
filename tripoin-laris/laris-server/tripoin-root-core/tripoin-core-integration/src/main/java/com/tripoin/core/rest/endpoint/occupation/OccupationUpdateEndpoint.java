package com.tripoin.core.rest.endpoint.occupation;

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
import com.tripoin.core.dto.GeneralTransferObject;
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

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/occupation/update</code><br>
	 * @param inMessage
	 * @return
	 */
    @Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<GeneralTransferObject> updateOccupation(Message<GeneralTransferObject> inMessage) {
    	GeneralTransferObject generalTransferObject = new GeneralTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		authentication = null;
        try {
        	GeneralTransferObject datasTransmit = inMessage.getPayload();
        	if(datasTransmit.getParameterData() != null && !datasTransmit.getParameterData().isEmpty()){
            	Occupation occupation = new Occupation();
            	occupation.setName((String)datasTransmit.getParameterData().get(EnumFieldOccupation.NAME_OCCUPATION.toString()));
            	occupation.setCode((String)datasTransmit.getParameterData().get(EnumFieldOccupation.CODE_OCCUPATION.toString()));
            	occupation.setRemarks((String)datasTransmit.getParameterData().get(EnumFieldOccupation.DESCRIPTION_OCCUPATION.toString()));
            	occupation.setModifiedIP((String)datasTransmit.getParameterData().get(ParameterConstant.IDENTIFIER_IP));
            	occupation.setModifiedTime(ParameterConstant.FORMAT_DEFAULT.parse((String)datasTransmit.getParameterData().get(ParameterConstant.IDENTIFIER_TIME)));
            	occupation.setModifiedPlatform((String)datasTransmit.getParameterData().get(ParameterConstant.IDENTIFIER_PLATFORM));
            	occupation.setModifiedBy(currentUserName);
	        	if(occupation.getModifiedIP() == null)
	        		occupation.setModifiedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
	        	if(occupation.getModifiedTime() == null)
	        		occupation.setModifiedTime(new Date());
	        	if(occupation.getModifiedPlatform() == null)
	        		occupation.setModifiedPlatform(ParameterConstant.PLATFORM_DEFAULT);
	        	ValueArgument[] valueArguments = new ValueArgument[]{
                		new ValueArgument("name", occupation.getName()),
                		new ValueArgument("remarks", occupation.getRemarks()),
                		new ValueArgument("modifiedBy", occupation.getModifiedBy()),
                		new ValueArgument("modifiedIP", occupation.getModifiedIP()),
                		new ValueArgument("modifiedTime", occupation.getModifiedTime()),
                		new ValueArgument("modifiedPlatform", occupation.getModifiedPlatform()),
                		new ValueArgument("code", occupation.getCode()),
            	};
	        	iGenericManagerJpa.execQueryNotCriteria("UPDATE mst_occupation SET occupation_name = :name, remarks = :remarks, "
        				+ "modified_by = :modifiedBy, modified_ip = :modifiedIP, modified_time = :modifiedTime, "
        				+ "modified_platform = :modifiedPlatform WHERE occupation_code = :code", valueArguments);    		
	            generalTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
	            generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
	            generalTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
	            occupation = null;
                valueArguments = null;
	            datasTransmit = null;
        	}
        } catch (Exception e) {
            LOGGER.error("Update Employee System Error : " + e.getMessage(), e);
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
