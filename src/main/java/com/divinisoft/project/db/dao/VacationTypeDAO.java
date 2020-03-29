package com.divinisoft.project.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.divinisoft.project.db.dto.VacationTypeDTO;

@Repository
public interface VacationTypeDAO extends JpaRepository<VacationTypeDTO, Integer> {
	@Query("select v from vacation_type v where v.vacationType = ?1")
	VacationTypeDTO findByName(String vacationType);
}
