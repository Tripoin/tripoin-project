package com.tripoin.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IForgotPasswordService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("forgotPasswordService")
public class ForgotPasswordServiceImpl implements IForgotPasswordService {

	@Autowired
	private ICommonRest commonRest;
	
	@Autowired
	private IStateFullRest stateFullRest;	

	@Override
	public GeneralTransferObject forgotPassword(String email) {
		stateFullRest.clearAllCookies();
		stateFullRest.setUsername("tripoin.app.web");
		stateFullRest.setPassword("72jsoH!=jn3oskqPHJ#@");
		GeneralTransferObject generalTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_FORGOT_PASSWORD), ParameterConstant.FORGOT_PASSWORD_EMAIL.concat(email), GeneralTransferObject.class);
		stateFullRest.clearAllCookies();
		return generalTransferObject;
	}

	@Override
	public GeneralTransferObject verifyForgotPassword(String username, String uuid) {
		stateFullRest.clearAllCookies();
		stateFullRest.setUsername("tripoin.app.web");
		stateFullRest.setPassword("72jsoH!=jn3oskqPHJ#@");
		GeneralTransferObject generalTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_FORGOT_PASSWORD_VERIFY), ParameterConstant.FORGOT_PASSWORD_USERNAME.concat(username).concat("&").concat(ParameterConstant.FORGOT_PASSWORD_UUID).concat(uuid), GeneralTransferObject.class);
		stateFullRest.clearAllCookies();
		return generalTransferObject;
	}

}
