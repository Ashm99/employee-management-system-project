package com.mini_project.employeemanagementsystem.controller;

import com.mini_project.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EmployeeRestController {
    private EmployeeService employeeService;


}
