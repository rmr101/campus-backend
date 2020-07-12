package com.rmr101.campus.dto.course;

import lombok.Data;

@Data
public class CoursePostRequest {
    private String name;
    private String introduction;
    private String year;
    private String semester;
    private String location;
    private String learningOutcome;
    private String workLoad;
    private String assessment;
    private long subjectId;
}
