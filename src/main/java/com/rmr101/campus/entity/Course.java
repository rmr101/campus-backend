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

    private String name;
    private String introduction;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<CourseAssignment> assignments = new ArrayList<CourseAssignment>();
}
