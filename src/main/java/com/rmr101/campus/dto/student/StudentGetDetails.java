package com.rmr101.campus.dto.student;

import com.rmr101.campus.dto.course.CourseGetResponse;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentGetResponse;
import lombok.Data;

import java.util.List;

@Data
public class StudentGetDetails {
    private StudentGetResponse studentInfo;
    List<CourseGetResponse> courseList;
    List<StudentAssignmentGetResponse> assignmentList;
}
