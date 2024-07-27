package com.mini_project.employeemanagementsystem.service.Impl;

import com.mini_project.employeemanagementsystem.common.exceptions.DataNotFoundException;
import com.mini_project.employeemanagementsystem.dto.CreateEmployeeDTO;
import com.mini_project.employeemanagementsystem.dto.EmployeeDTO;
import com.mini_project.employeemanagementsystem.mapper.EmployeeMapper;
import com.mini_project.employeemanagementsystem.model.Employee;
import com.mini_project.employeemanagementsystem.repo.EmployeeRepository;
import com.mini_project.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee employee : employeeList) {
            employeeDTOList.add(EmployeeMapper.mapToEmployeeDTO(employee));
        }
        return employeeDTOList;
    }

    @Override
    public EmployeeDTO addEmployee(CreateEmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.save(EmployeeMapper.mapToEmployeeFromCreateEmployeeDTO(employeeDTO));
        return EmployeeMapper.mapToEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isEmpty()){
            String message = "Database does not have an employee with ID: " + id +  " to fetch.";
            System.out.println(message);
            throw new DataNotFoundException(message);
        }
        EmployeeDTO employeeDTO = EmployeeMapper.mapToEmployeeDTO(optionalEmployee.get());

        return employeeDTO;
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDTO.getId());
        if(optionalEmployee.isEmpty()){
            String message = "Database does not have an employee with ID: " + employeeDTO.getId() +  " to update.";
            System.out.println(message);
            throw new DataNotFoundException(message);
        }
        Employee employee = employeeRepository.save(EmployeeMapper.mapToEmployee(employeeDTO));
        return EmployeeMapper.mapToEmployeeDTO(employee);
    }

    @Override
    public String deleteEmployee(Long id) {
        if(employeeRepository.findById(id).isEmpty()){
            String message = "Database does not have an employee with ID: " + id +  " to delete.";
            System.out.println(message);
            throw new DataNotFoundException(message);
        }
        employeeRepository.deleteById(id);
        return "Deleted Employee with ID: " + id;
    }
}
