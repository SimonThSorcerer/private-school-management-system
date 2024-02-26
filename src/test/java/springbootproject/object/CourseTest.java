package springbootproject.object;

import com.springbootproject.object.Course;
import com.springbootproject.object.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {

    Teacher teacherTest1 = Teacher.builder()
            .teacherId(1)
            .teacherName("Test Name")
            .teacherEmail("teacher@provider.com")
            .build();

    Teacher teacherTest2WithMoreComplicatedValues = Teacher.builder()
            .teacherId(-12)
            .teacherName("ÁÉÉ+!123? _ ŰÁŐÜüüöó" + " ")
            .teacherEmail("teacher@provider.com")
            .build();

    Course courseTest1WithTeacher = Course.builder()
            .courseId(1)
            .courseName("test")
            .courseCapacity(200)
            .courseTeacher(teacherTest1)
            .build();

    Course courseTest2WithTeacher = Course.builder()
            .courseId(-12)
            .courseName("FWGÁÉÉ! 4" + " " + "" + "___43+!null/n")
            .courseCapacity(-100)
            .courseTeacher(teacherTest2WithMoreComplicatedValues)
            .build();

    @Test
    @DisplayName("#1: Positive test, valid Course should not throw exceptions or constraint violation messages.")
   void positiveTestingCourseObjectCreation() {

        assertEquals(1, courseTest1WithTeacher.getCourseId());
        assertEquals("test", courseTest1WithTeacher.getCourseName());
        assertEquals(200, courseTest1WithTeacher.getCourseCapacity());
        assertEquals(teacherTest1, courseTest1WithTeacher.getCourseTeacher());

        assertEquals(-12, courseTest2WithTeacher.getCourseId());
        assertEquals("FWGÁÉÉ! 4 ___43+!null/n", courseTest2WithTeacher.getCourseName());
        assertEquals(-100, courseTest2WithTeacher.getCourseCapacity());
        assertEquals(teacherTest2WithMoreComplicatedValues, courseTest2WithTeacher.getCourseTeacher());
    }
}
