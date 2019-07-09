package com.example.testApp.services;

import com.example.testApp.components.DetailsComponent;
import com.example.testApp.components.repositories.EmployeeRepository;
import com.example.testApp.domains.Employee;
import com.example.testApp.nonfunctional.exceptions.EmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private DetailsComponent detailsComponent;

    @Inject
    public EmployeeService(EmployeeRepository employeeRepository, DetailsComponent detailsComponent) {
        this.employeeRepository = employeeRepository;
        this.detailsComponent = detailsComponent;
    }

    public Mono<Employee> addEmployee(@NotNull Employee employee) {
        return employeeRepository.save(employee);
    }


    public Mono<Employee> updateEmployee(@NotNull Employee updatedEmployee) {
        return employeeRepository
                .findById(updatedEmployee.getId())
                .flatMap(employeeDetails -> {
                    employeeDetails = updatedEmployee;
                    return employeeRepository.save(employeeDetails);
                })
                .switchIfEmpty(Mono.error(new EmployeeException(HttpStatus.BAD_REQUEST.value(), "Employee not found")));
    }

    public Mono<Employee> getEmployee(@NotNull String id) {
        return employeeRepository.findById(id)
                .switchIfEmpty(Mono.error(new EmployeeException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Employee not found")));
    }

    public Mono<Void> deleteEmployee(@NotNull String id) {
        return employeeRepository.findById(id)
                .flatMap(tempEmployee -> employeeRepository.deleteById(tempEmployee.getId()));
    }

    public Mono<String> details() {
        return detailsComponent.getUserDetails("123")
            .flatMap(names -> {
                String newName = names[0] + " " + names[1];
                return Mono.just(newName);
            });
    }

    // Try and return this as a Mono
    public Mono<List<Employee>> getAllEmployees() {
        return employeeRepository.findAll()
                .collectList();
    }
}
