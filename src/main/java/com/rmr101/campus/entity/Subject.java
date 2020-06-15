package com.rmr101.campus.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private int id;

    private String name;
    private String introduction;

}
