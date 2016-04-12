package com.tripoin.dto.request;

import com.tripoin.dto.app.BaseSignUpData;
import com.tripoin.dto.app.CustomerData;
import com.tripoin.dto.app.FacebookProfileData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 * @param <FPX><CD>
 */
public class DTORequestSignUpFacebook<FPX extends FacebookProfileData, CD extends CustomerData> extends BaseSignUpData {

	/**
	 * Override by Fauzi for Parcelable usability
	 */
	FPX facebookProfileData;
	CD customerData;

	public FPX getFacebookProfileData() {
		return facebookProfileData;
	}

	public void setFacebookProfileData(FPX facebookProfileData) {
		this.facebookProfileData = facebookProfileData;
	}

	public CD getCustomerData() {
		return customerData;
	}

	public void setCustomerData(CD customerData) {
		this.customerData = customerData;
	}

	@Override
	public String toString() {
		return "DTORequestSignUpFacebook{" +
				"facebookProfileData=" + facebookProfileData +
				", customerData=" + customerData +
				'}';
	}
}
