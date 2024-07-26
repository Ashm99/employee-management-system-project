package com.mini_project.employeemanagementsystem.controller;

import com.mini_project.employeemanagementsystem.dto.CreateEmployeeDTO;
import com.mini_project.employeemanagementsystem.dto.EmployeeDTO;
import com.mini_project.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeRestController {
    private EmployeeService employeeService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping(value = "/addEmployee")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody CreateEmployeeDTO employeeDTO){
        return employeeService.addEmployee(employeeDTO);
    }

    @GetMapping(value = "/getEmployee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") Long id){
        return employeeService.getEmployeeById(id);
    }

    @PutMapping(value = "/updateEmployee")
    public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateEmployee(employeeDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id){
        return employeeService.deleteEmployee(id);
    }
}
