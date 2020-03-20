package com.divinisoft.project.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	
	private int id;
	private String name;
	private int age;
	private String department;
	private List<VacationDetail> vacationDetails = new ArrayList<VacationDetail>();
	private List<VacationSummary> vacationSummaries = new ArrayList<VacationSummary>();
	
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
	public List<VacationDetail> getVacationDetails() {
		return vacationDetails;
	}
	public void setVacationDetails(List<VacationDetail> vacationDetails) {
		this.vacationDetails = vacationDetails;
	}
	public List<VacationSummary> getVacationSummaries() {
		return vacationSummaries;
	}
	public void setVacationSummaries(List<VacationSummary> vacationSummaries) {
		this.vacationSummaries = vacationSummaries;
	}
	
	

}
