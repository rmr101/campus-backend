package com.rmr101.campus.dto.courseassignment;

import com.rmr101.campus.dto.course.CourseGetResponse;
import com.rmr101.campus.entity.Course;
import lombok.Data;

import java.util.Date;

@Data
public class CourseAssignmentGetResponse {
    private long id;
    private String courseName;
    private String publisher;
    private String title;
    private String content;
    private Date dueDate;
    private String acceptanceCriteria;
}
