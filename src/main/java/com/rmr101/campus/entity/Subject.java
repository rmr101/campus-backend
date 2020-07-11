package com.rmr101.campus.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(unique = true)
    private String name;

    @Column(length = 3, unique = true)
    private String subjectCode;

    private String introduction;
    private int counter;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "subject")
    private List<Course> courses = new ArrayList<Course>();
}
