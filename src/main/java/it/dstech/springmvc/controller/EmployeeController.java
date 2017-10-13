package it.dstech.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.dstech.springmvc.model.Employee;
import it.dstech.springmvc.service.EmployeeService;

@RestController
@RequestMapping("/")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	/**
	 * Return json model
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/getModel" }, method = RequestMethod.GET)
	public Employee getEmployeeModel() {

		return new Employee();
	}

	/**
	 * List of employee
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public List<Employee> listEmployees() {

		List<Employee> employees = service.findAllEmployee();
		return employees;
	}

	/**
	 * Return an employee by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/getEmployee/{id}" }, method = RequestMethod.GET)
	public Employee getEmployeeById(@PathVariable int id) {

		Employee employee = service.findById(id);

		return employee;
	}

	/**
	 * Save new employee
	 * 
	 * @param employee
	 */
	@RequestMapping(value = { "/save" }, method = RequestMethod.POST)
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		
		try {
			Employee saveEmployee = service.saveEmployee(employee);

			return new ResponseEntity<Employee>(saveEmployee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Employee>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Update an employee
	 * 
	 * @param employee
	 * @param id
	 */
	@RequestMapping(value = { "/update" }, method = RequestMethod.PUT)
	public Employee updateEmployee(@RequestBody Employee employee) {

		return service.updateEmployee(employee);
	}

	/**
	 * Delete an employee by id
	 * 
	 * @param id
	 */
	@RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable int id) {

		service.deleteEmployee(id);
	}

}
