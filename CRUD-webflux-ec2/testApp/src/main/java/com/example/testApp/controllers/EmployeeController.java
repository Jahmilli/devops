package com.example.testApp.controllers;

import com.example.testApp.domains.Employee;
import com.example.testApp.nonfunctional.exceptions.EmployeeError;
import com.example.testApp.nonfunctional.exceptions.EmployeeException;
import com.example.testApp.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.util.List;

import static com.example.testApp.common.Utils.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Inject
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public Mono<Employee> getEmployeeById(@PathVariable String id) {
        logger.info("Employee id is " + id);
        logger.info("A request to '/{id}'");

        return Mono.just(id)
                .flatMap(employeeId -> employeeService.getEmployee(employeeId));
    }

    @PostMapping("/add")
    public Mono<Employee> addEmployee(@RequestBody Employee employee) {
        System.out.println("Employee is " + employee.toString());
        logger.info("A request to '/add' was made");
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/update")
    public Mono<Employee> updateEmployee(@RequestBody Employee employee) {
        logger.info("Employee is " + employee.toString());
        logger.info("A request to '/update' was made");
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteEmployeeById(@PathVariable String id) {
        logger.info("A request to '/delete/{id}' was made");
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/all")
    public Mono<List<Employee>> getAllEmployees() {
        logger.info("A request to '/all' was made");
        return employeeService.getAllEmployees();
    }

    @GetMapping("/details")
    public Mono<String> getDetails() {
        logger.info("A request to /details was made");
        return employeeService.details();
    }


    @ExceptionHandler({EmployeeException.class})
    public ResponseEntity<EmployeeError> handleEmployeeException(EmployeeException e) {
        logger.error("An Exception occurred at: " + getTimeStamp());
        logger.error(e.getMessage());
        return ResponseEntity.status(e.getErrorCode()).body(
                EmployeeError.builder().statusCode(e.getErrorCode()).message(e.getMessage()).build()
        );
    }
}
