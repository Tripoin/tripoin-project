package com.tripoin.core.rest.endpoint.area;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.AreaTransferObject;
import com.tripoin.core.dto.AreaTransferObject.EnumFieldArea;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.pojo.Area;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.IVersionControlSystemTableService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("areaSaveEndpoint")
public class AreaSaveEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(AreaSaveEndpoint.class);

    @Autowired
    private IGenericManagerJpa iGenericManagerJpa;
    
    @Autowired
    private IVersionControlSystemTableService versionControlSystemTableService;
    
	@Autowired
	@Qualifier(value="transactionManager")
	private PlatformTransactionManager transactionManager ;
	
	@Autowired
	@Qualifier(value="web-async-task-executor")
	private ThreadPoolTaskExecutor taskExecutor;
    
	private String currentUserName;
	
    @Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<GeneralTransferObject> saveArea(Message<AreaTransferObject> inMessage) {
    	GeneralTransferObject generalTransferObject = new GeneralTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}

        try {
        	AreaTransferObject datasTransmit = inMessage.getPayload();
        	Area area = new Area();
        	FilterArgument[] filterArgumentsCheck = new FilterArgument[] { 
    				new FilterArgument(EnumFieldArea.CODE_AREA.toString(), ECommonOperator.EQUALS) 
    		};
        	area.setName((String)datasTransmit.getFindAreaData().get(EnumFieldArea.NAME_AREA.toString()));
        	area.setCode(area.getName().replace(" ", "").toUpperCase());
        	area.setRemarks((String)datasTransmit.getFindAreaData().get(EnumFieldArea.DESCRIPTION_AREA.toString()));
    		List<Area> areaListCheck = iGenericManagerJpa.loadObjectsFilterArgument(Area.class, filterArgumentsCheck, new Object[] { area.getCode() }, null, null);
    		if(areaListCheck == null || areaListCheck.size() == 0){
                area.setCreatedBy(currentUserName);
            	if(area.getCreatedIP() == null)
            		area.setCreatedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
            	if(area.getCreatedTime() == null)
                    area.setCreatedTime(new Date());
            	if(area.getCreatedPlatform() == null)
            		area.setCreatedPlatform(ParameterConstant.PLATFORM_DEFAULT);
                iGenericManagerJpa.saveObjectAndSync(area);                    
                taskExecutor.execute(new Runnable() {			
        			@Override
        			public void run() {
        				final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        				transactionTemplate.execute(new TransactionCallback<Object>() {
        					@Override
        					public Object doInTransaction(TransactionStatus arg0) {
        						try {
        							versionControlSystemTableService.insertValueAndSync(Area.TABLE_NAME, new Long(1), "Table of ".concat(Area.TABLE_NAME));
        						} catch (Exception e) {
        							LOGGER.error("Save Area System Error : " + e.getLocalizedMessage(), e);
        						}
        						return null;
        					}
        				});
        			}
        		});            
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
