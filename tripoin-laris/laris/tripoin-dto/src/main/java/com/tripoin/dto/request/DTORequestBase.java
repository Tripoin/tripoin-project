package com.tripoin.dto.request;

import com.google.gson.annotations.SerializedName;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTORequestBase {

	@SerializedName("positionPage")
	Integer positionPage;
	
	@SerializedName("rowPerPage")
	Integer rowPerPage;

	public Integer getPositionPage() {
		return positionPage;
	}

	public void setPositionPage(Integer positionPage) {
		this.positionPage = positionPage;
	}

	public Integer getRowPerPage() {
		return rowPerPage;
	}

	public void setRowPerPage(Integer rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

}
