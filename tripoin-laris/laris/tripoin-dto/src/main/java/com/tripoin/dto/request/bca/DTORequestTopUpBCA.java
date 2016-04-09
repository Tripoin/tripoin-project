package com.tripoin.dto.request.bca;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTORequestTopUpBCA {
	
	String CompanyCode;
	String CustomerNumber;
	String TransactionID;
	String RequestDate;
	String Amount;
	String CurrencyCode;

	public String getCompanyCode() {
		return CompanyCode;
	}

	public void setCompanyCode(String companyCode) {
		CompanyCode = companyCode;
	}

	public String getCustomerNumber() {
		return CustomerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		CustomerNumber = customerNumber;
	}

	public String getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
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

	@Override
	public String toString() {
		return "DTORequestTopUpBCA [CompanyCode=" + CompanyCode
				+ ", CustomerNumber=" + CustomerNumber + ", TransactionID="
				+ TransactionID + ", RequestDate=" + RequestDate + ", Amount="
				+ Amount + ", CurrencyCode=" + CurrencyCode + "]";
	}
}
