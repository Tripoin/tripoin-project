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
	public String toString() {
		return "UserData [id=" + id + ", username=" + username + ", enabled=" + enabled + ", expiredDate="
				+ expiredDate + ", nonLocked=" + nonLocked + ", auth=" + auth
				+ ", status=" + status + ", remarks=" + remarks + ", roleData="
				+ roleData + "]";
	}

}
