package com.rmr101.campus.dto.course;

import com.rmr101.campus.dto.courseassignment.CourseAssignmentDto;
import com.rmr101.campus.dto.teacher.TeacherDto;
import com.rmr101.campus.entity.Course;
import lombok.Data;

import java.util.List;

@Data
public class CourseDetails {
    private CourseGetResponse course;
    List<CourseAssignmentDto> assignmentList;
    List<TeacherDto> teachers;
}
