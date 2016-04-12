package com.tripoin.core.dto.request.bca;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTORequestPaymentBCA {

	@JsonProperty("CompanyCode")
	String companyCode;

	@JsonProperty("PrimaryID")
	String primaryID;

	@JsonProperty("TransactionID")
	String transactionID;

	@JsonProperty("ReferenceID")
	String referenceID;

	@JsonProperty("RequestDate")
	String requestDate;

	@JsonProperty("Amount")
	String amount;

	@JsonProperty("CurrencyCode")
	String currencyCode;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getPrimaryID() {
		return primaryID;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getReferenceID() {
		return referenceID;
	}

	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

}
