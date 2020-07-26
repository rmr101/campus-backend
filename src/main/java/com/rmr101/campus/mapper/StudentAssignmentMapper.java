package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetResponse;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentGetResponse;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentStudentPutRequest;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentTeacherPutRequest;
import com.rmr101.campus.entity.CourseAssignment;
import com.rmr101.campus.entity.StudentAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentAssignmentMapper {
    public CourseAssignmentGetResponse courseAssignmentToCourseAssignmentGetResponse(CourseAssignment courseAssignment);
    @Mappings({
            @Mapping(target="courseAssignmentId", source="assignment.id"),
            @Mapping(target="courseName", source="assignment.courseName"),
            @Mapping(target="publisher", source="assignment.publisher"),
            @Mapping(target="title", source="assignment.title"),
            @Mapping(target="content", source="assignment.content"),
            @Mapping(target="dueDate", source="assignment.dueDate"),
            @Mapping(target="acceptanceCriteria", source="assignment.acceptanceCriteria"),
    })
    public StudentAssignmentGetResponse studentAssignmentToStudentAssignmentGetResponse(StudentAssignment assignment);
    public List<StudentAssignmentGetResponse> studentAssignmentToStudentAssignmentGetResponse(List<StudentAssignment> list);
    public StudentAssignment studentAssignmentStudentPutRequestToStudentAssignment(
            StudentAssignmentStudentPutRequest studentRequest);
    public StudentAssignment studentAssignmentTeacherPutRequestToStudentAssignment(
            StudentAssignmentTeacherPutRequest teacherRequest);
}
