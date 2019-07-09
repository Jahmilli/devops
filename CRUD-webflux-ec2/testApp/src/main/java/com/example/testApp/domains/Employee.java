package com.example.testApp.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String address;
//    private Date dateOfBirth;
//    private Timestamp creationDate;
}
