package com.tripoin.core.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EmployeeTransferObject")
public class EmployeeTransferObject extends ABaseDataTransferObject<EmployeeData> {
	
	@XmlElement(name = "EmployeeDatas", namespace = "")
	private List<EmployeeData> employeeDatas;

	@Override
	public List<EmployeeData> getDatas() {
		return employeeDatas;
	}
	
	public List<EmployeeData> getEmployeeDatas() {
		return employeeDatas;
	}

	public void setEmployeeDatas(List<EmployeeData> employeeDatas) {
		this.employeeDatas = employeeDatas;
	}

	@Override
	public String toString() {
		return "EmployeeTransferObject [employeeDatas=" + employeeDatas + "]";
	}
	
	public enum EnumFieldEmployee {
		NIK_EMPLOYE("nik"),
		NAME_EMPLOYE("profile.name"),
		OCCUPATION("occupation"),
		OCCUPATION_NAME("occupation.name"),
		OCCUPATION_CODE("occupation.code"),
		AREA("area"),
		AREA_NAME("area.name"),
		AREA_CODE("area.code"),
		ROLE_EMPLOYE("profile.user.role.code"),
		PARENT_EMPLOYE("employeeParent"),
		NAME_PARENT_EMPLOYE("employeeParent.profile.name"),
		NIK_PARENT_EMPLOYE("employeeParent.nik"),
		USERNAME_EMPLOYE("profile.user.username"),
		BIRTHPLACE_EMPLOYE("profile.birthplace"),
		BIRTHDATE_EMPLOYE("profile.birthdate"),
		GENDER_EMPLOYE("profile.gender"),
		TELP_EMPLOYE("profile.telp"),
		PHONE_EMPLOYE("profile.phone"),
		EMAIL_EMPLOYE("profile.email"),
		ADDRESS_EMPLOYE("profile.address"),
		ENABLE_EMPLOYE("profile.user.enabled");
		
		
		private String operator;		
		private EnumFieldEmployee(String operator){this.operator=operator;}
		public String toString() {return operator;}
	}
	
}
