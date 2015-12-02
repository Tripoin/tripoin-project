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
public class EmployeeTransferObject extends GeneralTransferObject {
	
	@XmlElement(name = "EmployeeDatas", namespace = "")
	private List<EmployeeData> employeeDatas;

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
	
}
