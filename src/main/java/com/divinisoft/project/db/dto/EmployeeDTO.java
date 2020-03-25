package com.divinisoft.project.db.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.Hibernate;
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

	@Override
	public int hashCode() {
		return new HashCodeBuilder(13, 37)
				.append(this.getId())
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(Hibernate.getClass(this).equals(Hibernate.getClass(obj)))) {
			return false;
		}
		EmployeeDTO otherDto = (EmployeeDTO) obj;

		return new EqualsBuilder()
				.append(this.getId(), otherDto.getId())
				.isEquals();
	}

}
