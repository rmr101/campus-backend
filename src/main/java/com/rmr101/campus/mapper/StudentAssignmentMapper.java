package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetResponse;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentGetResponse;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentStudentPutRequest;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentTeacherPutRequest;
import com.rmr101.campus.entity.CourseAssignment;
import com.rmr101.campus.entity.StudentAssignment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentAssignmentMapper {
    public CourseAssignmentGetResponse courseAssignmentToCourseAssignmentGetResponse(CourseAssignment courseAssignment);
    public StudentAssignmentGetResponse studentAssignmentToStudentAssignmentGetResponse(StudentAssignment assignment);
    public List<StudentAssignmentGetResponse> studentAssignmentToStudentAssignmentGetResponse(List<StudentAssignment> list);

    public StudentAssignment studentAssignmentStudentPutRequestToStudentAssignment(
            StudentAssignmentStudentPutRequest studentRequest);

    public StudentAssignment studentAssignmentTeacherPutRequestToStudentAssignment(
            StudentAssignmentTeacherPutRequest teacherRequest);
}
