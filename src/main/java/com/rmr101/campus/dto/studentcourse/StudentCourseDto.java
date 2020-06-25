package com.rmr101.campus.dto.student;

import lombok.Data;

import java.util.UUID;

@Data
public class StudentCourseDto {
    private UUID id;
    private String studentUuid;
    private long courseId;
}
