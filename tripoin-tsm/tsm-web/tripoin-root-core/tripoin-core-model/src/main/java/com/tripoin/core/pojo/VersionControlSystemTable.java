package com.tripoin.core.pojo;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class VersionControlSystemTable implements Serializable {

	private static final long serialVersionUID = -3920232227298104572L;
	private Long id;
	private String code;
	private Long totalRow;
	private int status;
	private String remarks;
	private String createdBy;
	private String createdIP;
	private Date createdTime;
    private String createdPlatform;
	private String modifiedBy;
	private String modifiedIP;
	private Date modifiedTime;
    private String modifiedPlatform;	

	public VersionControlSystemTable() {}

	public VersionControlSystemTable(Long id, String code, Long totalRow, int status,
			String remarks, String createdBy, String createdIP,
			Date createdTime, String createdPlatform, String modifiedBy,
			String modifiedIP, Date modifiedTime, String modifiedPlatform) {
		super();
		this.id = id;
		this.code = code;
		this.totalRow = totalRow;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(Long totalRow) {
		this.totalRow = totalRow;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedPlatform() {
		return createdPlatform;
	}

	public void setCreatedPlatform(String createdPlatform) {
		this.createdPlatform = createdPlatform;
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

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getModifiedPlatform() {
		return modifiedPlatform;
	}

	public void setModifiedPlatform(String modifiedPlatform) {
		this.modifiedPlatform = modifiedPlatform;
	}

	@Override
	public String toString() {
		return "VersionControlSystemTable [id=" + id + ", code=" + code + ", totalRow="
				+ totalRow + ", status=" + status + ", remarks=" + remarks
				+ ", createdBy=" + createdBy + ", createdIP=" + createdIP
				+ ", createdTime=" + createdTime + ", createdPlatform="
				+ createdPlatform + ", modifiedBy=" + modifiedBy
				+ ", modifiedIP=" + modifiedIP + ", modifiedTime="
				+ modifiedTime + ", modifiedPlatform=" + modifiedPlatform + "]";
	}

}
