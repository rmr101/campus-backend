package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.studentAssignment.StudentAssignmentGetResponse;
import com.rmr101.campus.entity.StudentAssignment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentAssignmentMapper {
    public StudentAssignmentGetResponse studentAssignmentToStudentAssignmentGetResponse(StudentAssignment assignment);
    public List<StudentAssignmentGetResponse> studentAssignmentToStudentAssignmentGetResponse(List<StudentAssignment> list);
}
