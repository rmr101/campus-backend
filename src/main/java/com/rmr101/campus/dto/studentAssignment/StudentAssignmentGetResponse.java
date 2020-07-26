package com.rmr101.campus.dto.studentAssignment;

import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetResponse;

import lombok.Data;

import java.util.Date;

@Data
public class StudentAssignmentGetResponse {
    private long id;
    private boolean isSubmitted;
    private String attachmentUrl;
    private boolean isScored;
    private int score;
    private String comment;

    private long courseAssignmentId;
    private String courseName;
    private String publisher;
    private String title;
    private String content;
    private Date dueDate;
    private String acceptanceCriteria;

//    private CourseAssignmentGetResponse assignment;
}
