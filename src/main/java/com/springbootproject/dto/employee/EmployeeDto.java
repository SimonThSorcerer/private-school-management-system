package com.springbootproject.dto.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    @NotNull
    int id;

    @NotBlank(message = "Please type in your first name here.")
    String firstName;

    @NotBlank(message = "Please type in your last name here.")
    String lastName;

    @NotBlank
    @Email

    String email;

    @NotBlank(message = "Please type in your blood group.0")
    String bloodGroup;
}
