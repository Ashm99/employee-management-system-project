package com.mini_project.employeemanagementsystem.controller;

import com.mini_project.employeemanagementsystem.dto.CreateEmployeeDTO;
import com.mini_project.employeemanagementsystem.dto.EmployeeDTO;
import com.mini_project.employeemanagementsystem.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeRestController {
    private EmployeeService employeeService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        List<EmployeeDTO> employeeDTOList =  employeeService.getAllEmployees();
        if(employeeDTOList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(employeeDTOList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTOList);
    }

    @PostMapping(value = "/addEmployee")
    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody CreateEmployeeDTO employeeDTO){
        EmployeeDTO savedEmployeeDTO =  employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployeeDTO);
    }

    @GetMapping(value = "/getEmployee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") Long id){
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
    }

    @PutMapping(value = "/updateEmployee")
    public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployeeDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteEmployee(id));
    }
}
