package com.tripoin.web.service.impl;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IDataLoadStarted;
import com.tripoin.web.service.IOccupationService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("occupationService")
public class OccupationServiceImpl implements IOccupationService {

	@Autowired
	private ICommonRest commonRest;
	
	@Autowired
	private IStateFullRest stateFullRest;
	
	@Autowired
	private IDataLoadStarted dataLoadStarted;
	
	@Autowired
	@Qualifier(value="web-app-async-task-executor")
	private ThreadPoolTaskExecutor taskExecutor;

	@Override
	public OccupationTransferObject getAllOccupationDatas(GeneralPagingTransferObject generalPagingTransferObject) {
		return stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_ALL_PAGE), generalPagingTransferObject, OccupationTransferObject.class);
	}

	@Override
	public GeneralTransferObject updateOccupation(GeneralTransferObject dataTransferObject, final ServletContext servletContext) {
		GeneralTransferObject generalTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_UPDATE), dataTransferObject, GeneralTransferObject.class);
		threadBuildOccupationContainer(generalTransferObject, servletContext);
		return generalTransferObject;
	}
	
	private void threadBuildOccupationContainer(GeneralTransferObject generalTransferObject, final ServletContext servletContext){
		if(EResponseCode.RC_SUCCESS.getResponseCode().equals(generalTransferObject.getResponseCode())){
			taskExecutor.execute(new Runnable() {				
				@Override
				public void run() {
					List<OccupationData> occupationDatas = dataLoadStarted.loadOccupationData();
					servletContext.setAttribute(WebServiceConstant.CONTEXT_CONSTANT_OCCUPATION, occupationDatas);
				}
			});
		}
	}

}
