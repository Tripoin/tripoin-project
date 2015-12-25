package com.tripoin.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
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
	public GeneralTransferObject updateOccupation(OccupationData occupationData) {
		return stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_UPDATE), occupationData, GeneralTransferObject.class);
	}

	@Override
	public GeneralTransferObject saveOccupation(OccupationData occupationData) {
		return stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_SAVE), occupationData, GeneralTransferObject.class);
	}

	@Override
	public OccupationTransferObject deleteOccupation(List<OccupationData> occupationDatas) {
		OccupationTransferObject occupationTransferObject = new OccupationTransferObject();
		occupationTransferObject.setOccupationDatas(occupationDatas);
		return stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_DELETE), occupationTransferObject, OccupationTransferObject.class);
	}

}
