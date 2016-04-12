package com.tripoin.dto.app;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class BaseSignUpData {
	
	String state;
	String accessToken;

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
		return "BaseSignUpData [state="
				+ state + ", accessToken=" + accessToken + "]";
	}

}
