package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.pojo.User;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "UserData")
public class UserData {

	@XmlElement(name = "ID", namespace = "")
	private Integer id;
	
	@XmlElement(name = "Username", namespace = "")
	private String username;
	
	@XmlElement(name = "Enabled", namespace = "")
	private Integer enabled;
	
	@XmlElement(name = "ExpiredDate", namespace = "")
	private String expiredDate;
	
	@XmlElement(name = "NonLocked", namespace = "")
	private Integer nonLocked;
	
	@XmlElement(name = "Auth", namespace = "")
	private String auth;
	
	@XmlElement(name = "Status", namespace = "")
	private Integer status;
	
	@XmlElement(name = "Remarks", namespace = "")
	private String remarks;
	
	@XmlElement(name = "RoleData", namespace = "")
	private RoleData roleData;
	
	public UserData(){}
	
	public UserData(User user) {
		if(user != null){
			this.setId(user.getId());
			this.setUsername(user.getUsername());
			this.setEnabled(user.getEnabled());
			if(user.getExpiredDate() != null)
				this.setExpiredDate(ParameterConstant.FORMAT_DEFAULT.format(user.getExpiredDate()));
			this.setNonLocked(user.getNonLocked());
			this.setAuth(user.getAuth());
			this.setStatus(user.getStatus());
			this.setRemarks(user.getRemarks());
			if(user.getRole() != null)
				this.setRoleData(new RoleData(user.getRole()));
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auth == null) ? 0 : auth.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result
				+ ((expiredDate == null) ? 0 : expiredDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nonLocked == null) ? 0 : nonLocked.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result
				+ ((roleData == null) ? 0 : roleData.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserData other = (UserData) obj;
		if (auth == null) {
			if (other.auth != null)
				return false;
		} else if (!auth.equals(other.auth))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (expiredDate == null) {
			if (other.expiredDate != null)
				return false;
		} else if (!expiredDate.equals(other.expiredDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nonLocked == null) {
			if (other.nonLocked != null)
				return false;
		} else if (!nonLocked.equals(other.nonLocked))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (roleData == null) {
			if (other.roleData != null)
				return false;
		} else if (!roleData.equals(other.roleData))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserData [id=" + id + ", username=" + username + ", enabled=" + enabled + ", expiredDate="
				+ expiredDate + ", nonLocked=" + nonLocked + ", auth=" + auth
				+ ", status=" + status + ", remarks=" + remarks + ", roleData="
				+ roleData + "]";
	}

}
