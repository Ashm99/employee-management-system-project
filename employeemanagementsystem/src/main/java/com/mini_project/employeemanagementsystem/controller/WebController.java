package com.mini_project.employeemanagementsystem.controller;

import com.mini_project.employeemanagementsystem.dto.EmployeeDTO;
import com.mini_project.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(value="/api/employees")
public class WebController {

    EmployeeService employeeService;

    // http://localhost:8081/api/employees/home
    @GetMapping(value="/home")
    public String home(Model model){
        List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();
        if(!employeeDTOList.isEmpty()){
            model.addAttribute("employeeList", employeeDTOList);
            model.addAttribute("isEmpty", false);
        }
        else{
            model.addAttribute("isEmpty", true);
        }
        return "index";
    }

    // http://localhost:8081/api/employees/add-employee
    @GetMapping(value="/add-employee")
    public String getEmployee(Model model){
        return "add-employee";
    }

    // http://localhost:8081/api/employees/view/{id}
    @GetMapping(value = "/view/{id}")
    public String showEmployeeById(@PathVariable(value = "id") Long id, Model model){
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employeeDTO);
        return "view-employee";
    }
}
