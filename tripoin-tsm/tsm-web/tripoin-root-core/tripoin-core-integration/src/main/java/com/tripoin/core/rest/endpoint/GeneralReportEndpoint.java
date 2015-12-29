package com.tripoin.core.rest.endpoint;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.GeneralReportTransferObject;
import com.tripoin.util.report.ReportGenerator;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("generalReportEndpoint")
public class GeneralReportEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(GeneralReportEndpoint.class);
    
    @Autowired
    private ReportGenerator reportGenerator;

	@Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<byte[]> exportStreamReport(Message<GeneralReportTransferObject> inMessage){
		ByteArrayOutputStream outputStreamData = new ByteArrayOutputStream();
		GeneralReportTransferObject generalReportTransferObject = new GeneralReportTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{
			if(inMessage.getPayload() != null){
				generalReportTransferObject = inMessage.getPayload();
				Map<String, Object> params = generalReportTransferObject.getDataFilter();
				JRDataSource dataSource = null;
				if(params != null){
					// TODO
					dataSource = new JRBeanCollectionDataSource(generalReportTransferObject.getDataSelection());		    		
		    		params.put("dataSourceManager", dataSource);
				}else{
	    			params = new HashMap<String, Object>();
			    	if(!generalReportTransferObject.getDataSelection().isEmpty()){
			    		dataSource = new JRBeanCollectionDataSource(generalReportTransferObject.getDataSelection());		    		
			    		params.put("beanCollectionDataSource", dataSource);
			    	}	
				}
				reportGenerator.printReportToFile(generalReportTransferObject.getTemplateReportName(), dataSource, params, outputStreamData);
			}
			generalReportTransferObject.setResponseCode("0");
			generalReportTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			generalReportTransferObject.setResponseDesc("Generate Report Success");			
		}catch (Exception e){
			LOGGER.error("Generate Report System Error : "+e.getLocalizedMessage(), e);
			generalReportTransferObject.setResponseCode("1");
			generalReportTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			generalReportTransferObject.setResponseDesc("Generate Report System Error : "+e.getLocalizedMessage());
		}
		setReturnStatusAndMessage(generalReportTransferObject, responseHeaderMap);
		Message<byte[]> message = new GenericMessage<byte[]>(outputStreamData.toByteArray(), responseHeaderMap);
		return message;		
	}
	
}
