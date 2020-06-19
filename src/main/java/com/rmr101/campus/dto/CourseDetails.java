package com.rmr101.campus.dto;

import com.rmr101.campus.entity.Course;
import com.rmr101.campus.entity.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class CourseDetails {
    private CourseDto courseDto;
    List<CourseAssignmentDto> assignmentList;
    List<TeacherDto> teachers;
}
