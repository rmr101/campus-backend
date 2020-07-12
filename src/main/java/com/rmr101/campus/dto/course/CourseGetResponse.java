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
    private String introduction;
    private SubjectDto subject;
}
