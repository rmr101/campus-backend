package com.rmr101.campus.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TeacherDto {
    private UUID uuid;
    private String name;
    private String introduction;
}
