package com.springbootproject.service;

import com.springbootproject.dto.address.AddressDto;
import com.springbootproject.dto.employee.EmployeeDto;
import com.springbootproject.object.Employee;
import com.springbootproject.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ModelMapper modelMapper;
    //    @Autowired
//    RestTemplate restTemplate;
    @Autowired
    WebClient webClient;
    RestTemplate restTemplate;
    @Value("${addressservice.base.url}")
    private String addressBaseURL;

    public EmployeeService(@Value("${addressservice.base.url}") String addressBaseURL, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .rootUri(addressBaseURL)
                .build();
    }

    public EmployeeDto getEmployeeByIdUsingRestTemplate(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        EmployeeDto employeeDto = employeeToEmployeeDtoMapper(employee);

//        AddressDto addressDto = restTemplate.getForObject("http://localhost:8081/address-app/api/address/{id}", AddressDto.class, id);
//        AddressDto addressDto = restTemplate.getForObject( addressBaseURL + "/address/{id}", AddressDto.class, id);  //application properties + @Value("${addressservice.base.url}")
        AddressDto addressDto = restTemplate.getForObject("/address/{id}", AddressDto.class, id); //This one is professional, but use Reactive Web services, below
        employeeDto.setAddressDto(addressDto);


        return employeeDto;
    }

    public EmployeeDto employeeToEmployeeDtoMapper(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto getEmployeeByIdUsingSpringReactiveWeb(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        EmployeeDto employeeDto = employeeToEmployeeDtoMapper(employee);

        AddressDto addressDto = webClient
                .get()
                .uri("/address/" + id)
                .retrieve()
                .bodyToMono(AddressDto.class)
                .block();

        employeeDto.setAddressDto(addressDto);


        return employeeDto;
    }


}
