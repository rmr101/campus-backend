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

    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="course_id",insertable = false,updatable = false)
    private Course course;
}
