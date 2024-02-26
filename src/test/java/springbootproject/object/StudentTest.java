package springbootproject.object;

import com.springbootproject.object.Course;
import com.springbootproject.object.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {

    Course courseTest1 = Course.builder()
            .courseId(1)
            .courseName("Course name")
            .courseCapacity(200)
            .build();

    Student studentTest1PositiveTestWithoutCourse = Student.builder()
            .studentId(1)
            .studentName("Test Name")
            .studentAge(12)
            .studentEmail("proper@email.com")
            .build();

    Student studentTest2PositiveTestWithCourse = Student.builder()
            .studentId(2)
            .studentName("Test Name 2")
            .studentAge(1)
            .studentEmail("simon@citromail.hu")
            .studentCourse(courseTest1)
            .build();

    @Test
    @DisplayName("#1: Positive test, valid students should not throw exceptions or constraint violation messages.")
    void positiveTestingStudentObjectCreation() {

        assertEquals(1, studentTest1PositiveTestWithoutCourse.getStudentId());
        assertEquals("Test Name", studentTest1PositiveTestWithoutCourse.getStudentName());
        assertEquals(12, studentTest1PositiveTestWithoutCourse.getStudentAge());
        assertEquals("proper@email.com", studentTest1PositiveTestWithoutCourse.getStudentEmail());

        assertEquals(2, studentTest2PositiveTestWithCourse.getStudentId());
        assertEquals("Test Name 2", studentTest2PositiveTestWithCourse.getStudentName());
        assertEquals(1, studentTest2PositiveTestWithCourse.getStudentAge());
        assertEquals("simon@citromail.hu", studentTest2PositiveTestWithCourse.getStudentEmail());
    }
}
