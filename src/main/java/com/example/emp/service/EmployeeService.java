package com.example.emp.service;

import com.example.emp.dto.EmployeeDTO;
import com.example.emp.exception.EmployeeException;
import com.example.emp.model.Employee;
import com.example.emp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean createEmployee(EmployeeDTO request) throws ParseException, EmployeeException {
        Employee dbEmp = employeeRepository.findByEmail(request.getEmail());

        if(dbEmp != null)
            throw new EmployeeException("User already exists.");

        Employee employee = new Employee();

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setDob(new SimpleDateFormat("d-M-y").parse(request.getDob()));
        employee.setFirstName(request.getFirstName());

        Employee save = employeeRepository.save(employee);

        if (save.getId() == null)
            throw new EmployeeException("Fail to create user.");

        return true;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Integer id) {

        try {
            return employeeRepository.findById(id).get();
        }catch (RuntimeException e){
            throw new EmployeeException("Employee not found");
        }
    }

    public boolean updateEmployee(EmployeeDTO request) throws ParseException {
        Employee employee;
        try {
            employee = employeeRepository.findById(request.getId()).get();
        }catch (RuntimeException e){
            throw new EmployeeException("Employee not found");
        }

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setDob(new SimpleDateFormat("d-M-y").parse(request.getDob()));
        employee.setFirstName(request.getFirstName());

        try {
            employeeRepository.save(employee);
            return true;
        }catch (RuntimeException e){
            throw new EmployeeException("Fail to update emp");
        }

    }


    public boolean deleteEmployee(Integer id) {

        try {
            Employee employee = employeeRepository.findById(id).get();
            employeeRepository.delete(employee);
            return true;
        }catch (RuntimeException e){
            throw new EmployeeException("Employee not found");
        }
    }
}
