package com.mini_project.employeemanagementsystem.controller;

import com.mini_project.employeemanagementsystem.dto.EmployeeDTO;
import com.mini_project.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
            model.addAttribute("employee-list", employeeDTOList);
        }
        return "index";
    }

    // http://localhost:8081/api/employees/home1
    @GetMapping(value="/home1")
    public String index(Model model){
        model.addAttribute("message", "Welcome home");
        return "index";
    }
}
