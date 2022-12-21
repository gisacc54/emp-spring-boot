package com.example.emp.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Integer Id;

    private String firstName;
    private String lastName;
    private String dob;
    private String email;

    public Map<String,String> validate(){
        Map<String,String> errors = new HashMap<>();
        if (this.firstName == null || this.firstName.isEmpty()){
            errors.put("firstName","firstName is required");
        }

        if (this.lastName == null || this.lastName.isEmpty()){
            errors.put("lastName","lastName is required");
        }

        if (this.dob == null || this.dob.isEmpty()){
            errors.put("dob","dob is required");
        }

        if (this.email == null ||this.email.isEmpty()){
            errors.put("email","email is required");
        }
        return errors;
    }
}