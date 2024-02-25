package com.springbootproject.dto.student;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    @Max(value = 2000000000, message = "ID: Must have a value and be above 1 and below 2000000000.")
    @Min(value = 1, message = "ID: Must have a value and be above 1 and below 2000000000.")
    private int id;

    @NotBlank(message = "Name: Name must have a value, cannot be blank.")
    @Size(max = 1000, message= "Name: Over the character limit of 1000.")
    private String name;

    @Max(value = 2000000000, message = "Age: Exceeded the age limit of 2000000000.")
    @Min(value = 1, message = "Age: Must have a value and be above 1.")
    private int age;

    @Email(message = "Email format is invalid. Please provide a valid email address.")
    @Size(max = 1000, message= "Name: Email the character limit of 1000.")
    @Pattern(regexp = "^[^@.]+@[^@.]+\\.[^@.]+$", message = "Email format is invalid. Please provide a valid email address.")
    private String email;
}
