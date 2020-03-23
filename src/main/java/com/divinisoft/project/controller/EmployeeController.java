package com.divinisoft.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.divinisoft.project.dbmanager.EmployeeManager;
import com.divinisoft.project.model.Employee;
import com.divinisoft.project.model.VacationDetail;
import com.divinisoft.project.model.VacationSummary;

@RestController
public class EmployeeController {

	@Autowired
	public EmployeeManager employeeManager;

	@RequestMapping(value = "/employees/{id}")
	public Employee getEmployee(@PathVariable("id") int id) {
		return employeeManager.getEmployee(id);
	}

	@RequestMapping(value = "/employees")
	public List<Employee> getEmployees() {
		return employeeManager.getEmployees();
	}

	@RequestMapping(value = "/employees/addEmployee", method = RequestMethod.PUT)
	public void addEmployee(@RequestBody Employee employee) {
		this.employeeManager.saveEmployee(employee);
	}

	@RequestMapping(value = "/employees/{id}/vacations")
	public List<VacationDetail> getVacations(@PathVariable("id") int id) {
		return this.employeeManager.getVacations(id);
	}

	@RequestMapping(value = "/employees/{id}/vacations/summary")
	public List<VacationSummary> getVacationSummary(@PathVariable("id") int id) {
		return this.employeeManager.getVacationSummary(id);
	}

	@RequestMapping(value = "/employees/{id}/addVacation", method = RequestMethod.PUT)
	public void addVacation(@PathVariable("id") int id, @RequestBody VacationDetail vacation) {
		// TODO: Need vacation detail to save and not id ?
		this.employeeManager.saveVacation(id, vacation);
	}

	@RequestMapping(value = "/employees/{id}/vacations", method = RequestMethod.POST)
	public void addVacations(@PathVariable("id") int id, @RequestBody List<VacationDetail> vacationDetails) {
		this.employeeManager.saveVacations(id, vacationDetails);
	}

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable("id") int id) {
		this.employeeManager.deleteEmployee(id);
	}

	@RequestMapping(value = "/employees/{id}/vacations/{vacationId}", method = RequestMethod.DELETE)
	public void cancelVacation(@PathVariable("id") int id, @PathVariable("vacationId") int vacationId) {
		this.employeeManager.cancelVacation(id, vacationId);
	}
}
