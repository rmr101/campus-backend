package com.rmr101.campus.dto.courseassignment;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
public class CourseAssignmentPostRequest {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @Future
    @NotNull
    private Date dueDate;
    @NotNull
    private String acceptanceCriteria;
    @NotNull
    private String publisher;
}
