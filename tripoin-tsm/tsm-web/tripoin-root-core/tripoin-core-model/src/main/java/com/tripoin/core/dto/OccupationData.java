package com.tripoin.core.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.pojo.Occupation;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OccupationData")
public class OccupationData {

	@XmlElement(name = "ID", namespace = "")
	private Integer id;

	@XmlElement(name = "Code", namespace = "")
	private String code;

	@XmlElement(name = "Name", namespace = "")
	private String name;

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

	public OccupationData() {}

	public OccupationData(Occupation occupation) {
		this.id = occupation.getId();
		this.code = occupation.getCode();
		this.name = occupation.getName();
		this.status = occupation.getStatus();
		this.remarks = occupation.getRemarks();
		this.createdBy = occupation.getCreatedBy();
		this.createdIP = occupation.getCreatedIP();
		if(occupation.getCreatedTime() != null)
			this.createdTime = ParameterConstant.FORMAT_DEFAULT.format(occupation.getCreatedTime());
		this.createdPlatform = occupation.getCreatedPlatform();
		this.modifiedBy = occupation.getModifiedBy();
		this.modifiedIP = occupation.getModifiedIP();
		if(occupation.getModifiedTime() != null)
			this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.format(occupation.getModifiedTime());
		this.modifiedPlatform = occupation.getModifiedPlatform();
	}
	
	public OccupationData(Integer id, String code, String name, Integer status,
			String remarks, String createdBy, String createdIP,
			Date createdTime, String createdPlatform, String modifiedBy,
			String modifiedIP, Date modifiedTime, String modifiedPlatform) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
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
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdIP == null) ? 0 : createdIP.hashCode());
		result = prime * result
				+ ((createdPlatform == null) ? 0 : createdPlatform.hashCode());
		result = prime * result
				+ ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedIP == null) ? 0 : modifiedIP.hashCode());
		result = prime
				* result
				+ ((modifiedPlatform == null) ? 0 : modifiedPlatform.hashCode());
		result = prime * result
				+ ((modifiedTime == null) ? 0 : modifiedTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		OccupationData other = (OccupationData) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		return "OccupationData [id=" + id + ", code=" + code + ", name=" + name
				+ ", status=" + status + ", remarks=" + remarks
				+ ", createdBy=" + createdBy + ", createdIP=" + createdIP
				+ ", createdTime=" + createdTime + ", createdPlatform="
				+ createdPlatform + ", modifiedBy=" + modifiedBy
				+ ", modifiedIP=" + modifiedIP + ", modifiedTime="
				+ modifiedTime + ", modifiedPlatform=" + modifiedPlatform + "]";
	}

}
