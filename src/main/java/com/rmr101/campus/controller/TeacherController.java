package com.rmr101.campus.controller;

import com.rmr101.campus.dto.studentAssignment.StudentAssignmentS3Url;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentTeacherPutRequest;
import com.rmr101.campus.dto.teacher.TeacherGetDetails;
import com.rmr101.campus.dto.teacher.TeacherUpdateRequest;
import com.rmr101.campus.dto.user.UserChangePasswordRequest;
import com.rmr101.campus.service.AmazonWebService;
import com.rmr101.campus.service.StudentAssignmentService;
import com.rmr101.campus.service.TeacherCourseService;
import io.swagger.annotations.*;
import com.rmr101.campus.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherCourseService teacherCourseService;


    @Autowired
    private AmazonWebService amazonWebService;

    @Autowired
    private StudentAssignmentService studentAssignmentService;

    @GetMapping("{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Get teacher details by uuid." ,
            notes = "Returns an object that contain a the student with the specific ID.")
    public TeacherGetDetails getTeacherDetails(@PathVariable UUID uuid,
                                               @RequestParam(required = false) String detail){
        return teacherService.getTeacherDetailsByID(uuid,detail);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Update Personal info" ,
            notes = "Returns null.")
    public void updateInfo(@PathVariable UUID uuid,@Validated @RequestBody TeacherUpdateRequest request){
        teacherService.updateTeacher(uuid,request);
    }

    @PutMapping("/{uuid}/password")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Change password" ,
            notes = "Returns null.")
    public void changePassword(@PathVariable UUID uuid,@Validated @RequestBody UserChangePasswordRequest request){
        teacherService.changePassword(uuid,request);
    }

    @PutMapping("/assignments/{assignmentId}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "review and score an assignment submitted by student" ,
            notes = "Returns null.")
    public void reviewAssignment(@PathVariable long assignmentId,
                                 @Validated @RequestBody StudentAssignmentTeacherPutRequest request){
        studentAssignmentService.reviewAssignment(assignmentId,request);
    }
    @GetMapping("/assignments")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "get a download link for an assignment submitted by student" ,
        notes = "Returns url")
    public StudentAssignmentS3Url getAssignmentUrl(@RequestParam String objectKey){

        return amazonWebService.preSignedGetUrl(objectKey);
    }



}
