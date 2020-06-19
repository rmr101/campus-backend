package com.rmr101.campus.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "teacher_course")
public class TeacherCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teacherId")
    private Teacher teacher;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="courseId")
    private Course course;

}
