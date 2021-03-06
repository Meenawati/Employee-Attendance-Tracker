package com.divinisoft.project.db.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.divinisoft.project.db.dto.VacationDetailDTO;

@Repository
public interface VacationDetailDAO extends JpaRepository<VacationDetailDTO, Integer> {
	@Query("select v from vacation_detail v where v.date = ?1")
	VacationDetailDTO findByDate(Date date);
	
	@Query("select v from vacation_detail v where v.vacationType.vacationType = ?1")
	List<VacationDetailDTO> findByVacationType(String vacationType);
}
