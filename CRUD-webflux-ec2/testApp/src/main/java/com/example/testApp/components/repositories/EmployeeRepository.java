package com.example.testApp.components.repositories;

import com.example.testApp.domains.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {

}
