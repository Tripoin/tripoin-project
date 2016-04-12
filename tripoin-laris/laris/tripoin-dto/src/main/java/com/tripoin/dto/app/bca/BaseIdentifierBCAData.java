package com.tripoin.dto.app.bca;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class BaseIdentifierBCAData {

	@JsonProperty("PrimaryID")
	String primaryID;

	@JsonProperty("CompanyCode")
	String companyCode;

	public String getPrimaryID() {
		return primaryID;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Override
	public String toString() {
		return "BaseIdentifierBCAData [PrimaryID=" + primaryID
				+ ", CompanyCode=" + companyCode + "]";
	}
	
}
