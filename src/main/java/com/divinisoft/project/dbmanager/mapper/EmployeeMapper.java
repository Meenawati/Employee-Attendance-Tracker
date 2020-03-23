package com.divinisoft.project.dbmanager.mapper;

import org.springframework.stereotype.Component;

import com.divinisoft.project.db.entity.EmployeeDTO;
import com.divinisoft.project.model.Employee;

@Component
public class EmployeeMapper extends AbstractMapper<EmployeeDTO, Employee> {

	@Override
	public Employee convertToModel(EmployeeDTO dto) {
		Employee employee = new Employee();
		employee.setId(dto.getId());
		employee.setName(dto.getName());
		employee.setDepartment(dto.getDepartment());
		return employee;
	}

	@Override
	public EmployeeDTO convertToDTO(Employee model) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		if (model.getId() != null) {
			employeeDTO.setId(model.getId());
		}
		employeeDTO.setName(model.getName());
		employeeDTO.setDepartment(model.getDepartment());
		return employeeDTO;
	}
}
