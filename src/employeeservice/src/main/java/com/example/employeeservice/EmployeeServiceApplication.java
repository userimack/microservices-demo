package com.example.employeeservice;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class EmployeeServiceApplication implements CommandLineRunner {
	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		employeeRepository.deleteAll();

		Employee john = new Employee(1L, 1L, "John Doe", 35, "Manager");
		Employee jane = new Employee(1L, 2L, "Jane Doe", 33, "Director");

		employeeRepository.save(john);
		employeeRepository.save(jane);
	}
}
