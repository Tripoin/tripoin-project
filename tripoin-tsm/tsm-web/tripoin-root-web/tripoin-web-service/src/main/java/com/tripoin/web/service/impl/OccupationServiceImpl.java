package com.tripoin.web.service.impl;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

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
	public OccupationData getOccupation() {
		return stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION), OccupationTransferObject.class).getOccupationDatas().get(0);
	}

	@Override
	public List<OccupationData> getAllOccupationDatas() {		
		return stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_ALL), OccupationTransferObject.class).getOccupationDatas();
	}

	@Override
	public OccupationTransferObject getAllOccupationDatas(OccupationTransferObject occupationTransferObject) {
		return stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_ALL_PAGE), occupationTransferObject, OccupationTransferObject.class);
	}

	@Override
	public GeneralTransferObject updateOccupation(OccupationTransferObject dataTransferObject, final ServletContext servletContext) {
		GeneralTransferObject generalTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_UPDATE), dataTransferObject, GeneralTransferObject.class);
		threadBuildOccupationContainer(generalTransferObject, servletContext);
		return generalTransferObject;
	}

	@Override
	public GeneralTransferObject saveOccupation(OccupationTransferObject dataTransferObject, final ServletContext servletContext) {
		GeneralTransferObject generalTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_SAVE), dataTransferObject, GeneralTransferObject.class);
		threadBuildOccupationContainer(generalTransferObject, servletContext);
		return generalTransferObject;
	}

	@Override
	public OccupationTransferObject deleteOccupation(OccupationTransferObject dataTransferObject, final ServletContext servletContext) {
		OccupationTransferObject occupationTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_DELETE), dataTransferObject, OccupationTransferObject.class);
		threadBuildOccupationContainer(occupationTransferObject, servletContext);
		return occupationTransferObject;
	}
	
	private void threadBuildOccupationContainer(GeneralTransferObject generalTransferObject, final ServletContext servletContext){
		if("0".equals(generalTransferObject.getResponseCode())){
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
