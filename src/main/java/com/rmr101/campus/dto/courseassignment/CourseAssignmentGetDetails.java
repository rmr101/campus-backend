package com.rmr101.campus.dto.courseassignment;

import com.rmr101.campus.dto.studentAssignment.StudentAssignmentGetResponse;
import lombok.Data;

import java.util.List;

@Data
public class CourseAssignmentGetDetails {
    CourseAssignmentGetResponse assignment;
    List<StudentAssignmentGetResponse> studentAssignmentList;
}
