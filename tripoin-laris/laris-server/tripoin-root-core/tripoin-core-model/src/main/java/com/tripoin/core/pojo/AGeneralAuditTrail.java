package com.tripoin.core.pojo;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@MappedSuperclass
public abstract class AGeneralAuditTrail implements IBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4752864267597314445L;
	private Integer status;
	private String remarks;
	private String createdBy;
	private String createdIP;
	private Timestamp createdTime;
	private String createdPlatform;
	private String modifiedBy;
	private String modifiedIP;
	private Timestamp modifiedTime;
	private String modifiedPlatform;
	
	public AGeneralAuditTrail() {}


	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="remarks", length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name="created_by", length=150)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	@Column(name="created_ip", length=150)
	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}

	@Column(name="created_time")
	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = new Timestamp(createdTime.getTime());
	}

	@Column(name="created_platform")
	public String getCreatedPlatform() {
		return createdPlatform;
	}

	public void setCreatedPlatform(String createdPlatform) {
		this.createdPlatform = createdPlatform;
	}

	@Column(name="modified_by", length=150)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name="modified_ip", length=150)
	public String getModifiedIP() {
		return modifiedIP;
	}

	public void setModifiedIP(String modifiedIP) {
		this.modifiedIP = modifiedIP;
	}

	@Column(name="modified_time")
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = new Timestamp(modifiedTime.getTime());
	}

	@Column(name="modified_platform")
	public String getModifiedPlatform() {
		return modifiedPlatform;
	}

	public void setModifiedPlatform(String modifiedPlatform) {
		this.modifiedPlatform = modifiedPlatform;
	}

	@Override
	public String toString() {
		return "[status=" + status + ", remarks=" + remarks + ", createdBy=" + createdBy
				+ ", createdIP=" + createdIP + ", createdTime=" + createdTime + ", createdPlatform=" + createdPlatform
				+ ", modifiedBy=" + modifiedBy + ", modifiedIP=" + modifiedIP + ", modifiedTime=" + modifiedTime
				+ ", modifiedPlatform=" + modifiedPlatform + "]";
	}

}
