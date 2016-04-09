package com.tripoin.dto.request.bca;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTORequestPaymentBCA {

	String CompanyCode;
	String PrimaryID;
	String TransactionID;
	String ReferenceID;
	String RequestDate;
	String Amount;
	String CurrencyCode;

	public String getCompanyCode() {
		return CompanyCode;
	}

	public void setCompanyCode(String companyCode) {
		CompanyCode = companyCode;
	}

	public String getPrimaryID() {
		return PrimaryID;
	}

	public void setPrimaryID(String primaryID) {
		PrimaryID = primaryID;
	}

	public String getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}

	public String getReferenceID() {
		return ReferenceID;
	}

	public void setReferenceID(String referenceID) {
		ReferenceID = referenceID;
	}

	public String getRequestDate() {
		return RequestDate;
	}

	public void setRequestDate(String requestDate) {
		RequestDate = requestDate;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getCurrencyCode() {
		return CurrencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}

}
