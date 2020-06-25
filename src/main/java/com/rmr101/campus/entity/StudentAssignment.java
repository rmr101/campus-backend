package com.rmr101.campus.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student_assignment")//intermediate table of student and course_assignment
public class StudentAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private float score;
    private boolean isSubmit;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="studentUuid", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AssignmentId", nullable = false)
    private CourseAssignment assignment;
}
