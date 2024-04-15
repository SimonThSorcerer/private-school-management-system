package springbootproject.object;

import com.springbootproject.object.Course;
import com.springbootproject.object.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {

    Teacher teacherTest1 = Teacher.builder()
            .id(1)
            .name("Test Name")
            .email("teacher@provider.com")
            .build();

    Teacher teacherTest2WithMoreComplicatedValues = Teacher.builder()
            .id(-12)
            .name("ÁÉÉ+!123? _ ŰÁŐÜüüöó" + " ")
            .email("teacher@provider.com")
            .build();

    Course courseTest1WithTeacher = Course.builder()
            .id(1)
            .name("test")
            .capacity(200)
            .teacher(teacherTest1)
            .build();

    Course courseTest2WithTeacher = Course.builder()
            .id(-12)
            .name("FWGÁÉÉ! 4" + " " + "" + "___43+!null/n")
            .capacity(-100)
            .teacher(teacherTest2WithMoreComplicatedValues)
            .build();

    @Test
    @DisplayName("#1: Positive test, valid Course should not throw exceptions or constraint violation messages.")
   void positiveTestingCourseObjectCreation() {

        assertEquals(1, courseTest1WithTeacher.getId());
        assertEquals("test", courseTest1WithTeacher.getName());
        assertEquals(200, courseTest1WithTeacher.getCapacity());
        assertEquals(teacherTest1, courseTest1WithTeacher.getTeacher());

        assertEquals(-12, courseTest2WithTeacher.getId());
        assertEquals("FWGÁÉÉ! 4 ___43+!null/n", courseTest2WithTeacher.getName());
        assertEquals(-100, courseTest2WithTeacher.getCapacity());
        assertEquals(teacherTest2WithMoreComplicatedValues, courseTest2WithTeacher.getTeacher());
    }
}
