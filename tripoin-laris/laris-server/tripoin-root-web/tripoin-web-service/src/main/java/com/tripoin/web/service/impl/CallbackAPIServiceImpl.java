package com.tripoin.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tripoin.dto.response.DTOResponseCallbackFacebook;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.ICallbackAPIService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("callbackAPIService")
public class CallbackAPIServiceImpl implements ICallbackAPIService {

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
	public DTOResponseCallbackFacebook callbackTokenFacebook(String code, String state) {
		stateFullRest.clearAllCookies();
		stateFullRest.setUsername(this.username);
		stateFullRest.setPassword(this.password);
		DTOResponseCallbackFacebook dtoResponseCallbackFacebook = stateFullRest.get(
				commonRest.getUrl(WebServiceConstant.HTTP_CALLBACK_FACEBOOK)
				.concat("?code=").concat(code)
				.concat("&state=").concat(state), 
				DTOResponseCallbackFacebook.class);
		stateFullRest.clearAllCookies();
		return dtoResponseCallbackFacebook;
	}

}
