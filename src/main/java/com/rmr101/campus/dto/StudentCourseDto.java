package com.rmr101.campus.dto;

import lombok.Data;

@Data
public class StudentCourseDto {
    private long id;
    private String studentUuid;
    private long courseId;
}
