package com.tripoin.dto.app;

import java.util.Collection;
import java.util.Map;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class GeneralReportTransferObject extends GeneralTransferObject {

	String reportName;

	String templateReportName;

	String typeFile;

	Collection<?> dataSelection;

	Map<String, Object> dataFilter;

	public GeneralReportTransferObject(){}
	
	public GeneralReportTransferObject(String reportName, String templateReportName, String typeFile){
		this.reportName = reportName;
		this.templateReportName = reportName;
		this.typeFile = typeFile;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getTemplateReportName() {
		return templateReportName;
	}

	public void setTemplateReportName(String templateReportName) {
		this.templateReportName = templateReportName;
	}

	public String getTypeFile() {
		return typeFile;
	}

	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}

	public Collection<?> getDataSelection() {
		return dataSelection;
	}

	public void setDataSelection(Collection<?> dataSelection) {
		this.dataSelection = dataSelection;
	}

	public Map<String, Object> getDataFilter() {
		return dataFilter;
	}

	public void setDataFilter(Map<String, Object> dataFilter) {
		this.dataFilter = dataFilter;
	}

	@Override
	public String toString() {
		return "GeneralReportTransferObject [reportName=" + reportName
				+ ", templateReportName=" + templateReportName + ", typeFile="
				+ typeFile + ", dataSelection=" + dataSelection
				+ ", dataFilter=" + dataFilter + "]";
	}

}
