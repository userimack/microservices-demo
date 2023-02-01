package com.example.departmentservice;

import com.example.departmentservice.model.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableMongoRepositories
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class DepartmentServiceApplication implements CommandLineRunner {

    @Autowired
    DepartmentRepository departmentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DepartmentServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        departmentRepository.deleteAll();

        String orgId1 = "65e76fbf-6e03-4298-a644-6db769a9436c";
        String orgId2 = "8acf038f-8617-46ab-824e-54801d07a53b";
        String financeId = "eccc57b5-df61-4926-bdf3-64326d63bf89";
        String hrId = "42fbb131-f315-42be-96c3-a76461a82897";
        String salesId = "539fa5da-d293-4ef7-8146-6112b8b438a3";
        String engineeringId = "0b35362e-87c3-4b17-ba3d-e92465579346";

        Department finance = new Department(orgId1, "Finance");
        finance.setId(financeId);

        Department hr = new Department(orgId1, "Human Resource");
        hr.setId(hrId);

        Department sales = new Department(orgId2, "Sales");
        sales.setId(salesId);

        Department engineering = new Department(orgId2, "Engineering");
        engineering.setId(engineeringId);

        departmentRepository.save(finance);
        departmentRepository.save(hr);

        departmentRepository.save(sales);
        departmentRepository.save(engineering);
    }
}
