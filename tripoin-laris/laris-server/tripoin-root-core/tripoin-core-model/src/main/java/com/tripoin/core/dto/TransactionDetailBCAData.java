package com.tripoin.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class TransactionDetailBCAData {

	@JsonProperty("TransactionID")
	String transactionID;

	@JsonProperty("AccountStatementID")
	String accountStatementID;

	@JsonProperty("TransactionDate")
	String transactionDate;

	@JsonProperty("TransactionType")
	String transactionType;

	@JsonProperty("Amount")
	String amount;

	@JsonProperty("CurrencyCode")
	String currencyCode;

	@JsonProperty("Description")
	String description;

	@JsonProperty("CurrentBalance")
	String currentBalance;

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getAccountStatementID() {
		return accountStatementID;
	}

	public void setAccountStatementID(String accountStatementID) {
		this.accountStatementID = accountStatementID;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}

	@Override
	public String toString() {
		return "TransactionDetailBCAData [TransactionID=" + transactionID
				+ ", AccountStatementID=" + accountStatementID
				+ ", TransactionDate=" + transactionDate + ", TransactionType="
				+ transactionType + ", Amount=" + amount + ", CurrencyCode="
				+ currencyCode + ", Description=" + description
				+ ", CurrentBalance=" + currentBalance + "]";
	}

}
