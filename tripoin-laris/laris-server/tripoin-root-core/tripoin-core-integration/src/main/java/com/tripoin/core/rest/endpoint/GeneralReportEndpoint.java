package com.tripoin.core.rest.endpoint;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.GeneralReportTransferObject;
import com.tripoin.util.report.ReportGenerator;
import com.tripoin.util.report.common.EReportUtilConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("generalReportEndpoint")
public class GeneralReportEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(GeneralReportEndpoint.class);
    
    @Autowired
    private ReportGenerator reportGenerator;

    @Autowired
	@Qualifier(value="dataSourceReport")
    private DataSource dataSourceReport;

    @Autowired
	@Qualifier(value="transactionManagerReport")
	private PlatformTransactionManager transactionManagerReport ;
	
	private ByteArrayOutputStream outputStreamData;
	private GeneralReportTransferObject generalReportTransferObject;
	private Map<String, Object> params;
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/report/select</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<byte[]> exportStreamReport(Message<GeneralReportTransferObject> inMessage){
		outputStreamData = new ByteArrayOutputStream();
		generalReportTransferObject = new GeneralReportTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		try{
			if(inMessage.getPayload() != null){
				generalReportTransferObject = inMessage.getPayload();
				params = generalReportTransferObject.getDataFilter();
				if(params == null) params = new HashMap<String, Object>();
				final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManagerReport);
				transactionTemplate.execute(new TransactionCallback<Object>() {
					@Override
					public Object doInTransaction(TransactionStatus arg0) {
						try {
							if(generalReportTransferObject.getDataSelection() != null && !generalReportTransferObject.getDataSelection().isEmpty()){
								JRDataSource dataSource = new JRBeanCollectionDataSource(generalReportTransferObject.getDataSelection());		    		
					    		params.put("beanCollectionDataSource", dataSource);
					    		reportGenerator.printReportToFile(generalReportTransferObject.getTemplateReportName(), dataSource, params, outputStreamData, EReportUtilConstant.getEnum(generalReportTransferObject.getTypeFile()));
					    	}else{
								Connection connection = dataSourceReport.getConnection();		    		
					    		params.put("dataSourceConnecion", connection);
					    		reportGenerator.printReportToFile(generalReportTransferObject.getTemplateReportName(), connection, params, outputStreamData, EReportUtilConstant.getEnum(generalReportTransferObject.getTypeFile()));
							}
						} catch (Exception e) {
							LOGGER.error("Generate Report System Error : "+e.getLocalizedMessage(), e);
							generalReportTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
							generalReportTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
							generalReportTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString()+e.getLocalizedMessage());
						}
						return null;
					}
				});	
			}
			generalReportTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
			generalReportTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			generalReportTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());			
		}catch (Exception e){
			LOGGER.error("Generate Report System Error : "+e.getLocalizedMessage(), e);
			generalReportTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			generalReportTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			generalReportTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString()+e.getLocalizedMessage());
		}
		setReturnStatusAndMessage(generalReportTransferObject, responseHeaderMap);
		Message<byte[]> message = new GenericMessage<byte[]>(outputStreamData.toByteArray(), responseHeaderMap);
		generalReportTransferObject = null;
		outputStreamData = null;
		return message;		
	}
	
}
