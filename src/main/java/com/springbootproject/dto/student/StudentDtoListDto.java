package com.springbootproject.dto.student;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentDtoListDto {
    private final List<StudentDto> studentDtoListDto = new ArrayList<>();

    public void addStudentDto(@Valid StudentDto studentDto) {
        this.studentDtoListDto.add(studentDto);
    }
}
