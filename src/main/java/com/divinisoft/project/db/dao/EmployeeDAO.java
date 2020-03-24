package com.divinisoft.project.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divinisoft.project.db.dto.EmployeeDTO;

@Repository
public interface EmployeeDAO extends JpaRepository<EmployeeDTO, Integer> {
}
