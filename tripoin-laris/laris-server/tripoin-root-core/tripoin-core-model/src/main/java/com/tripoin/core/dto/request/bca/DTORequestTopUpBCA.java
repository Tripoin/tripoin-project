package com.tripoin.core.dto.request.bca;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTORequestTopUpBCA {
	
	@JsonProperty("CompanyCode")
	String companyCode;

	@JsonProperty("CustomerNumber")
	String customerNumber;

	@JsonProperty("TransactionID")
	String transactionID;

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

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
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

	@Override
	public String toString() {
		return "DTORequestTopUpBCA [CompanyCode=" + companyCode
				+ ", CustomerNumber=" + customerNumber + ", TransactionID="
				+ transactionID + ", RequestDate=" + requestDate + ", Amount="
				+ amount + ", CurrencyCode=" + currencyCode + "]";
	}
}
