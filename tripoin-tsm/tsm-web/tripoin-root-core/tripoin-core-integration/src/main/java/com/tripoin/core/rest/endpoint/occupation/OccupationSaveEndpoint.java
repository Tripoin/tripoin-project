package com.tripoin.core.rest.endpoint.occupation;

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
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.core.dto.OccupationTransferObject.EnumFieldOccupation;
import com.tripoin.core.pojo.Occupation;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.IVersionControlSystemTableService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("occupationSaveEndpoint")
public class OccupationSaveEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(OccupationSaveEndpoint.class);

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
    public Message<GeneralTransferObject> saveOccupation(Message<OccupationTransferObject> inMessage) {
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
                occupation.setName((String)datasTransmit.getFindOccupationData().get(EnumFieldOccupation.NAME_OCCUPATION.toString()));
                occupation.setCode("ROLE_".concat(occupation.getName().replace(" ", "").toUpperCase()));
                occupation.setRemarks((String)datasTransmit.getFindOccupationData().get(EnumFieldOccupation.DESCRIPTION_OCCUPATION.toString()));
        		List<Occupation> occupationListCheck = iGenericManagerJpa.loadObjectsFilterArgument(Occupation.class, filterArguments, new Object[] { occupation.getCode() }, null, null);
	    		if(occupationListCheck == null || occupationListCheck.size() == 0){
	    			occupation.setCreatedBy(currentUserName);
	            	if(occupation.getCreatedIP() == null)
	            		occupation.setCreatedIP(ParameterConstant.IP_ADDRESSV4_DEFAULT);
	            	if(occupation.getCreatedTime() == null)
		                occupation.setCreatedTime(new Date());
		            if(occupation.getCreatedPlatform() == null)
	            		occupation.setCreatedPlatform(ParameterConstant.PLATFORM_DEFAULT);
	                iGenericManagerJpa.saveObjectAndSync(occupation);                
	                taskExecutor.execute(new Runnable() {			
	        			@Override
	        			public void run() {
	        				final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
	        				transactionTemplate.execute(new TransactionCallback<Object>() {
	        					@Override
	        					public Object doInTransaction(TransactionStatus arg0) {
	        						try {
	        							versionControlSystemTableService.insertValueAndSync(Occupation.TABLE_NAME, new Long(1), "Table of ".concat(Occupation.TABLE_NAME));
	        						} catch (Exception e) {
	        							LOGGER.error("Save Occupation System Error : " + e.getLocalizedMessage(), e);
	        						}
	        						return null;
	        					}
	        				});
	        			}
	        		});
	                generalTransferObject.setResponseCode("0");
	                generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
	                generalTransferObject.setResponseDesc("Save Occupation Data Success");    			
	    		}else{                
	                generalTransferObject.setResponseCode("2");
	                generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
	                generalTransferObject.setResponseDesc("Save Occupation Data Failure Duplicate");      			
	    		}
        	}else
        		throw new Exception();
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
