package com.example.emp.exception;

public class EmployeeException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public EmployeeException(String errorMessage) {
        super(errorMessage);
    }
}
