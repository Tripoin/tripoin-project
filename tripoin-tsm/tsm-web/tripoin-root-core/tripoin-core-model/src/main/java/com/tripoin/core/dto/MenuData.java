package com.tripoin.core.dto;


import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.pojo.Menu;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class MenuData {

	private Integer id;
	private String code;
	private String name;
	private Integer status;
	private String remarks;
	private String createdBy;
	private String createdIP;
	private String createdTime;
	private String modifiedBy;
	private String modifiedIP;
	private String modifiedTime;

	public MenuData() {}

	public MenuData(Menu menu) {
		if(menu != null){
			this.id = menu.getId();
			this.code = menu.getCode();
			this.name = menu.getName();
			this.status = menu.getStatus();
			this.remarks = menu.getRemarks();
			this.createdBy = menu.getCreatedBy();
			this.createdIP = menu.getCreatedIP();
			this.createdTime = ParameterConstant.FORMAT_DEFAULT.format(menu.getCreatedTime());
			this.modifiedBy = menu.getModifiedBy();
			this.modifiedIP = menu.getModifiedIP();
			if(menu.getModifiedTime() != null)
				this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.format(menu.getModifiedTime());
		}		
	}
	
	public MenuData(Integer id, String code, String name, Integer status,
			String remarks, String createdBy, String createdIP,
			String createdTime, String modifiedBy, String modifiedIP,
			String modifiedTime) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.status = status;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdIP = createdIP;
		this.createdTime = createdTime;
		this.modifiedBy = modifiedBy;
		this.modifiedIP = modifiedIP;
		this.modifiedTime = modifiedTime;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedIP() {
		return modifiedIP;
	}

	public void setModifiedIP(String modifiedIP) {
		this.modifiedIP = modifiedIP;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "MenuData [id=" + id + ", code=" + code + ", name=" + name
				+ ", status=" + status + ", remarks=" + remarks
				+ ", createdBy=" + createdBy + ", createdIP=" + createdIP
				+ ", createdTime=" + createdTime + ", modifiedBy=" + modifiedBy
				+ ", modifiedIP=" + modifiedIP + ", modifiedTime="
				+ modifiedTime + "]";
	}

}
