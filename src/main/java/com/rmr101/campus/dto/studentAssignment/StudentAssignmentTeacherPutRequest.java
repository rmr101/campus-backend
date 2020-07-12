package com.rmr101.campus.dto.studentAssignment;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class StudentAssignmentTeacherPutRequest {
    @NotNull()
    @Max(100)
    @Min(0)
    private int score;
    private String comment;
}
