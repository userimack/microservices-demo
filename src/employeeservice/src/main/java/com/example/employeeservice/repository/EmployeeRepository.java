package com.example.employeeservice.repository;

import java.util.List;

import com.example.employeeservice.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findByOrganizationId(Long organizationId);

}
