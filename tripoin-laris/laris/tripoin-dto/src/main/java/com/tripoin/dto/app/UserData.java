package com.tripoin.dto.app;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class UserData {

	private String username;

	private Integer enabled;

	private String expiredDate;

	private Integer nonLocked;

	private String auth;

	private Integer status;

	private String remarks;

	private RoleData roleData;
	
	public UserData(){}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Integer getNonLocked() {
		return nonLocked;
	}

	public void setNonLocked(Integer nonLocked) {
		this.nonLocked = nonLocked;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
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

	public RoleData getRoleData() {
		return roleData;
	}

	public void setRoleData(RoleData roleData) {
		this.roleData = roleData;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserData userData = (UserData) o;

		if (username != null ? !username.equals(userData.username) : userData.username != null)
			return false;
		if (enabled != null ? !enabled.equals(userData.enabled) : userData.enabled != null)
			return false;
		if (expiredDate != null ? !expiredDate.equals(userData.expiredDate) : userData.expiredDate != null)
			return false;
		if (nonLocked != null ? !nonLocked.equals(userData.nonLocked) : userData.nonLocked != null)
			return false;
		if (auth != null ? !auth.equals(userData.auth) : userData.auth != null) return false;
		if (status != null ? !status.equals(userData.status) : userData.status != null)
			return false;
		if (remarks != null ? !remarks.equals(userData.remarks) : userData.remarks != null)
			return false;
		return !(roleData != null ? !roleData.equals(userData.roleData) : userData.roleData != null);

	}

	@Override
	public int hashCode() {
		int result = username != null ? username.hashCode() : 0;
		result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
		result = 31 * result + (expiredDate != null ? expiredDate.hashCode() : 0);
		result = 31 * result + (nonLocked != null ? nonLocked.hashCode() : 0);
		result = 31 * result + (auth != null ? auth.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
		result = 31 * result + (roleData != null ? roleData.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "UserData{" +
				"username='" + username + '\'' +
				", enabled=" + enabled +
				", expiredDate='" + expiredDate + '\'' +
				", nonLocked=" + nonLocked +
				", auth='" + auth + '\'' +
				", status=" + status +
				", remarks='" + remarks + '\'' +
				", roleData=" + roleData +
				'}';
	}
}
