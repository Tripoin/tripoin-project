package com.tripoin.dto.response.bca;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.dto.app.bca.BaseIdentifierBCAData;
import com.tripoin.dto.app.bca.LanguageErrorMessage;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTOResponseUserRegistrationBCA extends BaseIdentifierBCAData {

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
		return "DTOResponseUserRegistrationBCA [BaseResponseMessageBCA=" + super.toString() + 
				", ErrorCode=" + getErrorCode() + ", ErrorMessage=" +  getErrorMessage() + "]";
	}

}