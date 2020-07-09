package com.rmr101.campus.dto.studentAssignment;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StudentAssignmentStudentPutRequest {
    @NotNull(message = "Attachment URl can't be null")
    private String attachmentUrl;
}
