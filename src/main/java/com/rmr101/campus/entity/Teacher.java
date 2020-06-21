package com.rmr101.campus.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(length = 16)
    private UUID uuid; //uuid

    private String name;
    private String introduction;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "teacher")
    private List<TeacherCourse> courses = new ArrayList<TeacherCourse>();
}
