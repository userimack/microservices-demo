package com.example.organizationservice;

import com.example.organizationservice.model.Organization;
import com.example.organizationservice.repository.OrganizationRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableMongoRepositories
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class OrganizationServiceApplication implements CommandLineRunner {

	@Autowired
	OrganizationRepository organizationRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		organizationRepository.deleteAll();

		String acmeId = "65e76fbf-6e03-4298-a644-6db769a9436c";
		String umbrellaId = "8acf038f-8617-46ab-824e-54801d07a53b";

		Organization acmeCorp = new Organization("Acme Corp", "New York, USA");
		acmeCorp.setId(acmeId);

		Organization umbrellaCorp = new Organization("Umbrella Corp", "Chicago, USA");
		umbrellaCorp.setId(umbrellaId);

		organizationRepository.save(acmeCorp);
		organizationRepository.save(umbrellaCorp);
	}

}
