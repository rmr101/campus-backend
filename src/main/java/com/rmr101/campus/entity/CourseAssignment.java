package com.rmr101.campus.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course_assignment")
public class CourseAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String content;
//    private Date dueDate;
//    private String acceptanceCriteria;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="courseId")
    private Course course;
}
