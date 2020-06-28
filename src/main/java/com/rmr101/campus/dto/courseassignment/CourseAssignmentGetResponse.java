package com.rmr101.campus.dto.courseassignment;

import lombok.Data;

import java.util.Date;

@Data
public class CourseAssignmentGetResponse {
    private long id;
    private String publisher;
    private String title;
    private String content;
    private Date dueDate;
    private String acceptanceCriteria;
    private long courseId;
}
