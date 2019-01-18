package com.capgemini;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.capgemini.address.Address;
import com.capgemini.employee.Employee;
import com.capgemini.repository.EmployeeRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public CommandLineRunner populateData(EmployeeRepository repository) {
		return (args)->{
			repository.save(new Employee(100, "Shubham Bhatt", 2000, new Address("Maharashtra", "Pune", "411031")));	
			repository.save(new Employee(101, "Rohan Bhosale", 3000, new Address("Maharashtra", "Pune", "411032")));	
			repository.save(new Employee(102, "Shubham Raut", 4000, new Address("Maharashtra", "Pune", "411033")));	
			repository.save(new Employee(103, "Tushar Deore", 5000, new Address("Maharashtra", "Pune", "411034")));	
			repository.save(new Employee(104, "Tejes khandagale", 6000, new Address("Maharashtra", "Pune", "411035")));	
		};
		
	}

}
