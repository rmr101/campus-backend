package com.rmr101.campus.dto.course;

import com.rmr101.campus.dto.subject.SubjectDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@AllArgsConstructor
public class CourseGetResponse {
    private long id;
    private String name;
    private Boolean isOpen;
    private String introduction;
    private String location;
    private String learningOutcome;
    private String workLoad;
    private String assessment;
    private String courseCode;
    private String year;
    private String semester;
    private SubjectDto subject;
}
