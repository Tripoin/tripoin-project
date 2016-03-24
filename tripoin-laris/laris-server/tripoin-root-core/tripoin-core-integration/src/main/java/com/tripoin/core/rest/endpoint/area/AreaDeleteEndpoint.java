package com.tripoin.core.rest.endpoint.area;

import java.math.BigInteger;
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

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.pojo.Area;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.IVersionControlSystemTableService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("areaDeleteEndpoint")
public class AreaDeleteEndpoint extends XReturnStatus {

	private static Logger LOGGER = LoggerFactory.getLogger(AreaDeleteEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	@Autowired
	private IVersionControlSystemTableService versionControlSystemTableService;

	@Autowired
	@Qualifier(value = "transactionManager")
	private PlatformTransactionManager transactionManager;

	@Autowired
	@Qualifier(value = "web-async-task-executor")
	private ThreadPoolTaskExecutor taskExecutor;

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/area/delete</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({ RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN })
	public Message<GeneralTransferObject> deleteArea(Message<GeneralTransferObject> inMessage) {
		GeneralTransferObject generalTransferObject = new GeneralTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

		try {
			GeneralTransferObject datasTransmit = inMessage.getPayload();
        	if(datasTransmit.getParameterData() != null && !datasTransmit.getParameterData().isEmpty()){
            	boolean isDeleted = false;
            	try {
            		Object[] values = new Object[datasTransmit.getParameterData().size()];
            		StringBuffer parameters = new StringBuffer("(");
            		int i = 0;
                	for(String code : datasTransmit.getParameterData().keySet()){
                		values[i] = code;
                		parameters.append("?,");
                		isDeleted = true;
                		i++;
                	}
                	Long countCheckedEmployee = ((BigInteger)iGenericManagerJpa.getObjectSQLNative("SELECT COUNT(em.employee_id) "
                			+ "FROM mst_employee em JOIN mst_area ar ON em.area_id = ar.area_id WHERE ar.area_code in ".concat(parameters.deleteCharAt(parameters.length()-1).append(")").toString()), values)).longValue();
                	if(countCheckedEmployee > 0){
                		isDeleted = false;
            			throw new Exception();
                	}                		
                	iGenericManagerJpa.execQueryNotCriteria("DELETE FROM mst_area WHERE area_code in ".concat(parameters.deleteCharAt(parameters.length()-1).append(")").toString()), values);
                	parameters = null;
                	values = null;
    			} catch (Exception e) {
    				generalTransferObject.setResponseCode(EResponseCode.RC_USED.getResponseCode());
    				generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
    				generalTransferObject.setResponseDesc(EResponseCode.RC_USED.toString());
    			}	
            	if(isDeleted){
    				taskExecutor.execute(new Runnable() {
    					@Override
    					public void run() {
    						final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
    						transactionTemplate.execute(new TransactionCallback<Object>() {
    							@Override
    							public Object doInTransaction(TransactionStatus arg0) {
    								try {
    									versionControlSystemTableService.insertValueAndSync(Area.TABLE_NAME, new Long(1),
    											"Table of ".concat(Area.TABLE_NAME));
    								} catch (Exception e) {
    									LOGGER.error("Delete Area System Error : " + e.getLocalizedMessage(), e);
    								}
    								return null;
    							}
    						});
    					}
    				});
    				generalTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
    				generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
    				generalTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
                	isDeleted = false;
    			}     		
        	}
		} catch (Exception e) {
			LOGGER.error("Delete Area System Error : " + e.getLocalizedMessage(), e);
			generalTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			generalTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString() + e.getLocalizedMessage());
		}
		setReturnStatusAndMessage(generalTransferObject, responseHeaderMap);
		Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(generalTransferObject, responseHeaderMap);
		generalTransferObject = null;
		return message;
	}

}
