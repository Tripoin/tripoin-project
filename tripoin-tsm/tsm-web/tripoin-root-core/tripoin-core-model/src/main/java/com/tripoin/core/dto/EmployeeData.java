package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tripoin.core.pojo.Employee;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EmployeeData")
public class EmployeeData extends AGeneralAuditTrailData {
	
	@XmlElement(name = "NIK", namespace = "")
    private String nik;
	
	@XmlElement(name = "OccupationData", namespace = "")
    private OccupationData occupationData;
	
	@XmlElement(name = "ProfileData", namespace = "")
    private ProfileData profileData;
	
	@XmlElement(name = "AreaData", namespace = "")
    private AreaData areaData;
	
	@XmlElement(name = "EmployeeParentData", namespace = "")
    private EmployeePrivateData employeeParentData;
	
	public EmployeeData() {}
	
	public EmployeeData(Employee employee) {
		super(employee);
		if(employee != null){
			this.nik = employee.getNik();
			this.occupationData = new OccupationData(employee.getOccupation());
			if(employee.getArea() != null)
				this.areaData = new AreaData(employee.getArea());	
		}
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public OccupationData getOccupationData() {
		return occupationData;
	}

	public void setOccupationData(OccupationData occupationData) {
		this.occupationData = occupationData;
	}

	public ProfileData getProfileData() {
		return profileData;
	}

	public void setProfileData(ProfileData profileData) {
		this.profileData = profileData;
	}

	public AreaData getAreaData() {
		return areaData;
	}

	public void setAreaData(AreaData areaData) {
		this.areaData = areaData;
	}

	public EmployeePrivateData getEmployeeParentData() {
		return employeeParentData;
	}

	public void setEmployeeParentData(EmployeePrivateData employeePrivateData) {
		this.employeeParentData = employeePrivateData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((areaData == null) ? 0 : areaData.hashCode());
		result = prime
				* result
				+ ((employeeParentData == null) ? 0 : employeeParentData
						.hashCode());
		result = prime * result + ((nik == null) ? 0 : nik.hashCode());
		result = prime * result
				+ ((occupationData == null) ? 0 : occupationData.hashCode());
		result = prime * result
				+ ((profileData == null) ? 0 : profileData.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeData other = (EmployeeData) obj;
		if (areaData == null) {
			if (other.areaData != null)
				return false;
		} else if (!areaData.equals(other.areaData))
			return false;
		if (employeeParentData == null) {
			if (other.employeeParentData != null)
				return false;
		} else if (!employeeParentData.equals(other.employeeParentData))
			return false;
		if (nik == null) {
			if (other.nik != null)
				return false;
		} else if (!nik.equals(other.nik))
			return false;
		if (occupationData == null) {
			if (other.occupationData != null)
				return false;
		} else if (!occupationData.equals(other.occupationData))
			return false;
		if (profileData == null) {
			if (other.profileData != null)
				return false;
		} else if (!profileData.equals(other.profileData))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeData [nik=" + nik + ", occupationData="
				+ occupationData + ", profileData=" + profileData
				+ ", areaData=" + areaData + ", employeeParentData="
				+ employeeParentData + "]";
	}
	
}
