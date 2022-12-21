package com.example.emp.controller;

import com.example.emp.dto.EmployeeDTO;
import com.example.emp.dto.ResponseDTO;
import com.example.emp.exception.EmployeeException;
import com.example.emp.model.Employee;
import com.example.emp.service.EmployeeService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(path = "")
    public ResponseEntity<ResponseDTO> createEmployee(@RequestBody EmployeeDTO request) throws ParseException {

        Map<String,String> validationErrors = request.validate();
        if (!validationErrors.isEmpty())
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(true,"validation error",validationErrors),HttpStatus.OK);

        try {
            if (!employeeService.createEmployee(request))
                return new ResponseEntity<ResponseDTO>(new ResponseDTO(true,"Fail to create User",null),HttpStatus.OK);
        }catch (EmployeeException e){
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(true,e.getMessage(),null),HttpStatus.OK);
        }


        return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"User created successful",null),HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"",employees),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getEmployee(@PathVariable("id") Integer id){
        try {
            Employee employee = employeeService.getEmployee(id);
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"",employee),HttpStatus.OK);
        }catch (EmployeeException e){
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(true,e.getMessage(),null),HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateEmployee(@PathVariable("id") Integer id,@RequestBody EmployeeDTO request){
        Map<String,String> validationErrors = request.validate();
        if (!validationErrors.isEmpty())
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(true,"validation error",validationErrors),HttpStatus.OK);

        try {
            request.setId(id);
            if (!employeeService.updateEmployee(request))
                return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"fail to update",null),HttpStatus.OK);
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Updated successful",null),HttpStatus.OK);
        }catch (EmployeeException e){
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(true,e.getMessage(),null),HttpStatus.OK);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteEmployee(@PathVariable("id") Integer id){
        try {
            if (!employeeService.deleteEmployee(id))
                return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Fail to delete employee",null),HttpStatus.OK);
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Employee deleted successful",null),HttpStatus.OK);
        }catch (EmployeeException e){
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(true,e.getMessage(),null),HttpStatus.OK);
        }
    }
}
