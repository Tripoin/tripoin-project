package com.tripoin.dto.request;

import com.tripoin.dto.app.CustomerData;
import com.tripoin.dto.app.FacebookProfileData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTORequestSignUp {

	FacebookProfileData facebookProfileData;
	CustomerData customerData;
	String state;
	String accessToken;

	public FacebookProfileData getFacebookProfileData() {
		return facebookProfileData;
	}

	public void setFacebookProfileData(FacebookProfileData facebookProfileData) {
		this.facebookProfileData = facebookProfileData;
	}

	public CustomerData getCustomerData() {
		return customerData;
	}

	public void setCustomerData(CustomerData customerData) {
		this.customerData = customerData;
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
		return "DTORequestSignUp [facebookProfileData=" + facebookProfileData
				+ ", customerData=" + customerData + ", state=" + state
				+ ", accessToken=" + accessToken + "]";
	}
}
