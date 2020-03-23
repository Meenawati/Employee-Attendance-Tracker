package com.divinisoft.project.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "vacation_detail")
public class VacationDetailDTO {

	@Id
	@GeneratedValue
	@Column(name = "vacation_detail_id")
	private int id;
	@Column(name = "Date")
	private Date date;
	@Cascade({ CascadeType.SAVE_UPDATE })
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
