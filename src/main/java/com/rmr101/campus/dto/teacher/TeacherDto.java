package com.rmr101.campus.dto.teacher;

import lombok.Data;

import java.util.UUID;

@Data
public class TeacherDto {
    private UUID uuid;
    private String title;
    private String name;
    private String introduction;
}
