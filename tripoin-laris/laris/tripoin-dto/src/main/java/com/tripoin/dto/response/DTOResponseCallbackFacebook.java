package com.tripoin.dto.response;

import com.tripoin.dto.app.FacebookProfileData;
import com.tripoin.dto.app.GeneralTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTOResponseCallbackFacebook extends GeneralTransferObject {

	private FacebookProfileData facebookProfileData;
	private String state;
	private String accessToken;

	public FacebookProfileData getFacebookProfileData() {
		return facebookProfileData;
	}

	public void setFacebookProfileData(FacebookProfileData facebookProfileData) {
		this.facebookProfileData = facebookProfileData;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "DTOResponseCallbackFacebook [facebookProfileData="
				+ facebookProfileData + ", state=" + state + ", accessToken="
				+ accessToken + "]";
	}

}
