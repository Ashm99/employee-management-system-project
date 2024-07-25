package com.mini_project.employeemanagementsystem.service.Impl;

import com.mini_project.employeemanagementsystem.repo.EmployeeRepository;
import com.mini_project.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;
}
