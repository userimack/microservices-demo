package com.example.organizationservice.client;

import com.example.organizationservice.model.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "department", url = "${feign.department.url}")
public interface DepartmentClient {

	@GetMapping("/organization/{organizationId}")
	public List<Department> findByOrganization(@PathVariable("organizationId") String organizationId);
	
	@GetMapping("/organization/{organizationId}/with-employees")
	public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") String organizationId);
	
}
