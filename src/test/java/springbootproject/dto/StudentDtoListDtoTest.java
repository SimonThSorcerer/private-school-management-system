package springbootproject.dto;

import com.springbootproject.dto.student.StudentDto;
import com.springbootproject.dto.student.StudentDtoListDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentDtoListDtoTest {

    @Test
    @DisplayName("#1: Testing StudentDtoListDto validation constraints. Should not throw any violation messages.")
    void StudentDtoListDto_validationConstraintTesting_positiveTestValidStudentDtoShouldNotThrowAnyViolationMessages() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        StudentDto studentDto1 = new StudentDto(1, "Test Name 1", 1, "testééáőúüó13_1@example.com");
        StudentDto studentDto2 = new StudentDto(2, "Test Name ÁÉŐ12+-+--__ŰÍY", 20, "test2@example.com");

        StudentDtoListDto studentDtoListDto = new StudentDtoListDto();
        studentDtoListDto.addStudentDto(studentDto1);
        studentDtoListDto.addStudentDto(studentDto2);

        Set<ConstraintViolation<StudentDtoListDto>> violations = validator.validate(studentDtoListDto);

        assertEquals(0, violations.size(), "Expected 0 validation violations.");
    }

    /*This just makes sure that StudentDto in StudentDtoListDto throws validation messages, as it should, for the specific validation violation messages and their testing can be found in
     StudentDtoTest.*/
    @Test
    @DisplayName("#2: Testing StudentDtoListDto validation constraints. Should throw violation messages.")
    void StudentDtoListDto_validationConstraintTesting_invalidStudentDtoListDtoShouldThrowTheRightViolationMessages() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        StudentDto studentDto1 = new StudentDto(0, " ", 0, " ");
        StudentDto studentDto2 = new StudentDto(2, "Test Name ÁÉŐ12+-+--__ŰÍY", 20, "test2example.com");

        StudentDtoListDto studentDtoListDto = new StudentDtoListDto();
        studentDtoListDto.addStudentDto(studentDto1);
        studentDtoListDto.addStudentDto(studentDto2);

        List<ConstraintViolation<StudentDto>> violations1 = new ArrayList<>();
        List<ConstraintViolation<StudentDto>> violations2 = new ArrayList<>();

        violations1.addAll(validator.validate(studentDtoListDto.getStudentDtoList().get(0)));
        violations2.addAll(validator.validate(studentDtoListDto.getStudentDtoList().get(1)));

        assertEquals(5, violations1.size(), "Expected 5 validation violations for studentDto1.");
        assertEquals(2, violations2.size(), "Expected 2 validation violations for studentDto2.");
    }
}
