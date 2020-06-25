package com.rmr101.campus.dto;

import com.rmr101.campus.entity.Subject;
import lombok.Data;

import java.util.List;

@Data
public class SubjectDetails {
    private SubjectDto subjectDto;
    private List<CourseDto> courseList;
}
