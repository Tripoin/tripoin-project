package com.tripoin.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IPaginationService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("paginationService")
public class PaginationServiceImpl implements IPaginationService {

	@Autowired
	private ICommonRest commonRest;
	
	@Autowired
	private IStateFullRest stateFullRest;

	@Override
	public GeneralPagingTransferObject getPagination(GeneralPagingTransferObject generalPagingTransferObject) {
		return stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_PAGINATION), generalPagingTransferObject, GeneralPagingTransferObject.class);
	}	

}
