package com.divinisoft.project.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "vacation_type")
public class VacationTypeDTO {

	@Id
	@GeneratedValue
	@Column(name = "vacation_id")
	private int vacationId;
	@Column(name = "vacation_type")
	private String vacationType;
	@Column(name = "total_days")
	private int days;

	public VacationTypeDTO() {

	}

	public VacationTypeDTO(String vacationType, int days) {
		this.vacationType = vacationType;
		this.days = days;
	}

	public int getVacationId() {
		return vacationId;
	}

	public void setVacationId(int vacationId) {
		this.vacationId = vacationId;
	}

	public String getVacationType() {
		return vacationType;
	}

	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

}
