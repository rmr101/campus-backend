package com.rmr101.campus.dto.course;

import com.rmr101.campus.dto.courseassignment.CourseAssignmentDto;
import com.rmr101.campus.dto.teacher.TeacherDto;
import lombok.Data;

import java.util.List;

@Data
public class CourseDetails {
    private CourseDto courseDto;
    List<CourseAssignmentDto> assignmentList;
    List<TeacherDto> teachers;
}
