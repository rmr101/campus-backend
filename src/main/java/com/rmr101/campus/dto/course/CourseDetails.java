package com.rmr101.campus.dto.course;


import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetResponse;
import com.rmr101.campus.dto.teacher.TeacherDto;
import com.rmr101.campus.entity.Course;
import lombok.Data;

import java.util.List;

@Data
public class CourseDetails {
    private CourseGetResponse course;
    List<CourseAssignmentGetResponse> assignmentList;
    List<TeacherDto> teachers;
}
