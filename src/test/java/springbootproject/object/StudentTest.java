package springbootproject.object;

import com.springbootproject.object.Course;
import com.springbootproject.object.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {

    Course courseTest1 = Course.builder()
            .id(1)
            .name("Course name")
            .capacity(200)
            .build();

    Student studentTest1PositiveTestWithoutCourse = Student.builder()
            .id(1)
            .name("Test Name")
            .age(12)
            .email("proper@email.com")
            .build();

    Student studentTest2PositiveTestWithCourse = Student.builder()
            .id(2)
            .name("Test Name 2")
            .age(1)
            .email("simon@citromail.hu")
            .course(courseTest1)
            .build();

    @Test
    @DisplayName("#1: Positive test, valid students should not throw exceptions or constraint violation messages.")
    void positiveTestingStudentObjectCreation() {

        assertEquals(1, studentTest1PositiveTestWithoutCourse.getId());
        assertEquals("Test Name", studentTest1PositiveTestWithoutCourse.getName());
        assertEquals(12, studentTest1PositiveTestWithoutCourse.getAge());
        assertEquals("proper@email.com", studentTest1PositiveTestWithoutCourse.getEmail());

        assertEquals(2, studentTest2PositiveTestWithCourse.getId());
        assertEquals("Test Name 2", studentTest2PositiveTestWithCourse.getName());
        assertEquals(1, studentTest2PositiveTestWithCourse.getAge());
        assertEquals("simon@citromail.hu", studentTest2PositiveTestWithCourse.getEmail());
    }
}
