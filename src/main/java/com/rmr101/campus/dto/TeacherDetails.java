package com.rmr101.campus.dto;

import com.rmr101.campus.entity.Course;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeacherDetails {
    private String uuid;
    private String name;
    private List<CourseDto> courseList;
}
