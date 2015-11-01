package com.tripoin.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.UserData;
import com.tripoin.core.dto.UserTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IUserService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private ICommonRest commonRest;
	
	@Autowired
	private IStateFullRest stateFullRest;
	
	@Override
	public UserData getUser() {
		UserTransferObject userTransferObject = stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_USER), UserTransferObject.class);
		return userTransferObject.getUserDatas().get(0);
	}

	@Override
	public UserTransferObject updateUser(UserData userData) {
		return stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_USER_UPDATE), userData, UserTransferObject.class);
	}

}
