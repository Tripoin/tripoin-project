package com.tripoin.web.service;

import com.tripoin.dto.app.GeneralReportTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IGeneralReportService {
    
	public byte[] getSelectedReport(GeneralReportTransferObject generalReportTransferObject);

}
