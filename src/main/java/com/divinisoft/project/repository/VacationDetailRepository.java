package com.divinisoft.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divinisoft.project.hibernate.entity.VacationDetailDTO;

@Repository
public interface VacationDetailRepository extends JpaRepository<VacationDetailDTO, Integer> {
}
