package com.tripoin.core.dto.response.bca;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.core.dto.LanguageErrorMessage;
import com.tripoin.core.dto.PaymentBCAData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTOResponsePaymentBCA extends PaymentBCAData {

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
		return "DTOResponsePaymentBCA [PaymentBCAData=" + super.toString() + "]";
	}

}
