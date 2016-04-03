package com.tripoin.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.dto.app.GeneralReportTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IGeneralReportService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("generalReportService")
public class GeneralReportServiceImpl implements IGeneralReportService {

	@Autowired
	private ICommonRest commonRest;
	
	@Autowired
	private IStateFullRest stateFullRest;

	@Override
	public byte[] getSelectedReport(GeneralReportTransferObject generalReportTransferObject) {
		stateFullRest.setDownloadedFile(true);
		return stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_REPORT_SELECTED), generalReportTransferObject, byte[].class);
	}

}
