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
@XmlRootElement(name = "EmployeePrivateTransferObject")
public class EmployeePrivateTransferObject extends ABaseDataTransferObject<EmployeePrivateData> {
	
	@XmlElement(name = "EmployeeParentDatas", namespace = "")
	private List<EmployeePrivateData> employeePrivateDatas;

	@Override
	public List<EmployeePrivateData> getDatas() {
		return employeePrivateDatas;
	}
	
	public List<EmployeePrivateData> getEmployeePrivateDatas() {
		return employeePrivateDatas;
	}

	public void setEmployeePrivateDatas(
			List<EmployeePrivateData> employeePrivateDatas) {
		this.employeePrivateDatas = employeePrivateDatas;
	}

	@Override
	public String toString() {
		return "EmployeePrivateTransferObject [employeePrivateDatas="
				+ employeePrivateDatas + "]";
	}

	public enum EnumFieldEmployeePrivate {
		NIK_EMPLOYE("nik"),
		NAME_EMPLOYE("name"),
		OCCUPATION_CODE("occupationcode");
		
		private String operator;		
		private EnumFieldEmployeePrivate(String operator){this.operator=operator;}
		public String toString() {return operator;}
	}
	
}
