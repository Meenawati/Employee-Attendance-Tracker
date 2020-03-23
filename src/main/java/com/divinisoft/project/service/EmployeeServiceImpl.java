package com.divinisoft.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divinisoft.project.converter.EmployeeConverter;
import com.divinisoft.project.converter.VacationDetailConverter;
import com.divinisoft.project.hibernate.entity.EmployeeDTO;
import com.divinisoft.project.hibernate.entity.VacationDetailDTO;
import com.divinisoft.project.model.Employee;
import com.divinisoft.project.model.VacationDetail;
import com.divinisoft.project.model.VacationSummary;
import com.divinisoft.project.repository.EmployeeRepository;
import com.divinisoft.project.repository.VacationDetailRepository;

@Component(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	EmployeeConverter employeeConverter;
	VacationDetailConverter vacationDetailConverter;
	@Autowired
	EmployeeRepository employeeDAO;
	@Autowired
	VacationDetailRepository vacationDetailRepository;

	public EmployeeServiceImpl() {
		this.employeeConverter = new EmployeeConverter();
		this.vacationDetailConverter = new VacationDetailConverter();
	}

	@Override
	public void saveEmployee(Employee employee) {
		EmployeeDTO employeeDTO = this.employeeConverter.convertToDTO(employee);
		this.employeeDAO.save(employeeDTO);
	}

	@Override
	public Employee getEmployee(int employeeId) {
		EmployeeDTO employeeDTO = this.employeeDAO.getOne(employeeId);
		return this.employeeConverter.convertToModel(employeeDTO);
	}

	@Override
	public List<VacationSummary> getVacationSummary(int employeeId) {
		Map<String, VacationSummary> map = new HashMap<String, VacationSummary>();
		EmployeeDTO employeeDTO = this.employeeDAO.getOne(employeeId);
		List<VacationDetailDTO> vaDetailDTOs = employeeDTO.getVacationDetails();
		for (VacationDetailDTO vDetailDTO : vaDetailDTOs) {
			String vacationType = vDetailDTO.getVacationType().getVacationType();
			VacationSummary vSummary;
			if (!map.keySet().contains(vacationType)) {
				vSummary = new VacationSummary();
				vSummary.setVacationType(vacationType);
				vSummary.setTotalDays(vDetailDTO.getVacationType().getDays());
				vSummary.setDaysTaken(1);
				map.put(vacationType, vSummary);
			} else {
				vSummary = map.get(vacationType);
				vSummary.setDaysTaken(vSummary.getDaysTaken() + 1);
				map.replace(vacationType, vSummary);
			}
		}
		return new ArrayList<>(map.values());
	}

	@Override
	public void saveVacation(int employeeId, VacationDetail vacationDetail) {
		EmployeeDTO employeeDTO = this.employeeDAO.getOne(employeeId);
		VacationDetailDTO vacationDetailDTO = this.vacationDetailConverter.convertToDTO(vacationDetail);
		employeeDTO.getVacationDetails().add(vacationDetailDTO);
		this.employeeDAO.save(employeeDTO);
	}

	@Override
	public void saveVacations(int employeeId, List<VacationDetail> vacationDetails) {
		EmployeeDTO employeeDTO = this.employeeDAO.getOne(employeeId);
		List<VacationDetailDTO> vacationDetailDTOs = this.vacationDetailConverter.convertToDTOs(vacationDetails);
		employeeDTO.getVacationDetails().addAll(vacationDetailDTOs);
		this.employeeDAO.save(employeeDTO);
	}

	@Override
	public void cancelVacation(int employeeId, int vacationDetailId) {
		this.vacationDetailRepository.deleteById(vacationDetailId);
	}

	@Override
	public List<VacationDetail> getVacations(int employeeId) {
		EmployeeDTO employeeDTO = this.employeeDAO.getOne(employeeId);
		return this.vacationDetailConverter.convertToModels(employeeDTO.getVacationDetails());
	}

}
