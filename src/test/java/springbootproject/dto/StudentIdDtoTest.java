package springbootproject.dto;

import com.springbootproject.dto.student.StudentIdDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentIdDtoTest {

    @Test
    @DisplayName("#1: Testing StudentIdDto validation constraints. Should throw the right violation messages.")
    void StudentIdDto_validationConstraintTesting_invalidStudentIdDtoShouldThrowTheRightTypeOfViolationMessages() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        StudentIdDto studentIdDto1 = new StudentIdDto(0);

        Set<ConstraintViolation<StudentIdDto>> violations1 = validator.validate(studentIdDto1);

        assertEquals(1, violations1.size(), "Expected 1 validation violations.");
        for (ConstraintViolation<StudentIdDto> violation : violations1) {
            if (violation.getPropertyPath().toString().equals("id")) {
                assertEquals("ID: Must have a value and be above 1 and below 2000000000.", violation.getMessage(), "Unexpected validation message for courseDtoId");
            }
        }

        StudentIdDto studentIdDto2 = new StudentIdDto(-122343);

        Set<ConstraintViolation<StudentIdDto>> violations2 = validator.validate(studentIdDto2);

        assertEquals(1, violations2.size(), "Expected 1 validation violations.");
        for (ConstraintViolation<StudentIdDto> violation : violations2) {
            if (violation.getPropertyPath().toString().equals("id")) {
                assertEquals("ID: Must have a value and be above 1 and below 2000000000.", violation.getMessage(), "Unexpected validation message for courseDtoId");
            }
        }

        StudentIdDto studentIdDto3 = new StudentIdDto(2000000001);

        Set<ConstraintViolation<StudentIdDto>> violations3 = validator.validate(studentIdDto2);

        assertEquals(1, violations3.size(), "Expected 1 validation violations.");
        for (ConstraintViolation<StudentIdDto> violation : violations3) {
            if (violation.getPropertyPath().toString().equals("id")) {
                assertEquals("ID: Must have a value and be above 1 and below 2000000000.", violation.getMessage(), "Unexpected validation message for courseDtoId");
            }
        }
    }

    @Test
    @DisplayName("#2: Testing StudentIdDto validation constraints. Positive test, should not throw any violation messages.")
    void StudentIdDto_validationConstraintTesting_shouldnotThrowAnyViolationMessages() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        StudentIdDto studentIdDto1 = new StudentIdDto(1);

        Set<ConstraintViolation<StudentIdDto>> violations1 = validator.validate(studentIdDto1);

        assertEquals(0, violations1.size(), "Expected 0 validation violations.");

        StudentIdDto studentIdDto2 = new StudentIdDto(5436);

        Set<ConstraintViolation<StudentIdDto>> violations2 = validator.validate(studentIdDto2);

        assertEquals(0, violations2.size(), "Expected 0 validation violations.");
    }
}
