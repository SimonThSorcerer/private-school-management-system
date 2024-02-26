package springbootproject.object;

import com.springbootproject.object.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherTest {

    Teacher testTeacher1 = new Teacher().builder()
            .teacherId(1)
            .teacherName("Teacher Name")
            .teacherEmail("teachereamil@provider.com")
            .build();

    Teacher testTeacher2 = new Teacher().builder()
            .teacherId(-99999)
            .teacherName("!!ÁÉÁtreg.-.  __ //" + " " + "" + " " + " /d/n")
            .teacherEmail("345teache!+ÁÉÉéáéáreamil@provider.com")
            .build();

    @Test
    @DisplayName("#1: Positive test, valid Teacher should not throw exceptions or constraint violation messages.")
    void positiveTestingTeacherObjectCreation() {

        assertEquals(1, testTeacher1.getTeacherId());
        assertEquals("Teacher Name", testTeacher1.getTeacherName());
        assertEquals("teachereamil@provider.com", testTeacher1.getTeacherEmail());

        assertEquals(-99999, testTeacher2.getTeacherId());
        assertEquals("!!ÁÉÁtreg.-.  __ //   /d/n", testTeacher2.getTeacherName());
        assertEquals("345teache!+ÁÉÉéáéáreamil@provider.com", testTeacher2.getTeacherEmail());
    }
}
