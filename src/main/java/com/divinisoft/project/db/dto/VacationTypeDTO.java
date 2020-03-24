package com.divinisoft.project.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.Hibernate;

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

	@Override
	public int hashCode() {
		return new HashCodeBuilder(13, 37)
				.append(this.getVacationType())
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
		VacationTypeDTO otherDto = (VacationTypeDTO) obj;

		return new EqualsBuilder()
				.append(this.getVacationType(), otherDto.getVacationType())
				.isEquals();
	}

}
