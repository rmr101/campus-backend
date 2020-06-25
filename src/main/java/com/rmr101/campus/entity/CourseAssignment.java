package com.rmr101.campus.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "course_assignment")
public class CourseAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String content;
//    private Date dueDate;
//    private String acceptanceCriteria;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="courseId", nullable = false)
    private Course course;
}
