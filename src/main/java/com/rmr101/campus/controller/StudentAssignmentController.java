package com.rmr101.campus.controller;

import com.rmr101.campus.dto.studentAssignment.StudentAssignmentGetResponse;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentList;
import com.rmr101.campus.entity.Student;
import com.rmr101.campus.entity.StudentAssignment;
import com.rmr101.campus.service.StudentAssignmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentAssignmentController {

    @Autowired
    private StudentAssignmentService studentAssignmentService;

//    @GetMapping("{assignmentId}/assignments")
//    public StudentAssignmentGetResponse getAssignmentListByAssignment(@PathVariable UUID studentUuid,
//                                                               @PathVariable long assignmentId){
//        return studentAssignmentService.getStudentAssignment()
//    }

    @GetMapping("{studentUuid}/assignments")
    @ApiOperation(value = "get the assignment list for a student specified by uuid")
    public StudentAssignmentList getAssignmentListByStudent(@PathVariable UUID studentUuid){
        StudentAssignmentList assignmentList = new StudentAssignmentList();
        assignmentList.setAssignmentList(studentAssignmentService.getAssignmentList(studentUuid));
        return assignmentList;
    }

}
