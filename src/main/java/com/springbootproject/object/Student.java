package com.springbootproject.object;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int studentId;

    @Column(name = "NAME", nullable = false)
    private String studentName;

    @Column(name = "AGE", nullable = false)
    private int studentAge;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "courseStudentListForeignKey")
    private Course studentCourse;

    @Column(name = "EMAIL", nullable = false)
    private String studentEmail;

    @Column(name = "EMAIL", nullable = false)
    private int phoneNumber;

    public Student(int studentId, String studentName, int studentAge, String studentEmail) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentEmail = studentEmail;
    }

    @Override
    public String toString(){
        return studentName;
    }
}
