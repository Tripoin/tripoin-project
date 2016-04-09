package com.tripoin.dto.app.bca;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class TransactionDetailBCAData {

	String TransactionID;
	String AccountStatementID;
	String TransactionDate;
	String TransactionType;
	String Amount;
	String CurrencyCode;
	String Description;
	String CurrentBalance;

	public String getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}

	public String getAccountStatementID() {
		return AccountStatementID;
	}

	public void setAccountStatementID(String accountStatementID) {
		AccountStatementID = accountStatementID;
	}

	public String getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}

	public String getTransactionType() {
		return TransactionType;
	}

	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCurrentBalance() {
		return CurrentBalance;
	}

	public void setCurrentBalance(String currentBalance) {
		CurrentBalance = currentBalance;
	}

	@Override
	public String toString() {
		return "TransactionDetailBCAData [TransactionID=" + TransactionID
				+ ", AccountStatementID=" + AccountStatementID
				+ ", TransactionDate=" + TransactionDate + ", TransactionType="
				+ TransactionType + ", Amount=" + Amount + ", CurrencyCode="
				+ CurrencyCode + ", Description=" + Description
				+ ", CurrentBalance=" + CurrentBalance + "]";
	}

}
