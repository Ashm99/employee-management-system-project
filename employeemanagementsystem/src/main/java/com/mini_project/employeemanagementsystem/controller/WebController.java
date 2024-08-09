package com.mini_project.employeemanagementsystem.controller;

import com.mini_project.employeemanagementsystem.common.exceptions.DataNotFoundException;
import com.mini_project.employeemanagementsystem.dto.CreateEmployeeDTO;
import com.mini_project.employeemanagementsystem.dto.EmployeeDTO;
import com.mini_project.employeemanagementsystem.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(value="/api/employees")
public class WebController {

    EmployeeService employeeService;

    // http://localhost:8081/api/employees/home
    /**
     * Renders the home page
     * @param model
     * @return home page
     */
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

    // http://localhost:8081/api/employees/view/{id}
    /**
     * On selection of a particular employee, shows that specific employee's details.
     * @param id
     * @param model
     * @return view-employee html page
     */
    @GetMapping(value = "/view/{id}")
    public String showEmployeeById(@PathVariable(value = "id") Long id, Model model){
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employeeDTO);
        return "view-employee";
    }


    // http://localhost:8081/api/employees/add-employee
    /**
     * Renders the HTML form page for adding a new user
     * @param model
     * @return add-employee html page
     */
    @GetMapping(value="/add-employee")
    public String getEmployee(Model model){
        model.addAttribute("employee", new CreateEmployeeDTO());
        return "add-employee";
    }

    // http://localhost:8081/api/employees/add
    /**
     * Gets a new employee object and saves into the repository.
     * Loads the success or error page based on the outcome.
     * @param model
     * @param newEmployee
     * @return success or error page
     */
    @PostMapping(value = "/add")
    public String addEmployee(Model model, @Valid @ModelAttribute CreateEmployeeDTO newEmployee){
        EmployeeDTO savedEmployee = employeeService.addEmployee(newEmployee);
        if(savedEmployee.getFirstName() == newEmployee.getFirstName() &&
                savedEmployee.getLastName() == newEmployee.getLastName() &&
                savedEmployee.getEmail() == newEmployee.getEmail()
        ){
            model.addAttribute("message", "Employee added with employee ID: " + savedEmployee.getId() + "." );
            return "success";
        }

        model.addAttribute("message", "Error creating new employee" );
        return "error";
    }

    // http://localhost:8081/api/employees/delete/{id}

    /**
     * Deletes the employee object upon clicking delete from the webpage
     * @param id
     * @param model
     * @return a success or an error page based on the outcome.
     */
    @GetMapping(value = "/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id, Model model){
        employeeService.deleteEmployee(id);
        try {
            employeeService.getEmployeeById(id);
        }
        catch(DataNotFoundException e){
            String message = "Successfully deleted employee from the database.";
            model.addAttribute("message", message);
            return "success";
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        model.addAttribute("message", "Error deleting the employee." );
        return "error";
    }
}
