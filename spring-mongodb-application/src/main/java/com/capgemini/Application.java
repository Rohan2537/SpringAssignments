package com.capgemini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.capgemini.customer.Customer;
import com.capgemini.repository.CustomerRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Rohan", "Bhosale"));
		repository.save(new Customer("Shubham", "Bhatt"));
		repository.save(new Customer("Shubham", "Raut"));
		repository.save(new Customer("Tushar", "Deore"));
		repository.save(new Customer("Tejas", "Khandagale"));
		repository.save(new Customer("Ankita", "Ambewadikar"));
		repository.save(new Customer("Deepika", "Bacchav"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName():");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Rohan"));
		System.out.println(repository.findByFirstName("Tushar"));
		System.out.println(repository.findByFirstName("Tejas"));
		System.out.println(repository.findByFirstName("Ankita"));
		System.out.println(repository.findByFirstName("Deepika"));

		System.out.println("Customers found with findByLastName():");
		System.out.println("--------------------------------");
		System.out.println(repository.findByLastName("Bhosale"));
		System.out.println(repository.findByLastName("Bhatt"));
		System.out.println(repository.findByLastName("Raut"));
		System.out.println(repository.findByLastName("Deore"));
		System.out.println(repository.findByLastName("Khandagale"));
		System.out.println(repository.findByLastName("Ambewadikar"));
		System.out.println(repository.findByLastName("Bacchav"));

	}

}