package com.tripoin.core.dto;

import com.tripoin.core.pojo.Employee;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class EmployeeData {

	private Integer id;
	private String name;
	
	public EmployeeData() {}	

	public EmployeeData(Employee availability) {
		super();
		this.id = availability.getId();
		this.name = availability.getName();
	}

	public EmployeeData(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee Data [id=" + id + ", name=" + name + "]";
	}
	
}
