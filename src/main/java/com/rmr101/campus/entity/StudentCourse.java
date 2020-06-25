package com.rmr101.campus.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student_course")//intermidiate table of student and course
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="courseId", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="studentUuid", nullable = false)
    private Student student;
}
