package com.example.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonComponent
public class ResponseMessageDTO {
    public Boolean status;
    public String message;
}
