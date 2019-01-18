package com.capgemini.employee;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.capgemini.address.Address;

@Entity
public class Employee {
	@Id
	private int empId;
	private String empName;
	private double salary;

	public Employee() {
		
	}
	public Employee(int empId, String empName, double salary, Address address) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.salary = salary;
		this.address = address;
	}

	@Embedded
	private Address address;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", salary=" + salary + ", address=" + address
				+ "]";
	}

}
