package com.rmr101.campus.dto.studentAssignment;

import lombok.Data;

import java.util.List;

@Data
public class StudentAssignmentList {
    private List<StudentAssignmentGetResponse> assignmentList;
}
