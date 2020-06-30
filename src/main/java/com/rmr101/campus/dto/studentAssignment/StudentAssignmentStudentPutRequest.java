package com.rmr101.campus.dto.studentAssignment;

import lombok.Data;

@Data
public class StudentAssignmentStudentPutRequest {
    private long id;
    private boolean isSubmitted;
    private String answer;
    private String attachmentUrl;
}
