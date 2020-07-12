package com.rmr101.campus.dto.studentcourse;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class StudentCoursePostRequest {
    @NotNull
    private UUID studentUuid;
    @NotNull
    private long courseId;
}
