package com.tripoin.web.view.base.container;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.MediaType;

import com.tripoin.web.common.EReportUIConstant;
import com.tripoin.web.common.ReportUtil;
import com.vaadin.server.Page;
import com.vaadin.server.ResourceReference;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public abstract class ATripoinDataReport {

	/**
	 * <b>Sample Code:</b><br>
	 * <code>exportStreamDataReport(data, "report.jasper", null, "Report-Monthly", EReportUIConstant.REPORT_PDF);</code><br>
	 * @param reportUtil
	 * @param data
	 * @param reportFilename
	 * @param params
	 * @param outputFilename
	 * @param typeFile
	 */
	public void exportStreamDataReport(ReportUtil reportUtil, Collection<?> data, String reportFilename, Map<String, Object> params, String outputFilename, EReportUIConstant typeFile){
		outputFilename = outputFilename.concat("-").concat(UUID.randomUUID().toString()).concat(typeFile.toString());
		final StreamResource resource = reportUtil.exportStreamReport(data, reportFilename, params, outputFilename, typeFile);	    
    	if(resource != null){
			if(data != null && !data.isEmpty()){
				resource.setMIMEType(typeFile.getOperand());
		    	resource.getStream().setParameter("Content-Disposition", "attachment; filename=\"".concat(outputFilename).concat("\""));
		    	final Window window = new Window("Report Preview");
			    final Embedded embedded = new Embedded();
			    embedded.setType(2);
			    embedded.setSizeFull();
		    	embedded.setSource(resource);
			    window.setContent(embedded);
			    window.setModal(true);
			    window.center();
			    window.setWidth("90%");
			    window.setHeight("90%");
			    UI.getCurrent().addWindow(window);		
			}else{
				resource.setMIMEType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
				ResourceReference resourceReference = setResourceReport("export", resource);
				Page.getCurrent().open(resourceReference.getURL(), "_self", false);			
			}
		}
	}
	
	protected abstract ResourceReference setResourceReport(String name, StreamResource resource);
	
}
