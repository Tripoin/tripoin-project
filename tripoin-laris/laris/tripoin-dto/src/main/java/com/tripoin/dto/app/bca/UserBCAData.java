package com.tripoin.dto.app.bca;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class UserBCAData extends BaseIdentifierBCAData {

	@JsonProperty("CustomerName")
	String customerName;

	@JsonProperty("DateOfBirth")
	String dateOfBirth;

	@JsonProperty("MobileNumber")
	String mobileNumber;

	@JsonProperty("EmailAddress")
	String emailAddress;

	@JsonProperty("CustomerNumber")
	String customerNumber;

	@JsonProperty("CurrencyCode")
	String currencyCode;

	@JsonProperty("Balance")
	String balance;

	@JsonProperty("WalletStatus")
    String walletStatus;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getWalletStatus() {
		return walletStatus;
	}

	public void setWalletStatus(String walletStatus) {
		this.walletStatus = walletStatus;
	}

	@Override
	public String toString() {
		return "UserBCAData [CustomerName=" + customerName + ", DateOfBirth="
				+ dateOfBirth + ", MobileNumber=" + mobileNumber
				+ ", EmailAddress=" + emailAddress + ", CustomerNumber="
				+ customerNumber + ", CurrencyCode=" + currencyCode
				+ ", Balance=" + balance + ", WalletStatus=" + walletStatus
				+ "]";
	}
	
}
