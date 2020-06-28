package com.rmr101.campus.dto.courseassignment;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class CourseAssignmentPostRequest {
    private String title;
    private String content;
    private Date dueDate;
    private String acceptanceCriteria;
    private long teacherCourseId;
}
