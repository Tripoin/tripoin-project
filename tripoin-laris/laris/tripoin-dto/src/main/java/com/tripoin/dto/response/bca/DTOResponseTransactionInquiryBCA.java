package com.tripoin.dto.response.bca;

import java.util.List;

import com.tripoin.dto.app.bca.ErrorMessageData;
import com.tripoin.dto.app.bca.TransactionDetailBCAData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTOResponseTransactionInquiryBCA extends ErrorMessageData {

	String CompanyCode;
	String PrimaryID;
	String TotalTransactions;
	String LastAccountStatementID;
	List<TransactionDetailBCAData> TransactionDetails;

	public String getCompanyCode() {
		return CompanyCode;
	}

	public void setCompanyCode(String companyCode) {
		CompanyCode = companyCode;
	}

	public String getPrimaryID() {
		return PrimaryID;
	}

	public void setPrimaryID(String primaryID) {
		PrimaryID = primaryID;
	}

	public String getTotalTransactions() {
		return TotalTransactions;
	}

	public void setTotalTransactions(String totalTransactions) {
		TotalTransactions = totalTransactions;
	}

	public String getLastAccountStatementID() {
		return LastAccountStatementID;
	}

	public void setLastAccountStatementID(String lastAccountStatementID) {
		LastAccountStatementID = lastAccountStatementID;
	}

	public List<TransactionDetailBCAData> getTransactionDetails() {
		return TransactionDetails;
	}

	public void setTransactionDetails(
			List<TransactionDetailBCAData> transactionDetails) {
		TransactionDetails = transactionDetails;
	}

	@Override
	public String toString() {
		return "DTOResponseTransactionInquiryBCA [CompanyCode=" + CompanyCode
				+ ", PrimaryID=" + PrimaryID + ", TotalTransactions="
				+ TotalTransactions + ", LastAccountStatementID="
				+ LastAccountStatementID + ", TransactionDetails="
				+ TransactionDetails + "]";
	}

}
