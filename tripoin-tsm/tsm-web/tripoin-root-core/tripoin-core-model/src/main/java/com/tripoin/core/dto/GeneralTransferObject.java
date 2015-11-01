package com.tripoin.core.dto;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class GeneralTransferObject {
	
	private String responseCode;

	private String responseMsg;

	private String responseDesc;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	@Override
	public String toString() {
		return "GeneralConnectionDTO [responseCode=" + responseCode
				+ ", responseMsg=" + responseMsg + ", responseDesc="
				+ responseDesc + "]";
	}

}
