package com.tripoin.core.dto.response.bca;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.core.dto.BaseIdentifierBCAData;
import com.tripoin.core.dto.LanguageErrorMessage;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTOResponseUserUpdateBCA extends BaseIdentifierBCAData {

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
		return "DTOResponseUserUpdateBCA [BaseResponseMessageBCA=" + super.toString() + "]";
	}

}