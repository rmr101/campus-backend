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
    private boolean isActive = true;

    private String title;
    private String firstName;
    private String lastName;
    private String name;
    private String avatar;

    private String introduction;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "teacher")
    private List<TeacherCourse> courses = new ArrayList<TeacherCourse>();
}
