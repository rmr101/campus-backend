package com.rmr101.campus.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Data
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private boolean isOpen = true;
    private String name;
    private String location;
    private String year;
    private String semester;


    @Column(length = 12, unique = true)
    private String courseCode;
    @Column(columnDefinition = "text")
    private String introduction;
    @Column(columnDefinition = "text")
    private String learningOutcome;
    @Column(columnDefinition = "text")
    private String workLoad;
    @Column(columnDefinition = "text")
    private String assessment;

    @ManyToOne
    @JoinColumn(name = "subjectId", nullable = false)
    private Subject subject;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "course")
    private List<CourseAssignment> assignments = new ArrayList<CourseAssignment>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "course")
    private List<TeacherCourse> teachers = new ArrayList<TeacherCourse>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<StudentCourse> students = new ArrayList<StudentCourse>();
}
