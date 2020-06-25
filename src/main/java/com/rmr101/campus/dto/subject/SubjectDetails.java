package com.rmr101.campus.dto.subject;

import com.rmr101.campus.dto.course.CourseDto;
import lombok.Data;

import java.util.List;

@Data
public class SubjectDetails {
    private SubjectDto subjectDto;
    private List<CourseDto> courseList;
}
