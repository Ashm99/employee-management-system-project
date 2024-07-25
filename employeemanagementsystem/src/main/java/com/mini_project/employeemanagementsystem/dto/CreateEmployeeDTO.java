package com.mini_project.employeemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEmployeeDTO {
    private String firstName;
    private String lastName;
    private String email;
}
