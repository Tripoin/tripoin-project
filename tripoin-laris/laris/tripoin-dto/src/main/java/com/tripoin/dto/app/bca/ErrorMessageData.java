package com.tripoin.dto.app.bca;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class ErrorMessageData {

	@JsonProperty("ErrorCode")
	String errorCode;
	
	@JsonProperty("ErrorMessage")
	LanguageErrorMessage errorMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public LanguageErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(LanguageErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorMessageData [ErrorCode=" + errorCode + ", ErrorMessage="
				+ errorMessage + "]";
	}

}
