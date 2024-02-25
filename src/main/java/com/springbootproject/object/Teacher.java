package com.springbootproject.object;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TEACHER")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int teacherId;

    @Column(name = "Name", nullable = false)
    private String teacherName;

    @Column(name = "EMAIl", nullable = false)
    private String teacherEmail;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "courseTeacher")
    private List<Course> teacherCourseListForeignKey;

    @Override
    public String toString() {
        return teacherName;
    }

    public Teacher(int teacherId, String teacherName, String teacherEmail) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
    }
}
