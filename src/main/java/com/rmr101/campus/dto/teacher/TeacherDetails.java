package com.rmr101.campus.dto.teacher;

import com.rmr101.campus.dto.course.CourseDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TeacherDetails {
    private UUID uuid;
    private String name;
    private List<CourseDto> courseList;
}
