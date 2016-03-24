package com.tripoin.core.dto;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GeneralTransferObject")
public class GeneralTransferObject {
	
	@XmlElement(name = "ResponseCode", namespace = "")
	protected String responseCode;

	@XmlElement(name = "ResponseMsg", namespace = "")
	protected String responseMsg;

	@XmlElement(name = "ResponseDesc", namespace = "")
	protected String responseDesc;
	
	@XmlElement(name = "ParameterData", namespace = "")
	private HashMap<String, Object> parameterData;

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

	public HashMap<String, Object> getParameterData() {
		return parameterData;
	}

	public void setParameterData(HashMap<String, Object> parameterData) {
		this.parameterData = parameterData;
	}

	@Override
	public String toString() {
		return "GeneralTransferObject [responseCode=" + responseCode + ", responseMsg=" + responseMsg
				+ ", responseDesc=" + responseDesc + "]";
	}

}
