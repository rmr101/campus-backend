package com.rmr101.campus.dto.studentAssignment;

import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetResponse;

import lombok.Data;

@Data
public class StudentAssignmentGetResponse {
    private long id;
    private boolean isSubmitted;
    private String attachmentUrl;
    private boolean isScored;
    private int score;
    private String comment;

    private CourseAssignmentGetResponse assignment;
}
