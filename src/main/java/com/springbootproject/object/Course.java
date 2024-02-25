package com.springbootproject.object;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "COURSE")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int courseId;

    @Column(name = "NAME", nullable = false)
    private String courseName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacherCourseListForeignKey")
    private Teacher courseTeacher;

    @Column(name = "CAPACITY", nullable = false)
    private int courseCapacity;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "studentCourse")
    private List<Student> courseStudentListForeignKey;

    public Course(int courseID, String courseName, int courseCapacity) {
        this.courseId = courseID;
        this.courseName = courseName;
        this.courseCapacity = courseCapacity;
    }

    @Override
    public String toString() {
        return  courseName;
    }
}
