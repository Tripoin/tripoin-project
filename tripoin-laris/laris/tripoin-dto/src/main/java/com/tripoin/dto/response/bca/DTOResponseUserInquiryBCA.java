package com.tripoin.dto.response.bca;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.dto.app.bca.LanguageErrorMessage;
import com.tripoin.dto.app.bca.UserBCAData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTOResponseUserInquiryBCA extends UserBCAData {

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
		return "DTOResponseUserInquiryBCA [UserBCAData=" + super.toString() + "]";
	}
}
