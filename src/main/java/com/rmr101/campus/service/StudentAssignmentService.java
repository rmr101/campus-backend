package com.rmr101.campus.service;

import com.rmr101.campus.dto.studentAssignment.StudentAssignmentGetResponse;
import com.rmr101.campus.entity.StudentAssignment;
import com.rmr101.campus.mapper.StudentAssignmentMapper;
import com.rmr101.campus.repository.StudentAssignmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class StudentAssignmentService {

    @Autowired
    private StudentAssignmentRepository studentAssignmentRepository;

    @Autowired
    private StudentAssignmentMapper studentAssignmentMapper;

    public List<StudentAssignmentGetResponse> getAssignmentList(UUID studentUuid) {
        List<StudentAssignment> assignmentsList = studentAssignmentRepository.findByStudentUuid(studentUuid);
        return studentAssignmentMapper.studentAssignmentToStudentAssignmentGetResponse(assignmentsList);
    }
}
