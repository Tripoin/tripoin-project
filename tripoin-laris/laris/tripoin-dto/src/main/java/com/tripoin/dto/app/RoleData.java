package com.tripoin.dto.app;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class RoleData {

	String code;

	Integer status;

	String remarks;

	public RoleData() {}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RoleData roleData = (RoleData) o;

		if (code != null ? !code.equals(roleData.code) : roleData.code != null) return false;
		if (status != null ? !status.equals(roleData.status) : roleData.status != null)
			return false;
		return !(remarks != null ? !remarks.equals(roleData.remarks) : roleData.remarks != null);

	}

	@Override
	public int hashCode() {
		int result = code != null ? code.hashCode() : 0;
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
		return result;
	}
}
