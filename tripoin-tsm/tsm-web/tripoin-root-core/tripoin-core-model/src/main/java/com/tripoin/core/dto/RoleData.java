package com.tripoin.core.dto;

import com.tripoin.core.pojo.Role;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class RoleData {

	private Integer id;
	private String code;
	private Integer status;
	private String remarks;	

	public RoleData() {}
	
	public RoleData(Role role) {
		if(role != null){
			this.setId(role.getId());
			this.setCode(role.getCode());
			this.setStatus(role.getStatus());
			this.setRemarks(role.getRemarks());
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
	public String toString() {
		return "RoleData [id=" + id + ", code=" + code + ", status=" + status
				+ ", remarks=" + remarks + "]";
	}

}
