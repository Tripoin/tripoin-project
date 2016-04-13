package com.tripoin.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tripoin.dto.app.CustomerData;
import com.tripoin.dto.app.FacebookProfileData;
import com.tripoin.dto.app.GeneralTransferObject;
import com.tripoin.dto.request.DTORequestSignUpFacebook;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.ISignUpService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("signUpService")
public class SignUpServiceImpl implements ISignUpService {

	@Autowired
	private ICommonRest commonRest;
	
	@Autowired
	private IStateFullRest stateFullRest;	

	private String username;
	private String password;

	@Value("${tripoin.web.app.username}")	
	public void setUsername(String username) {this.username = username;}
	@Value("${tripoin.web.app.password}")
	public void setPassword(String password) {this.password = password;}

	@Override
	public GeneralTransferObject registerWithFacebook(DTORequestSignUpFacebook<FacebookProfileData, CustomerData> dtoRequestSignUp) {
		stateFullRest.clearAllCookies();
		stateFullRest.setUsername(this.username);
		stateFullRest.setPassword(this.password);
		GeneralTransferObject generalTransferObject = stateFullRest.post(
				commonRest.getUrl(WebServiceConstant.HTTP_SIGNUP_FACEBOOK), 
				dtoRequestSignUp,
				GeneralTransferObject.class);
		stateFullRest.clearAllCookies();
		return generalTransferObject;
	}

}
