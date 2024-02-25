package com.springbootproject.service;

import com.springbootproject.exception.ElementNotFoundException;
import com.springbootproject.exception.EnrollmentStatusException;
import com.springbootproject.object.Course;
import com.springbootproject.object.Student;
import com.springbootproject.repository.CourseRepository;
import com.springbootproject.repository.StudentRepository;
import com.springbootproject.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrolmentServiceImpl implements EnrolmentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

//    @PostConstruct
//    public void init() {
//
//        studentRepository.deleteAll();
//        courseRepository.deleteAll();
//        teacherRepository.deleteAll();
//
//        studentRepository.save(Student.builder()
//                .name("John Smith")
//                .age(20)
//                .email("jsmith@gmail.com")
//                .build());
//
//        studentRepository.save(Student.builder()
//                .name("Veronica Adams")
//                .age(18)
//                .email("vadams@gmail.com")
//                .build());
//
//        Teacher teacher = teacherRepository.save(Teacher.builder()
//                .name("Andrew Ng")
//                .email("andrewNg@coursera.com")
//                .build());
//
//        courseRepository.save(Course.builder()
//                .name("Java for beginners")
//                .teacher(teacher)
//                .capacity(20)
//                .build());
//
//        courseRepository.save(Course.builder()
//                .name("Spring Boot for beginners")
//                .teacher(teacher)
//                .capacity(20)
//                .build());
//
//        courseRepository.save(Course.builder()
//                .name("Advance Java for developers")
//                .teacher(teacher)
//                .capacity(20)
//                .build());
//
//    }


    @Override
    public Student enroll(int studentId, int courseId) {
        log.trace("starting the process of enrollment of student with id {} to course with id {}", studentId, courseId);
        // student record is retrieved from the database.
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ElementNotFoundException("Student not found with id " + studentId));

        // check if student has already been enrolled in any course
        if (student.getStudentCourse() != null) {
            String message = String.format("Student %s already enrolled in the course %s", student.getStudentName(), student.getStudentCourse().getCourseName());
            throw new EnrollmentStatusException(message);
        }

        // desired course is retrieved from the database
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ElementNotFoundException("Course not found with id " + courseId));

        // check if desired course has vacant seats
        if (course.getCourseStudentListForeignKey().size() >= course.getCourseCapacity()) {
            String message = String.format("Unable to enrol as the course %s is full. Please choose another course.", course.getCourseName());
            throw new EnrollmentStatusException(message);
        }

        // update the student record
        student.setStudentCourse(course);
        studentRepository.save(student);
        log.debug("Student {} (id {}) successfully enrolled in the course {} (id {})", student.getStudentName(), student.getStudentId(), course.getCourseName(), course.getCourseId());
        return student;
    }

    @Override
    public Student unenroll(int studentId, int courseId) {
        log.trace("starting the process of unenrollment of student with id {} to course with id {}", studentId, courseId);

        // student record is retrieved from the database.
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ElementNotFoundException("Student not found with id " + studentId));

        // desired course is retrieved from the database
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ElementNotFoundException("Course not found with id " + courseId));

        // nothing is changed if student is not actually enrolled in any course
        if (student.getStudentCourse() == null) {
            String message = String.format("Student %s is not currently enrolled in any of the courses", student.getStudentName());
            throw new EnrollmentStatusException(message);
            // nothing is changed if student is not enrolled to the course, from which he needs to be enenrolled
        } else if (student.getStudentCourse() != course) {
            String message = String.format("Student %s is not currently enrolled in the course %s", student.getStudentName(), course.getCourseName());
            throw new EnrollmentStatusException(message);
            // otherwise, student is enenrolled
        } else {
            student.setStudentCourse(null);
            student = studentRepository.save(student);
            log.debug("Student {} (id {}) successfully unenrolled from the course {} (id {})", student.getStudentName(), student.getStudentId(), course.getCourseName(), course.getCourseId());
            return student;
        }
    }
}
