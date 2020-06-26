package com.rmr101.campus.dto.course;

import lombok.Data;

@Data
public class CoursePostRequest {
    private String name;
    private String introduction;
    private long subjectId;
}
