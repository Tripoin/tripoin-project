package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EmployeePrivateData")
public class EmployeePrivateData {
	
	@XmlElement(name = "NIK", namespace = "")
    private String nik;
	
	@XmlElement(name = "Name", namespace = "")
    private String name;
	
	@XmlElement(name = "OccupationCode", namespace = "")
    private String occupationCode;

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupationCode() {
		return occupationCode;
	}

	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nik == null) ? 0 : nik.hashCode());
		result = prime * result
				+ ((occupationCode == null) ? 0 : occupationCode.hashCode());
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
		EmployeePrivateData other = (EmployeePrivateData) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nik == null) {
			if (other.nik != null)
				return false;
		} else if (!nik.equals(other.nik))
			return false;
		if (occupationCode == null) {
			if (other.occupationCode != null)
				return false;
		} else if (!occupationCode.equals(other.occupationCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeePrivateData [nik=" + nik + ", name=" + name
				+ ", occupationCode=" + occupationCode + "]";
	}
	
}
