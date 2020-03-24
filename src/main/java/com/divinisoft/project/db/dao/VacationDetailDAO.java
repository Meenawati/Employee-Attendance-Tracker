package com.divinisoft.project.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divinisoft.project.db.dto.VacationDetailDTO;

@Repository
public interface VacationDetailDAO extends JpaRepository<VacationDetailDTO, Integer> {
}
