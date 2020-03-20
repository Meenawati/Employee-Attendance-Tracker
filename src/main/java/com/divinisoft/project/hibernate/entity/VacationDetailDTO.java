package com.divinisoft.project.hibernate.entity;

import java.util.Date;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NaturalId;

@Entity(name = "vacation_detail")
public class VacationDetailDTO {
	
	@Id
	@GeneratedValue
	@Column(name = "vacation_detail_id")
	private int id;
	@Column(name = "Date")
	private Date date;
	@ManyToOne
	@JoinColumn(name = "vacation_id")
	private VacationTypeDTO vacationType;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	

	public void setDate(Date date) {
		this.date = date;
	}

	public VacationTypeDTO getVacationType() {
		return vacationType;
	}

	public void setVacationType(VacationTypeDTO vacationType) {
		this.vacationType = vacationType;
	}

}
