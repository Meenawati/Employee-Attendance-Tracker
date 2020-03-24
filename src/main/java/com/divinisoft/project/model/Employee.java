package com.divinisoft.project.model;

import javax.validation.constraints.NotBlank;

public class Employee {

	private Integer id;
	@NotBlank(message = "employee name cannot be null or empty")
	private String name;
	private String department;

	public Integer getId() {
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
