package com.tripoin.core.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.pojo.Employee;
import com.tripoin.core.pojo.Occupation;
import com.tripoin.core.pojo.Profile;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EmployeeData")
public class EmployeeData {

	@XmlElement(name = "ID", namespace = "")
	private Integer id;
	
	@XmlElement(name = "Code", namespace = "")
    private String code;
	
	@XmlElement(name = "NIK", namespace = "")
    private String nik;
	
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
	
	@XmlElement(name = "ProfileData", namespace = "")
    private ProfileData profileData;
	
	@XmlElement(name = "OccupationData", namespace = "")
    private OccupationData occupationData;
	
	@XmlElement(name = "EmployeeDataParent", namespace = "")
    private EmployeeData employeeDataParent;
	
	public EmployeeData() {}
	
	public EmployeeData(Employee employee) {
		super();
		this.id = employee.getId();
		this.code = employee.getCode();
		this.nik = employee.getNik();
		this.status = employee.getStatus();
		this.remarks = employee.getRemarks();
		this.createdBy = employee.getCreatedBy();
		this.createdIP = employee.getCreatedIP();
		if(employee.getCreatedTime() != null)
			this.createdTime = ParameterConstant.FORMAT_DEFAULT.format(employee.getCreatedTime());
		this.createdPlatform = employee.getCreatedPlatform();
		this.modifiedBy = employee.getModifiedBy();
		this.modifiedIP = employee.getModifiedIP();
		if(employee.getModifiedTime() != null)
			this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.format(employee.getModifiedTime());
		this.modifiedPlatform = employee.getModifiedPlatform();
		this.profileData = new ProfileData(employee.getProfile());
		this.occupationData = new OccupationData(employee.getOccupation());
		if(employee.getEmployeeParent() != null)
			this.employeeDataParent = new EmployeeData(employee.getEmployeeParent());
	}
	
	public EmployeeData(Integer id, String code, String nik, Integer status,
			String remarks, String createdBy, String createdIP,
			Date createdTime, String createdPlatform, String modifiedBy,
			String modifiedIP, Date modifiedTime, String modifiedPlatform,
			Profile profile, Occupation occupation, Employee employeeParent) {
		super();
		this.id = id;
		this.code = code;
		this.nik = nik;
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
		this.profileData = new ProfileData(profile);
		this.occupationData = new OccupationData(occupation);
		if(employeeParent != null)
			this.employeeDataParent = new EmployeeData(employeeParent);
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

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
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

	public ProfileData getProfileData() {
		return profileData;
	}

	public void setProfileData(ProfileData profileData) {
		this.profileData = profileData;
	}

	public OccupationData getOccupationData() {
		return occupationData;
	}

	public void setOccupationData(OccupationData occupationData) {
		this.occupationData = occupationData;
	}

	public EmployeeData getEmployeeDataParent() {
		return employeeDataParent;
	}

	public void setEmployeeDataParent(EmployeeData employeeDataParent) {
		this.employeeDataParent = employeeDataParent;
	}

	@Override
	public String toString() {
		return "EmployeeData [id=" + id + ", code=" + code + ", nik=" + nik
				+ ", status=" + status + ", remarks=" + remarks
				+ ", createdBy=" + createdBy + ", createdIP=" + createdIP
				+ ", createdTime=" + createdTime + ", createdPlatform="
				+ createdPlatform + ", modifiedBy=" + modifiedBy
				+ ", modifiedIP=" + modifiedIP + ", modifiedTime="
				+ modifiedTime + ", modifiedPlatform=" + modifiedPlatform
				+ ", profileData=" + profileData + ", occupationData="
				+ occupationData + ", employeeDataParent=" + employeeDataParent
				+ "]";
	}
	
}
