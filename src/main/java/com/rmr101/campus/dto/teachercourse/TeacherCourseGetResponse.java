package com.rmr101.campus.dto.teachercourse;

import lombok.Data;
import java.util.UUID;

@Data
public class TeacherCourseGetResponse {
    private long id;
    private long courseId;
    private UUID teacherUuid;
}
