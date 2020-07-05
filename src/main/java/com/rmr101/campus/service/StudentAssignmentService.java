package com.rmr101.campus.service;

import com.rmr101.campus.dto.studentAssignment.StudentAssignmentGetResponse;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentStudentPutRequest;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentTeacherPutRequest;
import com.rmr101.campus.entity.CourseAssignment;
import com.rmr101.campus.entity.Student;
import com.rmr101.campus.entity.StudentAssignment;
import com.rmr101.campus.exception.InvalidIdException;
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

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseAssignmentService courseAssignmentService;

    public List<StudentAssignmentGetResponse> getAssignmentList(UUID studentUuid) {
        List<StudentAssignment> assignmentsList = studentAssignmentRepository.findByStudentUuid(studentUuid);
        return studentAssignmentMapper.studentAssignmentToStudentAssignmentGetResponse(assignmentsList);
    }

    //updated by student
    public void updateAssignmentByStudent(UUID studentUuid,StudentAssignmentStudentPutRequest request){
        //validate
        StudentAssignment assignment = studentAssignmentRepository.findById(request.getId())
                .orElseThrow(()-> new InvalidIdException("The student doesn't have this assignment"));

        assignment.setAttachmentUrl(request.getAttachmentUrl());
        assignment.setSubmitted(true);

        studentAssignmentRepository.save(assignment);
        //todo:inform teacher
    }

    //updated by teacher
//    public void updateAssignmentByTeacher(UUID TeacherUuid,StudentAssignmentTeacherPutRequest request){
//        //validate
//        StudentAssignment assignment = studentAssignmentRepository.findById(request.getId())
//                .orElseThrow(()-> new InvalidIdException());
//
//        assignment.setComment(request.getComment());
//        assignment.setScore(request.getScore());
//        assignment.setScored(request.isScored());
//
//        studentAssignmentRepository.save(assignment);
//    }

    //insert an assignment into student_assignment table
    protected StudentAssignment addAssignment(UUID studentUuid, long courseAssignmentId){
        //validate studentUuid
        log.debug("Add assignment: student uuid = "+ studentUuid +" ,courseAssignment id = "+ courseAssignmentId);
        Student student = studentService.validateUuid(studentUuid);
        CourseAssignment courseAssignment = courseAssignmentService.validateId(courseAssignmentId);

        StudentAssignment assignment = new StudentAssignment();
        assignment.setSubmitted(false);
        assignment.setScored(false);
        assignment.setAssignment(courseAssignment);
        assignment.setStudent(student);

        studentAssignmentRepository.save(assignment);
        log.debug("Succeed in saving");
        return assignment;
    }

}
