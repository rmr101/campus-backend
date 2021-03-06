package com.rmr101.campus.dto.subject;

import com.rmr101.campus.dto.course.CourseGetResponse;
import lombok.Data;

import java.util.List;

@Data
public class SubjectGetDetails {
    private SubjectGetResponse subjectGetResponse;
    private List<CourseGetResponse> courseList;
}
