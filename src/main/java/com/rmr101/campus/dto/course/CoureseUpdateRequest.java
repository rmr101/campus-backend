package com.rmr101.campus.dto.course;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CoureseUpdateRequest {
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
