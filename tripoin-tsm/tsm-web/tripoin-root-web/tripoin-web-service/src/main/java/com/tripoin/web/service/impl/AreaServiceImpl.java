package com.tripoin.web.service.impl;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.AreaData;
import com.tripoin.core.dto.AreaTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IDataLoadStarted;
import com.tripoin.web.service.IAreaService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("areaService")
public class AreaServiceImpl implements IAreaService {

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
	public AreaTransferObject getAllAreaDatas(GeneralTransferObject generalTransferObject) {
		return stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_AREA_ALL_PAGE), generalTransferObject, AreaTransferObject.class);
	}

	@Override
	public GeneralTransferObject updateArea(AreaTransferObject dataTransferObject, final ServletContext servletContext) {
		GeneralTransferObject generalTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_AREA_UPDATE), dataTransferObject, GeneralTransferObject.class);
		threadBuildAreaContainer(generalTransferObject, servletContext);
		return generalTransferObject;
	}

	@Override
	public GeneralTransferObject saveArea(AreaTransferObject dataTransferObject, final ServletContext servletContext) {
		GeneralTransferObject generalTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_AREA_SAVE), dataTransferObject, GeneralTransferObject.class);
		threadBuildAreaContainer(generalTransferObject, servletContext);
		return generalTransferObject;
	}

	@Override
	public AreaTransferObject deleteArea(AreaTransferObject dataTransferObject, final ServletContext servletContext) {
		AreaTransferObject areaTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_AREA_DELETE), dataTransferObject, AreaTransferObject.class);
		threadBuildAreaContainer(areaTransferObject, servletContext);
		return areaTransferObject;
	}
	
	private void threadBuildAreaContainer(GeneralTransferObject generalTransferObject, final ServletContext servletContext){
		if(EResponseCode.RC_SUCCESS.getResponseCode().equals(generalTransferObject.getResponseCode())){
			taskExecutor.execute(new Runnable() {				
				@Override
				public void run() {
					List<AreaData> areaDatas = dataLoadStarted.loadAreaData();
					servletContext.setAttribute(WebServiceConstant.CONTEXT_CONSTANT_AREA, areaDatas);
				}
			});
		}
	}

}
