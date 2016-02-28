package com.tripoin.core.pojo;

import java.sql.Date;
import java.text.ParseException;

import javax.persistence.Column;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.AGeneralAuditTrailData;

/**
 * @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class AGeneralAuditTrail implements IBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4752864267597314445L;
	private Integer status;
	private String remarks;
	private String createdBy;
	private String createdIP;
	private Date createdTime;
	private String createdPlatform;
	private String modifiedBy;
	private String modifiedIP;
	private Date modifiedTime;
	private String modifiedPlatform;
	
	public AGeneralAuditTrail() {}
	
	public AGeneralAuditTrail(AGeneralAuditTrailData aGeneralAuditTrailData) {
		super();
		this.status = aGeneralAuditTrailData.getStatus();
		this.remarks = aGeneralAuditTrailData.getRemarks();
		this.createdBy = aGeneralAuditTrailData.getCreatedBy();
		this.createdIP = aGeneralAuditTrailData.getCreatedIP();
		try {
			this.createdTime = new Date(ParameterConstant.FORMAT_DEFAULT.parse(aGeneralAuditTrailData.getCreatedTime()).getTime());
		} catch (ParseException e) {
			this.createdTime = new Date(new java.util.Date().getTime());
		}
		this.createdPlatform = aGeneralAuditTrailData.getCreatedPlatform();
		this.modifiedBy = aGeneralAuditTrailData.getModifiedBy();
		this.modifiedIP = aGeneralAuditTrailData.getModifiedIP();
		try {
			this.modifiedTime = new Date(ParameterConstant.FORMAT_DEFAULT.parse(aGeneralAuditTrailData.getModifiedTime()).getTime());
		} catch (ParseException e) {
			this.modifiedTime = new Date(new java.util.Date().getTime());
		}
		this.modifiedPlatform = aGeneralAuditTrailData.getModifiedPlatform();
	}
	
	public AGeneralAuditTrail(Integer status, String remarks, String createdBy, String createdIP, String createdTime,
			String createdPlatform, String modifiedBy, String modifiedIP, String modifiedTime, String modifiedPlatform) {
		super();
		this.status = status;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdIP = createdIP;
		try {
			this.createdTime = new Date(ParameterConstant.FORMAT_DEFAULT.parse(createdTime).getTime());
		} catch (ParseException e) {
			this.createdTime = new Date(new java.util.Date().getTime());
		}
		this.createdPlatform = createdPlatform;
		this.modifiedBy = modifiedBy;
		this.modifiedIP = modifiedIP;
		try {
			this.modifiedTime = new Date(ParameterConstant.FORMAT_DEFAULT.parse(modifiedTime).getTime());
		} catch (ParseException e) {
			this.modifiedTime = new Date(new java.util.Date().getTime());
		}
		this.modifiedPlatform = modifiedPlatform;
	}	
	
	public AGeneralAuditTrail(Integer status, String remarks, String createdBy, String createdIP, Date createdTime,
			String createdPlatform, String modifiedBy, String modifiedIP, Date modifiedTime, String modifiedPlatform) {
		super();
		this.status = status;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdIP = createdIP;
		this.createdTime = createdTime;
		this.createdPlatform = createdPlatform;
		this.modifiedBy = modifiedBy;
		this.modifiedIP = modifiedIP;
		this.modifiedTime = modifiedTime;
		this.modifiedPlatform = modifiedPlatform;
	}

	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="reamarks", length=255)
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


	@Column(name="created_by")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setCreatedTime(java.util.Date createdTime) {
		this.createdTime = new Date(createdTime.getTime());
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
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public void setModifiedTime(java.util.Date modifiedTime) {
		this.modifiedTime = new Date(modifiedTime.getTime());
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
		return "AGeneralAuditTrail [status=" + status + ", remarks=" + remarks + ", createdBy=" + createdBy
				+ ", createdIP=" + createdIP + ", createdTime=" + createdTime + ", createdPlatform=" + createdPlatform
				+ ", modifiedBy=" + modifiedBy + ", modifiedIP=" + modifiedIP + ", modifiedTime=" + modifiedTime
				+ ", modifiedPlatform=" + modifiedPlatform + "]";
	}

}
