package com.capgemini.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.employee.Employee;
import com.capgemini.services.EmployeeService;

@RestController
@RequestMapping("/employees")	//Addressable Resource
public class EmployeeResource {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping	//Uniform Constraint Interface
	public void addNewEmployee(@RequestBody Employee employee) {
		service.addNewEmployee(employee);
	}
	
	@GetMapping
	//(method=RequestMethod.GET, produces= {"application/json","application/xml"})
	public List<Employee> getAllEmployees() {
		return service.getAllEmployees();
	}
	
	@PutMapping
	public void updateEmployeeDetails(@RequestBody Employee employee) {
		service.updateDetails(employee);
	}

	@DeleteMapping("/{empId}")
	public void deleteEmployee(@PathVariable int empId) {
		service.deleteEmployee(empId);
	}
}
