package com.rmr101.campus.dto.course;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CoursePostRequest {
    @NotNull
    private String name;
    private String introduction;
    private String year;
    private String semester;
    private String location;
    private String learningOutcome;
    private String workLoad;
    private String assessment;
    @NotNull
    private long subjectId;
    //teacher UUID
    private UUID teacherUuid;
}
