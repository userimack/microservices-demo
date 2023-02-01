package com.example.organizationservice.repository;

import com.example.organizationservice.model.Organization;
import org.springframework.data.repository.CrudRepository;


public interface OrganizationRepository extends CrudRepository<Organization, String> {
	
}
