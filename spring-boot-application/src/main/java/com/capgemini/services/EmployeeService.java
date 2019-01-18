package com.capgemini.services;

import java.util.List;

import com.capgemini.employee.Employee;

public interface EmployeeService {

	void addNewEmployee(Employee employee);
	List<Employee> getAllEmployees();
	void updateDetails(Employee employee);
	void deleteEmployee(int empId);

}