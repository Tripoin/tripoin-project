package com.tripoin.dto.request;

import com.tripoin.dto.app.BaseSignUpData;
import com.tripoin.dto.app.FacebookProfileData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTORequestSignUpFacebook extends BaseSignUpData {

	FacebookProfileData facebookProfileData;

	public FacebookProfileData getFacebookProfileData() {
		return facebookProfileData;
	}

	public void setFacebookProfileData(FacebookProfileData facebookProfileData) {
		this.facebookProfileData = facebookProfileData;
	}

	@Override
	public String toString() {
		return "DTORequestSignUp [facebookProfileData=" + facebookProfileData
				+ ", customerData=" + getCustomerData() + ", state=" + getState()
				+ ", accessToken=" + getAccessToken() + "]";
	}
}
