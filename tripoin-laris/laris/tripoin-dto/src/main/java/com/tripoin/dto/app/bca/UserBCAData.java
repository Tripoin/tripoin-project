package com.tripoin.dto.app.bca;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class UserBCAData extends BaseIdentifierBCAData {

	String CustomerName;
	String DateOfBirth;
	String MobileNumber;
	String EmailAddress;
	String CustomerNumber;
	
	String CurrencyCode;
	String Balance;
	
    String WalletStatus;

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}

	public String getCustomerNumber() {
		return CustomerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		CustomerNumber = customerNumber;
	}

	public String getCurrencyCode() {
		return CurrencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}

	public String getBalance() {
		return Balance;
	}

	public void setBalance(String balance) {
		Balance = balance;
	}

	public String getWalletStatus() {
		return WalletStatus;
	}

	public void setWalletStatus(String walletStatus) {
		WalletStatus = walletStatus;
	}

	@Override
	public String toString() {
		return "UserBCAData [CustomerName=" + CustomerName + ", DateOfBirth="
				+ DateOfBirth + ", MobileNumber=" + MobileNumber
				+ ", EmailAddress=" + EmailAddress + ", CustomerNumber="
				+ CustomerNumber + ", CurrencyCode=" + CurrencyCode
				+ ", Balance=" + Balance + ", WalletStatus=" + WalletStatus
				+ "]";
	}
	
}
