package springbootproject.object;

import com.springbootproject.object.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherTest {

    Teacher testTeacher1 = new Teacher().builder()
            .id(1)
            .name("Teacher Name")
            .email("teachereamil@provider.com")
            .build();

    Teacher testTeacher2 = new Teacher().builder()
            .id(-99999)
            .name("!!ÁÉÁtreg.-.  __ //" + " " + "" + " " + " /d/n")
            .email("345teache!+ÁÉÉéáéáreamil@provider.com")
            .build();

    @Test
    @DisplayName("#1: Positive test, valid Teacher should not throw exceptions or constraint violation messages.")
    void positiveTestingTeacherObjectCreation() {

        assertEquals(1, testTeacher1.getId());
        assertEquals("Teacher Name", testTeacher1.getName());
        assertEquals("teachereamil@provider.com", testTeacher1.getEmail());

        assertEquals(-99999, testTeacher2.getId());
        assertEquals("!!ÁÉÁtreg.-.  __ //   /d/n", testTeacher2.getName());
        assertEquals("345teache!+ÁÉÉéáéáreamil@provider.com", testTeacher2.getEmail());
    }
}
