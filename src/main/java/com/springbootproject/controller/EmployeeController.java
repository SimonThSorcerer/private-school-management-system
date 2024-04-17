package com.springbootproject.controller;


import com.springbootproject.dto.employee.EmployeeDto;
import com.springbootproject.object.Employee;
import com.springbootproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeDetails(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeByIdUsingRestTemplate(id));
    }


}
