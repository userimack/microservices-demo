package com.example.employeeservice.controller;

import java.util.List;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static net.logstash.logback.argument.StructuredArguments.keyValue;


@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository repository;

    @PostMapping("/")
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee, keyValue("path", "/"));

        return repository.save(employee);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") String id) {
        LOGGER.info("Employee find: {}", keyValue("id", id), keyValue("path", "/id"));
        return repository.findById(id).get();
    }

    @GetMapping("/")
    public Iterable<Employee> findAll() {
        LOGGER.info("Employee find", keyValue("path", "/"));
        return repository.findAll();
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") String departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId, keyValue("path", "/department/{departmentId}"));
        return repository.findByDepartmentId(departmentId);
    }

    @GetMapping("/organization/{organizationId}")
    public List<Employee> findByOrganization(@PathVariable("organizationId") String organizationId) {
        LOGGER.info("Employee find: organizationId={}", organizationId, keyValue("path", "/organization/{organizationId}"));
        return repository.findByOrganizationId(organizationId);
    }

}