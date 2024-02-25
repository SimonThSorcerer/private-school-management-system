package springbootproject.dto;

import com.springbootproject.dto.student.StudentDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentDtoTest {

    @Test
    @DisplayName("#1: Testing StudentDto validation constraints. Should throw the right violation messages.")
    void StudentDto_validationConstraintTesting_invalidStudentDtoShouldThrowTheRightTypeOfViolationMessages() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        StudentDto studentDto = new StudentDto(0, " ", 0, " ");

        Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);

        int emailViolationCount = 0;

        assertEquals(5, violations.size(), "Expected 5 validation violations.");
        for (ConstraintViolation<StudentDto> violation : violations) {
            if (violation.getPropertyPath().toString().equals("id")) {
                assertEquals("ID: Must have a value and be above 1 and below 2000000000.", violation.getMessage(), "Unexpected validation message for id");
            } else if (violation.getPropertyPath().toString().equals("name")) {
                assertEquals("Name: Name must have a value, cannot be blank.", violation.getMessage(), "Unexpected validation message for name");
            } else if (violation.getPropertyPath().toString().equals("age")) {
                assertEquals("Age: Must have a value and be above 1.", violation.getMessage(), "Unexpected validation message for age");
            } else if (violation.getPropertyPath().toString().equals("email")) {
                emailViolationCount++;
                assertEquals("Email format is invalid. Please provide a valid email address.", violation.getMessage(), "Unexpected validation message for email");
            }
        }

        assertEquals(2, emailViolationCount, "Expected 2 violations for the email property");

        StringBuilder randomChars = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 1001; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            randomChars.append(randomChar);
        }

        StudentDto studentDto2 = new StudentDto(2000000001, randomChars.toString(), 2000000001, "email.com");

        Set<ConstraintViolation<StudentDto>> violations2 = validator.validate(studentDto2);

        emailViolationCount = 0;

        assertEquals(5, violations2.size(), "Expected 5 validation violations.");
        for (ConstraintViolation<StudentDto> violation : violations2) {
            if (violation.getPropertyPath().toString().equals("id")) {
                assertEquals("ID: Must have a value and be above 1 and below 2000000000.", violation.getMessage(), "Unexpected validation message for id");
            } else if (violation.getPropertyPath().toString().equals("name")) {
                assertEquals("Name: Over the character limit of 1000.", violation.getMessage(), "Unexpected validation message for name");
            } else if (violation.getPropertyPath().toString().equals("age")) {
                assertEquals("Age: Exceeded the age limit of 2000000000.", violation.getMessage(), "Unexpected validation message for age");
            } else if (violation.getPropertyPath().toString().equals("email")) {
                emailViolationCount++;
                assertEquals("Email format is invalid. Please provide a valid email address.", violation.getMessage(), "Unexpected validation message for email");
            }
        }

        assertEquals(2, emailViolationCount, "Expected 2 violations for the email property");

        StringBuilder randomChars2 = new StringBuilder();
        Random random2 = new Random();
        for (int i = 0; i < 1001; i++) {
            char randomChar = (char) (random2.nextInt(26) + 'a');
            randomChars2.append(randomChar);
        }

        //This test the difference between the @email and the regex, the @Email would be fine with a wrong address if it has a @ in it.
        StudentDto studentDto3 = new StudentDto(-123, randomChars2.toString(), -120, "email@com");

        Set<ConstraintViolation<StudentDto>> violations3 = validator.validate(studentDto3);

        assertEquals(4, violations3.size(), "Expected 4 validation violations.");
        for (ConstraintViolation<StudentDto> violation : violations3) {
            if (violation.getPropertyPath().toString().equals("id")) {
                assertEquals("ID: Must have a value and be above 1 and below 2000000000.", violation.getMessage(), "Unexpected validation message for id");
            } else if (violation.getPropertyPath().toString().equals("name")) {
                assertEquals("Name: Over the character limit of 1000.", violation.getMessage(), "Unexpected validation message for name");
            } else if (violation.getPropertyPath().toString().equals("age")) {
                assertEquals("Age: Must have a value and be above 1.", violation.getMessage(), "Unexpected validation message for age");
            } else if (violation.getPropertyPath().toString().equals("email")) {
                assertEquals("Email format is invalid. Please provide a valid email address.", violation.getMessage(), "Unexpected validation message for email");
            }
        }

        StudentDto studentDto4 = new StudentDto(-123, randomChars2.toString(), -120, "email.com");

        Set<ConstraintViolation<StudentDto>> violations4 = validator.validate(studentDto4);

        emailViolationCount = 0;

        assertEquals(5, violations4.size(), "Expected 5 validation violations.");
        for (ConstraintViolation<StudentDto> violation : violations4) {
            if (violation.getPropertyPath().toString().equals("id")) {
                assertEquals("ID: Must have a value and be above 1 and below 2000000000.", violation.getMessage(), "Unexpected validation message for id");
            } else if (violation.getPropertyPath().toString().equals("name")) {
                assertEquals("Name: Over the character limit of 1000.", violation.getMessage(), "Unexpected validation message for name");
            } else if (violation.getPropertyPath().toString().equals("age")) {
                assertEquals("Age: Must have a value and be above 1.", violation.getMessage(), "Unexpected validation message for age");
            } else if (violation.getPropertyPath().toString().equals("email")) {
                emailViolationCount++;
                assertEquals("Email format is invalid. Please provide a valid email address.", violation.getMessage(), "Unexpected validation message for email");
            }
        }

        assertEquals(2, emailViolationCount, "Expected 2 violations for the email property");
    }

    @Test
    @DisplayName("#2: Testing StudentDto validation constraints. Positive test. Should not throw any violation messages.")
    void StudentDto_validationConstraintTesting_positiveTestValidStudentDtoShouldNotThrowAnyViolationMessages() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        StudentDto studentDto = new StudentDto(2, "Test Name", 18, "rightemail@address.com");

        Set<ConstraintViolation<StudentDto>> violations = validator.validate(studentDto);

        assertEquals(0, violations.size(), "Expected 0 validation violations.");
    }
}
