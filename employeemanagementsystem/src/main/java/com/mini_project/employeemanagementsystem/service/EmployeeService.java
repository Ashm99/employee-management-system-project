package com.mini_project.employeemanagementsystem.service;

import com.mini_project.employeemanagementsystem.dto.CreateEmployeeDTO;
import com.mini_project.employeemanagementsystem.dto.EmployeeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<List<EmployeeDTO>> getAllEmployees();

    ResponseEntity<EmployeeDTO> addEmployee(CreateEmployeeDTO employeeDTO);

    ResponseEntity<EmployeeDTO> getEmployeeById(Long id);

    ResponseEntity<Object> updateEmployee(EmployeeDTO employeeDTO);

    String deleteEmployee(Long id);
}
