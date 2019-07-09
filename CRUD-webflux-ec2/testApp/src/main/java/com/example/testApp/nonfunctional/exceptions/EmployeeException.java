package com.example.testApp.nonfunctional.exceptions;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class EmployeeException extends Exception {
    private final int errorCode;
    private final String message;
}
