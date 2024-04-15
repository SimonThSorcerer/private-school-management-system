package com.springbootproject.service;

import com.springbootproject.dto.employee.EmployeeDto;
import com.springbootproject.object.Employee;
import com.springbootproject.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ModelMapper modelMapper;

    public EmployeeDto getEmployeeById(int id) {
      Employee employee  = employeeRepository.findById(id).orElse(null);
        return employeeToEmployeeDtoMapper(employee);
    }

    public EmployeeDto employeeToEmployeeDtoMapper(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }




}
