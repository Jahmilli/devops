package com.example.testApp.controllers;

import com.example.testApp.domains.Employee;
import com.example.testApp.services.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;


    @Test
    public void shouldGetEmployeeById() {
        given(employeeService.getEmployee("123")).willReturn(Mono.just(getEmployee()));

        StepVerifier.create(employeeController.getEmployeeById("123"))
                .assertNext(employee -> {
                    assertEquals("123", employee.getId());
                    assertEquals("Harry", employee.getFirstName());
                    assertEquals("Potter", employee.getLastName());
                })
                .verifyComplete();
    }

    @Test
    public void shouldAddEmployee() {
        given(employeeService.addEmployee(getEmployee())).willReturn(Mono.just(getEmployee()));
        StepVerifier.create(employeeController.addEmployee(getEmployee()))
                .assertNext(employee -> {
                    assertEquals("123", employee.getId());
                    assertEquals("Harry", employee.getFirstName());
                })
                .verifyComplete();
    }

    @Test
    public void shouldUpdateEmployee() {
        Employee updatedEmployee = getEmployee();
        updatedEmployee.setFirstName("Albus");
        updatedEmployee.setLastName("Dumbledore");
        given(employeeService.updateEmployee(updatedEmployee)).willReturn(Mono.just(updatedEmployee));

        StepVerifier.create(employeeController.updateEmployee(updatedEmployee))
                .assertNext(employee -> {
                    assertEquals("Albus", employee.getFirstName());
                    assertEquals("Dumbledore", employee.getLastName());
                })
                .verifyComplete();
    }

    @Test
    public void shouldGetAllEmployees() {
        Employee employee1 = getEmployee();
        Employee employee2 = new Employee("345", "Albus", "Dumbledore", "Hogwarts");
        Employee employee3 = new Employee("678", "Hermione", "Granger", "Hogwarts");
        List<Employee> employeeList = new LinkedList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        given(employeeService.getAllEmployees()).willReturn(Mono.just(employeeList));

        StepVerifier.create(employeeController.getAllEmployees())
                .assertNext(employees -> {
                    assertEquals("Potter", employees.get(0).getLastName());
                    assertEquals("Dumbledore", employees.get(1).getLastName());
                    assertEquals("Granger", employees.get(2).getLastName());
                })
                .verifyComplete();
    }

    private Employee getEmployee() {
        return Employee.builder()
                .id("123")
                .firstName("Harry")
                .lastName("Potter")
                .address("4 Privet Drive")
                .build();
    }
}
