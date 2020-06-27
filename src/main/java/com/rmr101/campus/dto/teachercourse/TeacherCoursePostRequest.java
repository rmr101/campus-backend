package com.rmr101.campus.dto.teachercourse;

import lombok.Data;

import java.util.UUID;

@Data
public class TeacherCoursePostRequest {
    private UUID teacherUuid;
    private long courseId;
}
