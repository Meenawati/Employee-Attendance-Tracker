package com.divinisoft.project.dbmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divinisoft.project.db.dao.EmployeeDAO;
import com.divinisoft.project.db.dao.VacationDetailDAO;
import com.divinisoft.project.db.dao.VacationTypeDAO;
import com.divinisoft.project.db.dto.EmployeeDTO;
import com.divinisoft.project.db.dto.VacationDetailDTO;
import com.divinisoft.project.dbmanager.mapper.EmployeeMapper;
import com.divinisoft.project.dbmanager.mapper.VacationDetailMapper;
import com.divinisoft.project.model.Employee;
import com.divinisoft.project.model.VacationDetail;
import com.divinisoft.project.model.VacationSummary;
import com.divinisoft.project.utility.DateUtility;

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
			String dateString = DateUtility.formatDateToString(vDetailDTO.getDate());

			// get all vacations for the current year
			if (dateString.compareTo(DateUtility.getStartDateStringOfCurrentYear()) >= 0) {
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
		}

		List<VacationSummary> vacationSummaries = new ArrayList<>(map.values());
		Collections.sort(vacationSummaries);
		return vacationSummaries;
	}

	@Override
	public void saveVacation(int employeeId, VacationDetail vacationDetail) {
		this.throwErrorIfVacationNotAvailable(employeeId, vacationDetail.getVacationType());
		EmployeeDTO employeeDTO = this.employeeDAO.getOne(employeeId);
		VacationDetailDTO existingVacation = this.vacationDetailDAO.getOne(vacationDetail.getVacationDetailId());
		VacationDetailDTO updatedVacation = this.vacationDetailMapper.convertToDTO(vacationDetail);
		employeeDTO.getVacationDetails().remove(existingVacation);
		employeeDTO.getVacationDetails().add(updatedVacation);
		this.employeeDAO.save(employeeDTO);
	}

	@Override
	public void saveVacations(int employeeId, List<VacationDetail> vacationDetails) {
		EmployeeDTO employeeDTO = this.employeeDAO.getOne(employeeId);
		List<VacationDetailDTO> vacationDetailDTOs = new ArrayList<>();
		for(VacationDetail vDetail : vacationDetails) {
			this.throwErrorIfVacationNotAvailable(employeeId, vDetail.getVacationType());
			VacationDetailDTO vDetailDTO = this.vacationDetailMapper.convertToDTO(vDetail);
			vacationDetailDTOs.add(vDetailDTO);
		}
		
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
		List<VacationDetail> vDetails = this.vacationDetailMapper.convertToModels(employeeDTO.getVacationDetails());
		Collections.sort(vDetails);
		return vDetails;
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

	public void throwErrorIfVacationNotAvailable(int employeeId, String vacationType) {
		boolean isVacationAvailable = true;
		if (vacationType.equals("PARENTAL") || vacationType.equals("MARRIAGE")) {
			isVacationAvailable = this.isVacationAvailableForParentalAndMarriage(vacationType);
		} else {
			List<VacationSummary> vacationSummaries = this.getVacationSummary(employeeId);
			for (VacationSummary vSummary : vacationSummaries) {
				if (vSummary.getVacationType().equalsIgnoreCase(vacationType)) {
					isVacationAvailable = vSummary.getDaysTaken() < vSummary.getTotalDays();
				}
			}
		}
		if (!isVacationAvailable) {
			throw new IllegalArgumentException("All vacations are already taken for vacation type: " + vacationType);
		}
	}
	
	public boolean isVacationAvailableForParentalAndMarriage(String vacationType) {
		List<VacationDetailDTO> vDetailDTOs = this.vacationDetailDAO.findByVacationType(vacationType);
		return vDetailDTOs.size() < 5;
	}
}
