package com.divinisoft.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.divinisoft.project.hibernate.entity.VacationTypeDTO;

@Repository
public interface VacationTypeRepository extends JpaRepository<VacationTypeDTO, Integer> {
	@Query("select v from vacation_type v where v.vacationType = ?1")
	List<VacationTypeDTO> findByName(String vacationType);
}
