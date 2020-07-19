package com.rmr101.campus.dto.teachercourse;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class TeacherCourseDeleteRequest {
    @NotNull
    private long courseId;
    @NotNull
    private UUID teacherUuid;
}
