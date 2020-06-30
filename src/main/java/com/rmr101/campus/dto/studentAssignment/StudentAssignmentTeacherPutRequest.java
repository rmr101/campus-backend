package com.rmr101.campus.dto.studentAssignment;

import lombok.Data;

@Data
public class StudentAssignmentTeacherPutRequest {
    private long id;
    private boolean isScored;
    private float score;
    private String comment;
}
