package com.rmr101.campus.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "teacher_course")//intermediate table of teach and course
public class TeacherCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teacherUuid", nullable = false)
    private Teacher teacher;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="courseId", nullable = false)
    private Course course;

}
