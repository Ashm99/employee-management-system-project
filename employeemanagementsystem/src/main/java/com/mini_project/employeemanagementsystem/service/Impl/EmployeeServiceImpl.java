package com.mini_project.employeemanagementsystem.service.Impl;

import com.mini_project.employeemanagementsystem.dto.CreateEmployeeDTO;
import com.mini_project.employeemanagementsystem.dto.EmployeeDTO;
import com.mini_project.employeemanagementsystem.mapper.EmployeeMapper;
import com.mini_project.employeemanagementsystem.model.Employee;
import com.mini_project.employeemanagementsystem.repo.EmployeeRepository;
import com.mini_project.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        if(employeeList.isEmpty()){
            System.out.println("Database is empty");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee employee : employeeList) {
            employeeDTOList.add(EmployeeMapper.mapToEmployeeDTO(employee));
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTOList);
    }

    @Override
    public ResponseEntity<EmployeeDTO> addEmployee(CreateEmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.save(EmployeeMapper.mapToEmployeeFromCreateEmployeeDTO(employeeDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(EmployeeMapper.mapToEmployeeDTO(employee));
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        EmployeeDTO employeeDTO = EmployeeMapper.mapToEmployeeDTO(optionalEmployee.get());

        return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
    }

    @Override
    public ResponseEntity<Object> updateEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDTO.getId());
        if(optionalEmployee.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No resource available with given employee ID.");
        }
        Employee employee = employeeRepository.save(EmployeeMapper.mapToEmployee(employeeDTO));
        return ResponseEntity.status(HttpStatus.OK).body(EmployeeMapper.mapToEmployeeDTO(employee));
    }

    @Override
    public String deleteEmployee(Long id) {
        if(employeeRepository.findById(id).isEmpty()){
            return "No existing employee found in the given ID";
        }
        employeeRepository.deleteById(id);
        return "Deleted Employee with ID: " + id;
    }
}
