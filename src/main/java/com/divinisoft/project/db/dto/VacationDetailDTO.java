package com.divinisoft.project.db.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "vacation_detail")
public class VacationDetailDTO {

	@Id
	@GeneratedValue
	@Column(name = "vacation_detail_id")
	private int id;
	
	@Column(name = "date")
	private Date date;
	
	@Cascade({ CascadeType.ALL })
	@ManyToOne
	@JoinColumn(name = "vacation_id")
	private VacationTypeDTO vacationType;
	
	public VacationDetailDTO() {
		
	}

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

	@Override
	public int hashCode() {
		return new HashCodeBuilder(13, 37)
				.append(this.getVacationType())
				.append(this.getDate())
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
		VacationDetailDTO otherDto = (VacationDetailDTO) obj;

		return new EqualsBuilder()
				.append(this.getVacationType(), otherDto.getVacationType())
				.append(this.getDate(), otherDto.getDate())
				.isEquals();
	}

}
