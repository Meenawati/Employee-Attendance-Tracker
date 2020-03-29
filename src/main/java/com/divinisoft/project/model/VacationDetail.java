package com.divinisoft.project.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VacationDetail implements Comparable<VacationDetail> {

	private Integer vacationDetailId;
	@NotBlank(message = "vacation type cannot be null or empty")
	private String vacationType;
	@NotNull(message = "vacation date cannot be null")
	private String date;

	public String getVacationType() {
		return vacationType;
	}

	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getVacationDetailId() {
		return this.vacationDetailId;
	}

	public void setVacationDetailId(int vacationId) {
		this.vacationDetailId = vacationId;
	}

	@Override
	public int compareTo(VacationDetail vacationDetail) {
		return vacationDetail.getDate().compareTo(this.getDate());
	}
}
