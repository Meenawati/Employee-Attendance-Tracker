package com.divinisoft.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.divinisoft.project.model.Employee;
import com.divinisoft.project.model.VacationDetail;
import com.divinisoft.project.model.VacationSummary;
import com.divinisoft.project.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	public EmployeeService employeeService;

	@RequestMapping(value = "/employees/{id}")
	public Employee getEmployee(@PathVariable("id") int id) {
		return employeeService.getEmployee(id);
	}

	@RequestMapping(value = "/employees/addEmployee", method = RequestMethod.PUT)
	public void addEmployee(@RequestBody Employee employee) {
		this.employeeService.saveEmployee(employee);
	}

	@RequestMapping(value = "/employees/{id}/vacations")
	public List<VacationDetail> getVacations(@PathVariable("id") int id) {
		return this.employeeService.getVacations(id);
	}

	@RequestMapping(value = "/employees/{id}/vacations/summary")
	public List<VacationSummary> getVacationSummary(@PathVariable("id") int id) {
		return this.employeeService.getVacationSummary(id);
	}

	@RequestMapping(value = "/employees/{id}/addVacation", method = RequestMethod.PUT)
	public void addVacation(@PathVariable("id") int id, @RequestBody VacationDetail vacation) {
		// TODO: Need vacation detail to save and not id ?
		this.employeeService.saveVacation(id, vacation);
	}

	@RequestMapping(value = "/employees/{id}/vacations", method = RequestMethod.POST)
	public void addVacations(@PathVariable("id") int id, @RequestBody List<VacationDetail> vacationDetails) {
		this.employeeService.saveVacations(id, vacationDetails);
	}

//	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
//	public void deleteEmployee(@PathVariable("id") int id) {
//		this.deleteEmployee(id);
//	}

	@RequestMapping(value = "/employees/{id}/vacations/{vacationId}", method = RequestMethod.DELETE)
	public void cancelVacation(@PathVariable("id") int id, @PathVariable("vacationId") int vacationId) {
		this.employeeService.cancelVacation(id, vacationId);
	}
}
