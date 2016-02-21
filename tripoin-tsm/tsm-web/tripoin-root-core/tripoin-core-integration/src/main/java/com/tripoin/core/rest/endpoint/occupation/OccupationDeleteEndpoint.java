package com.tripoin.core.rest.endpoint.occupation;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.core.pojo.Employee;
import com.tripoin.core.pojo.Occupation;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.IVersionControlSystemTableService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("occupationDeleteEndpoint")
public class OccupationDeleteEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(OccupationDeleteEndpoint.class);

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

    @Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<OccupationTransferObject> deleteOccupation(Message<OccupationTransferObject> inMessage) {
    	OccupationTransferObject occupationTransferObject = new OccupationTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

        try {
        	FilterArgument[] filterArguments = new FilterArgument[] { 
    				new FilterArgument("code", ECommonOperator.EQUALS) 
    		};
        	Long countOccupation = new Long(0);
        	for(String code : inMessage.getPayload().getFindOccupationData().keySet()){
        		countOccupation  = iGenericManagerJpa.totalRowData(Employee.class, filterArguments, new Object[] { code }, null, null);
        		if(countOccupation > 0)
        			break;
        	}	
        	if(countOccupation > 0){
                occupationTransferObject.setResponseCode("2");
                occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
                occupationTransferObject.setResponseDesc("Delete Occupation Data Failure, Some Occupation Data already being used");        		
        	}else{
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
        							LOGGER.error("Delete Occupation System Error : " + e.getLocalizedMessage(), e);
        						}
        						return null;
        					}
        				});
        			}
        		});
                occupationTransferObject.setResponseCode("0");
                occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
                occupationTransferObject.setResponseDesc("Delete Occupation Data Success");	
        	}
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
