package com.tripoin.dto.request.bca;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTORequestUserRegistrationBCA {

	String CustomerName;
	String DateOfBirth;
	String PrimaryID;
	String MobileNumber;
	String EmailAddress;
	String CompanyCode;
	String CustomerNumber;

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

	public String getPrimaryID() {
		return PrimaryID;
	}

	public void setPrimaryID(String primaryID) {
		PrimaryID = primaryID;
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

	@Override
	public String toString() {
		return "DTORequestUserRegistrationBCA [CustomerName=" + CustomerName
				+ ", DateOfBirth=" + DateOfBirth + ", PrimaryID=" + PrimaryID
				+ ", MobileNumber=" + MobileNumber + ", EmailAddress="
				+ EmailAddress + ", CompanyCode=" + CompanyCode
				+ ", CustomerNumber=" + CustomerNumber + "]";
	}

}
