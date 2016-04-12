package com.tripoin.dto.response.bca;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.dto.app.bca.ErrorMessageData;
import com.tripoin.dto.app.bca.TransactionDetailBCAData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTOResponseTransactionInquiryBCA extends ErrorMessageData {

	@JsonProperty("CompanyCode")
	String companyCode;

	@JsonProperty("PrimaryID")
	String primaryID;

	@JsonProperty("TotalTransactions")
	String totalTransactions;

	@JsonProperty("LastAccountStatementID")
	String lastAccountStatementID;

	@JsonProperty("TransactionDetails")
	List<TransactionDetailBCAData> transactionDetails;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getPrimaryID() {
		return primaryID;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	public String getTotalTransactions() {
		return totalTransactions;
	}

	public void setTotalTransactions(String totalTransactions) {
		this.totalTransactions = totalTransactions;
	}

	public String getLastAccountStatementID() {
		return lastAccountStatementID;
	}

	public void setLastAccountStatementID(String lastAccountStatementID) {
		this.lastAccountStatementID = lastAccountStatementID;
	}

	public List<TransactionDetailBCAData> getTransactionDetails() {
		return transactionDetails;
	}

	public void setTransactionDetails(List<TransactionDetailBCAData> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	@Override
	public String toString() {
		return "DTOResponseTransactionInquiryBCA [CompanyCode=" + companyCode
				+ ", PrimaryID=" + primaryID + ", TotalTransactions="
				+ totalTransactions + ", LastAccountStatementID="
				+ lastAccountStatementID + ", TransactionDetails="
				+ transactionDetails + "]";
	}

}
