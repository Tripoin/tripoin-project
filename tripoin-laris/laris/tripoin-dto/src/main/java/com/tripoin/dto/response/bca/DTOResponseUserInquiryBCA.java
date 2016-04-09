package com.tripoin.dto.response.bca;

import com.tripoin.dto.app.bca.LanguageErrorMessage;
import com.tripoin.dto.app.bca.UserBCAData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTOResponseUserInquiryBCA extends UserBCAData {

	String ErrorCode;
	LanguageErrorMessage ErrorMessage;

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public LanguageErrorMessage getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(LanguageErrorMessage errorMessage) {
		ErrorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "DTOResponseUserInquiryBCA [UserBCAData=" + super.toString() + "]";
	}
}
