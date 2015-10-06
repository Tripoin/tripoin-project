package com.tripoin.core.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tripoin.core.dto.EmployeeData;

@Table(name="mst_employee")
@Entity
public class Employee {

	public Employee() {
	}
	
	public Employee(EmployeeData employeeData) {
		if(employeeData.getId() != null){
			this.id = employeeData.getId();
			this.name = employeeData.getName();
		}
	}
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
		return "Employee [id=" + id + ", name=" + name + "]";
	}
		
}
