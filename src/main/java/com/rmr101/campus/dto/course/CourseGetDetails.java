package com.rmr101.campus.dto.course;


import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetResponse;
import com.rmr101.campus.dto.student.StudentGetResponse;
import com.rmr101.campus.dto.teacher.TeacherGetResponse;
import lombok.Data;

import java.util.List;

@Data
public class CourseGetDetails {
    private CourseGetResponse course;
    List<CourseAssignmentGetResponse> assignmentList;
    List<TeacherGetResponse> teacherList;
    List<StudentGetResponse> studentList;
}
