//package springbootproject.service;
//
//import com.springbootproject.exception.ElementNotFoundException;
//import com.springbootproject.exception.EnrollmentStatusException;
//import com.springbootproject.object.Course;
//import com.springbootproject.object.Student;
//import com.springbootproject.object.Teacher;
//import com.springbootproject.repository.CourseRepository;
//import com.springbootproject.repository.StudentRepository;
//import com.springbootproject.service.EnrolmentServiceImpl;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class EnrolmentServiceImplTests {
//
//    @Mock
//    private CourseRepository courseRepository;
//    @Mock
//    private StudentRepository studentRepository;
//    @InjectMocks
//    private EnrolmentServiceImpl enrolmentService;
//
//
//
//    private static Course testCourse1;
//    private static Course testCourse2;
//    private static Student enrolledStudent;
//    private static Student unenrolledStudent;
//
//    private static Teacher testTeacher;
//
//    @BeforeAll
//    public static void init() {
//
//        // setting a test teacher
//        testTeacher = Teacher.builder()
//                .courseId(1)
//                .name("Andrew Ng")
//                .email("AndrewNg@coursera.com")
//                .build();
//
//        // setting a test course 1
//        testCourse1 = Course.builder()
//                .courseId(1)
//                .name("Java for beginners")
//                .capacity(20)
//                .teacher(testTeacher)
//                .build();
//
//        // setting a test course 2
//        testCourse1 = Course.builder()
//                .courseId(1)
//                .name("Docker for DevOps specialists")
//                .capacity(20)
//                .teacher(testTeacher)
//                .build();
//
//        // setting a test enrolled Student
//        enrolledStudent = Student.builder()
//                .courseId(1)
//                .name("John Smith")
//                .age(20)
//                .email("jsmith@gmail.com")
//                .course(testCourse1)
//                .build();
//
//        // setting a test unenrolled Student
//        unenrolledStudent = Student.builder()
//                .courseId(2)
//                .name("Veronica Adams")
//                .age(18)
//                .email("vadams@gmail.com")
//                .build();
//    }
//
//    @BeforeEach
//    public void setTestingObjects() {
//        enrolledStudent.setCourse(testCourse1);
//        unenrolledStudent.setCourse(null);
//    }
//
//    // ENROLL METHOD
//
//    @Test
//    @DisplayName("#1.1: Non-existing student should throw exception")
//    public void nonExistingStudentShouldThrowException1() {
//        when(studentRepository.findById(anyInt())).thenReturn(Optional.empty());
//        Assertions.assertThrows(ElementNotFoundException.class, () -> enrolmentService.enroll(anyInt(), testCourse1.getCourseId()));
//    }
//
//    @Test
//    @DisplayName("#1.2: Non-existing course should throw exception")
//    public void nonExistingCourseShouldThrowException1() {
//        when(studentRepository.findById(anyInt())).thenReturn(Optional.of(unenrolledStudent));
//        when(courseRepository.findById(anyInt())).thenReturn(Optional.empty());
//       Assertions.assertThrows(ElementNotFoundException.class, () -> enrolmentService.enroll(unenrolledStudent.getCourseId(), anyInt()));
//    }
//
//    @Test
//    @DisplayName("#1.3: Enrolled student can't be re-enrolled")
//    public void enrolledStudentCantBeEnrolledAgain() {
//        int studentId = enrolledStudent.getCourseId();
//        int courseId = testCourse1.getCourseId();
//        when(studentRepository.findById(studentId)).thenReturn(Optional.of(enrolledStudent));
//        Assertions.assertThrows(EnrollmentStatusException.class, () -> enrolmentService.enroll(studentId, courseId));
//    }
//
//    @Test
//    @DisplayName("#1.4: Correct enrolling student does not throw exception")
//    public void correctStudentShouldNotThrowException1() {
//        int studentId = unenrolledStudent.getCourseId();
//        int courseId = testCourse2.getCourseId();
//        when(studentRepository.findById(studentId)).thenReturn(Optional.of(unenrolledStudent));
//        when(courseRepository.findById(courseId)).thenReturn(Optional.of(testCourse2));
//        Assertions.assertDoesNotThrow(() -> enrolmentService.enroll(studentId, courseId));
//    }
//
//    @Test
//    @DisplayName("#1.5: Unenrolled existing student should enrol to existing course")
//    public void unenrolledStudentDShouldEnrollSuccessfully() {
//        int studentId = unenrolledStudent.getCourseId();
//        int courseId = testCourse2.getCourseId();
//        when(studentRepository.findById(studentId)).thenReturn(Optional.of(unenrolledStudent));
//        when(courseRepository.findById(courseId)).thenReturn(Optional.of(testCourse2));
//        Student result = enrolmentService.enroll(studentId, courseId);
//        Assertions.assertNotNull(result.getCourse(), "Student should have been enrolled, but he/she did not");
//        Assertions.assertEquals(testCourse2, result.getCourse(), String.format("Student should have been enrolled to %s, but he/she enrolled to %s", testCourse2.getName(), result.getCourse().getName()));
//    }
//
//
//    // UNENROLL METHOD
//
//    @Test
//    @DisplayName("#2.1: Non-existing unenrolling student should throw exception")
//    public void nonExistingStudentShouldThrowException2() {
//       when(studentRepository.findById(anyInt())).thenReturn(Optional.empty());
//       Assertions.assertThrows(ElementNotFoundException.class, () -> enrolmentService.unenroll(anyInt(), testCourse1.getCourseId()));
//    }
//
//    @Test
//    @DisplayName("#2.2: Non-existing course should throw exception")
//    public void nonExistingCourseShouldThrowException2() {
//        when(studentRepository.findById(anyInt())).thenReturn(Optional.of(unenrolledStudent));
//        when(courseRepository.findById(anyInt())).thenReturn(Optional.empty());
//        Assertions.assertThrows(ElementNotFoundException.class, () -> enrolmentService.unenroll(unenrolledStudent.getCourseId(), -1));
//    }
//
//    @Test
//    @DisplayName("#2.3: Unenrolled student can't be unenrolled")
//    public void unenrolledStudentCantBeUnenrolledAgain() {
//        int studentId = unenrolledStudent.getCourseId();
//        when(studentRepository.findById(studentId)).thenReturn(Optional.of(unenrolledStudent));
//        when(courseRepository.findById(anyInt())).thenReturn(Optional.of(testCourse1)); // to ensure that 404 exception is not thrown
//        Assertions.assertThrows(EnrollmentStatusException.class, () -> enrolmentService.unenroll(studentId, anyInt()));
//    }
//
//    @Test
//    @DisplayName("#2.4: Wrong course to unenroll")
//    public void wrongCourseToUnenroll() {
//        int studentId = enrolledStudent.getCourseId(); // enrolled in testCourse1
//        int courseId = testCourse2.getCourseId(); // testCourse2
//        when(studentRepository.findById(studentId)).thenReturn(Optional.of(enrolledStudent));
//        when(courseRepository.findById(courseId)).thenReturn(Optional.of(testCourse2)); // to ensure that 404 exception is not thrown
//        Assertions.assertThrows(EnrollmentStatusException.class, () -> enrolmentService.unenroll(studentId, courseId));
//    }
//
//    @Test
//    @DisplayName("#2.5: Correct unenrolling student does not throw exception")
//    public void correctStudentShouldNotThrowException2() {
//        int studentId = enrolledStudent.getCourseId(); // enrolled in testCourse1
//        int courseId = enrolledStudent.getCourse().getCourseId(); // testCourse1
//
//        when(studentRepository.findById(studentId)).thenReturn(Optional.of(enrolledStudent));
//        when(courseRepository.findById(courseId)).thenReturn(Optional.of(enrolledStudent.getCourse()));
//        when(studentRepository.save(any(Student.class))).thenAnswer(i -> i.getArgument(0));
//        Assertions.assertDoesNotThrow(() -> enrolmentService.unenroll(studentId, courseId));
//    }
//}
