package com.rmr101.campus.dto.course;

import com.rmr101.campus.dto.subject.SubjectGetResponse;
import lombok.Data;

@Data
//@AllArgsConstructor
public class CourseGetResponse {
    private long id;
    private String name;
    private boolean isOpen;
    private String introduction;
    private String location;
    private String learningOutcome;
    private String workLoad;
    private String assessment;
    private String courseCode;
    private String year;
    private String semester;
    private SubjectGetResponse subject;
}
