package com.rmr101.campus.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "course_assignment")
public class CourseAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String publisher;
    private String courseName;
    private String title;
    private String content;

    @Type(type = "date")
    private Date dueDate;

    @Column(name = "ac")
    private String acceptanceCriteria;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="courseId", nullable = false)
    private Course course;

//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="teacherUuid", nullable = false)
//    private Teacher teacher;
}
