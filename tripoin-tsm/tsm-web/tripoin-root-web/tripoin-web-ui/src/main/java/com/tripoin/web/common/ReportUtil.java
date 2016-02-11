package com.tripoin.web.common;

import com.tripoin.core.dto.GeneralReportTransferObject;
import com.tripoin.web.service.IGeneralReportService;
import com.vaadin.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ReportUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(ReportUtil.class);
    
	@Autowired
	private IGeneralReportService iGeneralReportService;
	
    public StreamResource exportStreamReport(Collection<?> data, String reportFilename, Map<String, Object> params, String outputFilename, EReportUIConstant typeFile){    	
    	return createStreamReport(data, reportFilename, params, outputFilename, typeFile);
    }
    
    private StreamResource createStreamReport(Collection<?> data, String reportFilename, Map<String, Object> params, String outputFilename, EReportUIConstant typeFile){
    	final GeneralReportTransferObject generalReportTransferObject = new GeneralReportTransferObject();
    	generalReportTransferObject.setTemplateReportName(reportFilename);
    	generalReportTransferObject.setTypeFile(typeFile.getOperator());
    	generalReportTransferObject.setDataSelection(data);
    	return new StreamResource(new StreamResource.StreamSource() {
			private static final long serialVersionUID = -7890689648689132725L;
			@Override
            public InputStream getStream () {
                ByteArrayOutputStream pdfBuffer = new ByteArrayOutputStream();
                try {
                	pdfBuffer.write(iGeneralReportService.getSelectedReport(generalReportTransferObject));
				} catch (NullPointerException ne) {
					LOGGER.error("Response Web Service Generate Report : Null");
				} catch (Exception e) {
					LOGGER.error("Create Stream Resource Failure",e);
				}
                return new ByteArrayInputStream(pdfBuffer.toByteArray());
            }			
        }, outputFilename);
    }

}
