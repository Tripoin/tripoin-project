package com.tripoin.core.dto.request.bca;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTORequestUserRegistrationBCA {

	@JsonProperty("CustomerName")
	String customerName;

	@JsonProperty("DateOfBirth")
	String dateOfBirth;
	
	@JsonProperty("PrimaryID")
	String primaryID;

	@JsonProperty("MobileNumber")
	String mobileNumber;

	@JsonProperty("EmailAddress")
	String emailAddress;

	@JsonProperty("CompanyCode")
	String companyCode;

	@JsonProperty("CustomerNumber")
	String customerNumber;

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

	public String getPrimaryID() {
		return primaryID;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
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

}
