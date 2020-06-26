package com.rmr101.campus.dto.courseassignment;

import lombok.Data;

@Data
public class CourseAssignmentDto {
    private long id;
    private String title;
    private String content;

    private long courseId;
}
