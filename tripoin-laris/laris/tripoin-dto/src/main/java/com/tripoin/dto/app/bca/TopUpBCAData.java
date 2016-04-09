package com.tripoin.dto.app.bca;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class TopUpBCAData {
	String CompanyCode;
	String CustomerNumber;
	String TransactionID;
	String RequestDate;
	String Amount;
	String CurrencyCode;

	String TopUpID;
	String TransactionDate;

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

	public String getTopUpID() {
		return TopUpID;
	}

	public void setTopUpID(String topUpID) {
		TopUpID = topUpID;
	}

	public String getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "TopUpBCAData [CompanyCode=" + CompanyCode + ", CustomerNumber="
				+ CustomerNumber + ", TransactionID=" + TransactionID
				+ ", RequestDate=" + RequestDate + ", Amount=" + Amount
				+ ", CurrencyCode=" + CurrencyCode + ", TopUpID=" + TopUpID
				+ ", TransactionDate=" + TransactionDate + "]";
	}
}
