package com.divinisoft.project.hibernate.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "employee_detail")
public class EmployeeDTO {
	@Id
	@GeneratedValue
	@Column(name = "employee_id")
	private int id;
	private String name;
	private String department;
	@Cascade({ CascadeType.ALL })
	@OneToMany
	@JoinColumn(name = "employee_id")
	private List<VacationDetailDTO> vacationDetails = new ArrayList<VacationDetailDTO>();

	public EmployeeDTO() {
	}

	public EmployeeDTO(String name, String department) {
		super();
		this.name = name;
		this.department = department;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
