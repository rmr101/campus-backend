package com.rmr101.campus.dto.course;

import com.rmr101.campus.dto.subject.SubjectDto;
import lombok.Data;

@Data
public class CoursePostRequest {
    private String name;
    private String introduction;
    private long yearSemester;
    private String location;
    private String learningOutcome;
    private String workLoad;
    private String assessment;
    private long subjectId;
}
