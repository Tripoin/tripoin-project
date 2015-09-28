package com.tripoin.core.dto;

import java.util.List;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class EmployeeTransferObject extends GeneralTransferObject {
	
	public EmployeeTransferObject() {
	}
	
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
