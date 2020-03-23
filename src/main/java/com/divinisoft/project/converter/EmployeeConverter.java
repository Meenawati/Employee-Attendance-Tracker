package com.divinisoft.project.converter;

import java.util.List;

import com.divinisoft.project.hibernate.entity.EmployeeDTO;
import com.divinisoft.project.model.Employee;
import com.divinisoft.project.model.VacationSummary;

public class EmployeeConverter extends BaseConverter<EmployeeDTO, Employee> {
	VacationDetailConverter vdConverter;

	public EmployeeConverter() {
		vdConverter = new VacationDetailConverter();
	}

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
		// TODO: what if id is null
		if (model.getId() != null) {
			employeeDTO.setId(model.getId());
		}
		employeeDTO.setName(model.getName());
		employeeDTO.setDepartment(model.getDepartment());
		return employeeDTO;
	}

	public List<VacationSummary> getVacationSummary(int employeeId) {
		// TODO:
		return null;
	}
}
