package com.capgemini.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.employee.Employee;
import com.capgemini.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public void addNewEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void updateDetails(Employee employee) {
		employeeRepository.save(employee);
		
	}

	@Override
	public void deleteEmployee(int empId) {
		Employee employee = employeeRepository.getOne(empId);
		employeeRepository.delete(employee);
	}

}
