package com.example.emp.repository;

import com.example.emp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findByEmail(@Param("email")String email);
}
