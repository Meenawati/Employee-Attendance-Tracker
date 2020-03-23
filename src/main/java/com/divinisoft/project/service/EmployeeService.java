package com.divinisoft.project.service;

import java.util.List;

import com.divinisoft.project.model.Employee;
import com.divinisoft.project.model.VacationDetail;
import com.divinisoft.project.model.VacationSummary;

public interface EmployeeService {
	public void saveEmployee(Employee user);

	public Employee getEmployee(int employeeId);

	public List<VacationSummary> getVacationSummary(int employeeId);

	public List<VacationDetail> getVacations(int employeeId);

	public void saveVacation(int employeeId, VacationDetail vacationDetail);

	public void saveVacations(int employeeId, List<VacationDetail> vacationDetails);

	public void cancelVacation(int employeeId, int vacationDetailId);
}
