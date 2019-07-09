package com.example.testApp.services;


import com.example.testApp.components.repositories.EmployeeRepository;
import com.example.testApp.domains.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void shouldReturnEmployeeDetails() {
        Employee testEmployee = getEmployee();
        given(employeeRepository.save(testEmployee)).willReturn(Mono.just(testEmployee));

        StepVerifier.create(employeeService.addEmployee(testEmployee))
                .assertNext(employee -> {
                    assertEquals(employee.getId(), testEmployee.getId());
                    assertEquals(employee.getFirstName(), testEmployee.getFirstName());
                })
                .verifyComplete();
    }

    @Test
    public void shouldReturnUpdatedDetails() {
        Employee testEmployee = getEmployee();
        given(employeeRepository.findById(testEmployee.getId())).willReturn(Mono.just(testEmployee));
        testEmployee.setFirstName("Hagrid");
        when(employeeRepository.save(testEmployee)).thenReturn(Mono.just(testEmployee));

        StepVerifier.create(employeeService.updateEmployee(testEmployee))
                .assertNext(employee -> {
                    assertEquals("123", employee.getId());
                    assertEquals("Hagrid", employee.getFirstName());
                })
                .verifyComplete();

    }

    // I really feel like this shouldnt return void but o'well
    // Need to come back to this
//    @Test
//    public void deleteEmployeeShouldReturnVoid() {
//        Employee testEmployee = getEmployee();
//        given(employeeRepository.findById(testEmployee.getId()).then(Mono.just(testEmployee)));
//        when(employeeRepository.deleteById(testEmployee.getId())).thenReturn(Mono.empty());
//
//        StepVerifier.create(employeeService.deleteEmployee("123"))
//                .verifyComplete();
//    }

    @Test
    public void shouldGetAllEmployees() {
        Employee employee1 = getEmployee();
        Employee employee2 = new Employee("345", "Albus", "Dumbledore", "Hogwarts");
        Employee employee3 = new Employee("678", "Hermione", "Granger", "Hogwarts");

        given(employeeRepository.findAll()).willReturn(Flux.just(employee1, employee2, employee3));

        StepVerifier.create(employeeService.getAllEmployees())
                .assertNext(employees -> {
                    assertEquals(employees.get(0).getId(), employee1.getId());
                    assertEquals(employees.get(1).getId(), employee2.getId());
                    assertEquals(employees.get(2).getId(), employee3.getId());
                })
                .verifyComplete();
    }

    // TODO: Add in a tests for failure conditions

    private Employee getEmployee() {
        return Employee.builder()
                .id("123")
                .firstName("Harry")
                .lastName("Potter")
                .address("4 Privet Drive")
                .build();
    }
}