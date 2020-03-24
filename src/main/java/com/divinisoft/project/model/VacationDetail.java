package com.divinisoft.project.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VacationDetail {

	private Integer vacationId;
	@NotBlank(message = "vacation type cannot be null or empty")
	private String vacationType;
	@NotNull(message = "vacation date cannot be null")
	private Date date;

	public String getVacationType() {
		return vacationType;
	}

	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getVacationId() {
		return vacationId;
	}

	public void setVacationId(int vacationId) {
		this.vacationId = vacationId;
	}
}
