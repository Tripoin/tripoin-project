package com.tripoin.core.dto;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EmployeeTransferObject")
public class EmployeeTransferObject extends GeneralPagingTransferObject {
	
	@XmlElement(name = "EmployeeDatas", namespace = "")
	private List<EmployeeData> employeeDatas;
	
	@XmlElement(name = "FindEmployeeData", namespace = "")
	private Map<String, Object> findEmployeeData;

	public List<EmployeeData> getEmployeeDatas() {
		return employeeDatas;
	}

	public void setEmployeeDatas(List<EmployeeData> employeeDatas) {
		this.employeeDatas = employeeDatas;
	}

	public Map<String, Object> getFindEmployeeData() {
		return findEmployeeData;
	}

	public void setFindEmployeeData(Map<String, Object> findEmployeeData) {
		this.findEmployeeData = findEmployeeData;
	}

	@Override
	public String toString() {
		return "EmployeeTransferObject [employeeDatas=" + employeeDatas
				+ ", findEmployeeData=" + findEmployeeData + "]";
	}
	
	public enum EnumFieldEmployee {
		NIK_EMPLOYE("nik"),
		NAME_EMPLOYE("profile.name"),
		OCCUPATION_EMPLOYE("occupation.name"),
		NAME_PARENT_EMPLOYE("employeeParent.profile.name");
		
		private String operator;		
		private EnumFieldEmployee(String operator){this.operator=operator;}
		public String toString() {return operator;}
	}
	
}
