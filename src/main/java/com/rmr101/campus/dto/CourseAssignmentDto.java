package com.rmr101.campus.dto;

import lombok.Data;

@Data
public class CourseAssignmentDto {
    private long id;
    private String title;
    private String content;

    private long courseId;
}
