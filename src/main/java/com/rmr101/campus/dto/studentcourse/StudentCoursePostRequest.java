package com.rmr101.campus.dto.studentcourse;

import lombok.Data;

import java.util.UUID;

@Data
public class StudentCoursePostRequest {
    private UUID studentUuid;
    private long courseId;
}
