package com.rmr101.campus.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(length = 16)
    private UUID uuid;

    @Column(length = 64, unique = true)
    private String email;

    private String password;

    @Column(length = 64, unique = true)
    private String campusId; //student number,teacher numner or staff number

    private boolean isActive;

    @Column(length = 32)
    private String role;
}
