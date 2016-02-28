package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.pojo.AGeneralAuditTrail;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AGeneralAuditTrailData")
public class AGeneralAuditTrailData {

	@XmlElement(name = "Status", namespace = "")
	private Integer status;

	@XmlElement(name = "Remarks", namespace = "")
	private String remarks;
	
	@XmlElement(name = "CreatedBy", namespace = "")
    private String createdBy;
	
	@XmlElement(name = "CreatedIP", namespace = "")
    private String createdIP;
	
	@XmlElement(name = "CreatedTime", namespace = "")
    private String createdTime;
	
	@XmlElement(name = "CreatedPlatform", namespace = "")
    private String createdPlatform;
	
	@XmlElement(name = "ModifiedBy", namespace = "")
    private String modifiedBy;
	
	@XmlElement(name = "ModifiedIP", namespace = "")
    private String modifiedIP;
	
	@XmlElement(name = "ModifiedTime", namespace = "")
    private String modifiedTime;
	
	@XmlElement(name = "ModifiedPlatform", namespace = "")
    private String modifiedPlatform;

	public AGeneralAuditTrailData() {}

	public AGeneralAuditTrailData(AGeneralAuditTrail aGeneralAuditTrail) {
		this.status = aGeneralAuditTrail.getStatus();
		this.remarks = aGeneralAuditTrail.getRemarks();
		this.createdBy = aGeneralAuditTrail.getCreatedBy();
		this.createdIP = aGeneralAuditTrail.getCreatedIP();
		if(aGeneralAuditTrail.getCreatedTime() != null)
			this.createdTime = ParameterConstant.FORMAT_DEFAULT.format(aGeneralAuditTrail.getCreatedTime());
		this.createdPlatform = aGeneralAuditTrail.getCreatedPlatform();
		this.modifiedBy = aGeneralAuditTrail.getModifiedBy();
		this.modifiedIP = aGeneralAuditTrail.getModifiedIP();
		if(aGeneralAuditTrail.getModifiedTime() != null)
			this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.format(aGeneralAuditTrail.getModifiedTime());
		this.modifiedPlatform = aGeneralAuditTrail.getModifiedPlatform();
	}
	
	public AGeneralAuditTrailData(Integer id, String code, String name, Integer status,
			String remarks, String createdBy, String createdIP,
			java.sql.Date createdTime, String createdPlatform, String modifiedBy,
			String modifiedIP, java.sql.Date modifiedTime, String modifiedPlatform) {
		super();
		this.status = status;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdIP = createdIP;
		if(createdTime != null)
			this.createdTime = ParameterConstant.FORMAT_DEFAULT.format(createdTime);
		this.createdPlatform = createdPlatform;
		this.modifiedBy = modifiedBy;
		this.modifiedIP = modifiedIP;
		if(modifiedTime != null)
			this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.format(modifiedTime);
		this.modifiedPlatform = modifiedPlatform;
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

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getModifiedPlatform() {
		return modifiedPlatform;
	}

	public void setModifiedPlatform(String modifiedPlatform) {
		this.modifiedPlatform = modifiedPlatform;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdIP == null) ? 0 : createdIP.hashCode());
		result = prime * result + ((createdPlatform == null) ? 0 : createdPlatform.hashCode());
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result + ((modifiedIP == null) ? 0 : modifiedIP.hashCode());
		result = prime * result + ((modifiedPlatform == null) ? 0 : modifiedPlatform.hashCode());
		result = prime * result + ((modifiedTime == null) ? 0 : modifiedTime.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		AGeneralAuditTrailData other = (AGeneralAuditTrailData) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdIP == null) {
			if (other.createdIP != null)
				return false;
		} else if (!createdIP.equals(other.createdIP))
			return false;
		if (createdPlatform == null) {
			if (other.createdPlatform != null)
				return false;
		} else if (!createdPlatform.equals(other.createdPlatform))
			return false;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (modifiedIP == null) {
			if (other.modifiedIP != null)
				return false;
		} else if (!modifiedIP.equals(other.modifiedIP))
			return false;
		if (modifiedPlatform == null) {
			if (other.modifiedPlatform != null)
				return false;
		} else if (!modifiedPlatform.equals(other.modifiedPlatform))
			return false;
		if (modifiedTime == null) {
			if (other.modifiedTime != null)
				return false;
		} else if (!modifiedTime.equals(other.modifiedTime))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AGeneralAuditTrailData [status=" + status + ", remarks=" + remarks + ", createdBy=" + createdBy
				+ ", createdIP=" + createdIP + ", createdTime=" + createdTime + ", createdPlatform=" + createdPlatform
				+ ", modifiedBy=" + modifiedBy + ", modifiedIP=" + modifiedIP + ", modifiedTime=" + modifiedTime
				+ ", modifiedPlatform=" + modifiedPlatform + "]";
	}

}
