package com.rmr101.campus.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@AllArgsConstructor
public class CourseDto {
    private long id;
    private String name;
    private String introduction;

    private long subjectId;
}
