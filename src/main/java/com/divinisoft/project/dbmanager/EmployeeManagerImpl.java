package com.divinisoft.project.dbmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divinisoft.project.db.dao.EmployeeDAO;
import com.divinisoft.project.db.dao.VacationDetailDAO;
import com.divinisoft.project.db.dao.VacationTypeDAO;
import com.divinisoft.project.db.entity.EmployeeDTO;
import com.divinisoft.project.db.entity.VacationDetailDTO;
import com.divinisoft.project.dbmanager.mapper.EmployeeMapper;
import com.divinisoft.project.dbmanager.mapper.VacationDetailMapper;
import com.divinisoft.project.model.Employee;
import com.divinisoft.project.model.VacationDetail;
import com.divinisoft.project.model.VacationSummary;

@Service
public class EmployeeManagerImpl implements EmployeeManager {
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	VacationDetailMapper vacationDetailMapper;
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@Autowired
	VacationDetailDAO vacationDetailDAO;
	
	@Autowired
	VacationTypeDAO vacationTypeDAO;

	@Override
	public void saveEmployee(Employee employee) {
		EmployeeDTO employeeDTO = this.employeeMapper.convertToDTO(employee);
		this.employeeDAO.save(employeeDTO);
	}

	@Override
	public Employee getEmployee(int employeeId) {
		EmployeeDTO employeeDTO = this.employeeDAO.getOne(employeeId);
		return this.employeeMapper.convertToModel(employeeDTO);
	}

	@Override
	public List<VacationSummary> getVacationSummary(int employeeId) {
		EmployeeDTO employeeDTO = this.employeeDAO.getOne(employeeId);
		
		Map<String, VacationSummary> map = new HashMap<String, VacationSummary>();
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
		VacationDetailDTO vacationDetailDTO = this.vacationDetailMapper.convertToDTO(vacationDetail);
		employeeDTO.getVacationDetails().add(vacationDetailDTO);
		this.employeeDAO.save(employeeDTO);
	}

	@Override
	public void saveVacations(int employeeId, List<VacationDetail> vacationDetails) {
		EmployeeDTO employeeDTO = this.employeeDAO.getOne(employeeId);
		List<VacationDetailDTO> vacationDetailDTOs = this.vacationDetailMapper.convertToDTOs(vacationDetails);
		employeeDTO.getVacationDetails().addAll(vacationDetailDTOs);
		this.employeeDAO.save(employeeDTO);
	}

	@Override
	public void cancelVacation(int employeeId, int vacationDetailId) {
		VacationDetailDTO vacationDetailDTO = this.vacationDetailDAO.getOne(vacationDetailId);
		vacationDetailDTO.setVacationType(null);
		this.vacationDetailDAO.delete(vacationDetailDTO);
	}

	@Override
	public List<VacationDetail> getVacations(int employeeId) {
		EmployeeDTO employeeDTO = this.employeeDAO.getOne(employeeId);
		return this.vacationDetailMapper.convertToModels(employeeDTO.getVacationDetails());
	}

	@Override
	public void deleteEmployee(int employeeId) {
		EmployeeDTO employeeDTO = this.employeeDAO.getOne(employeeId);
		employeeDTO.getVacationDetails().clear();
		this.employeeDAO.deleteById(employeeId);
	}

	@Override
	public List<Employee> getEmployees() {
		List<EmployeeDTO> employeeDTOs = this.employeeDAO.findAll();
		return this.employeeMapper.convertToModels(employeeDTOs);
	}

}
