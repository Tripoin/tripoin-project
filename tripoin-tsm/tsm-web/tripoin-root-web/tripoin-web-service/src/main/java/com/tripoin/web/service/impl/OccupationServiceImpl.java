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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OccupationData> getAllOccupationDatas() {		
		return stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_OCCUPATION_ALL), OccupationTransferObject.class).getOccupationDatas();
	}

	@Override
	public OccupationTransferObject updateOccupation(OccupationData occupationData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OccupationTransferObject saveeOccupation(OccupationData occupationData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralTransferObject deleteOccupation(OccupationData occupationData) {
		// TODO Auto-generated method stub
		return null;
	}

}
