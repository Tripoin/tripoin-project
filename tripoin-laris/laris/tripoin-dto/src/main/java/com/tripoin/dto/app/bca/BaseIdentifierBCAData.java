package com.tripoin.dto.app.bca;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class BaseIdentifierBCAData {

	String PrimaryID;
	String CompanyCode;

	public String getPrimaryID() {
		return PrimaryID;
	}

	public void setPrimaryID(String primaryID) {
		PrimaryID = primaryID;
	}

	public String getCompanyCode() {
		return CompanyCode;
	}

	public void setCompanyCode(String companyCode) {
		CompanyCode = companyCode;
	}

	@Override
	public String toString() {
		return "BaseIdentifierBCAData [PrimaryID=" + PrimaryID
				+ ", CompanyCode=" + CompanyCode + "]";
	}
	
}
