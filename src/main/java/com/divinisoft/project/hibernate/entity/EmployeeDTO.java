package com.divinisoft.project.hibernate.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "employee_detail")
public class EmployeeDTO {
	@Id
	@GeneratedValue
	@Column(name = "employee_id")
	private int id;
	private String name;
	private int age;
	private String department;
	private double salary;	
	@OneToMany
	@JoinColumn(name = "employee_id")
	private List<VacationDetailDTO> vacationDetails = new ArrayList<VacationDetailDTO>();
	
	public EmployeeDTO() {
	}

	

	public EmployeeDTO(String name, int age, String department, double salary) {
		super();
		this.name = name;
		this.age = age;
		this.department = department;
		this.salary = salary;
	}
	
	public List<VacationDetailDTO> getVacationDetails() {
		return vacationDetails;
	}

	public void setVacationDetails(List<VacationDetailDTO> vacationDetails) {
		this.vacationDetails = vacationDetails;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
