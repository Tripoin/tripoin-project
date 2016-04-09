package com.tripoin.dto.app.bca;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class ErrorMessageData {

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
		return "ErrorMessageData [ErrorCode=" + ErrorCode + ", ErrorMessage="
				+ ErrorMessage + "]";
	}

}
