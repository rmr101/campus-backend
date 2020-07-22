package com.rmr101.campus.controller;


import com.rmr101.campus.dto.course.CourseList;
import com.rmr101.campus.dto.student.*;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentGetResponse;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentS3Url;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentStudentPutRequest;
import com.rmr101.campus.dto.studentcourse.StudentCoursePostRequest;
import com.rmr101.campus.dto.studentcourse.StudentCoursePostResponse;
import com.rmr101.campus.dto.teacher.TeacherList;
import com.rmr101.campus.dto.user.UserChangePasswordRequest;
import com.rmr101.campus.entity.Student;
import com.rmr101.campus.service.AmazonWebService;
import com.rmr101.campus.service.StudentAssignmentService;
import com.rmr101.campus.service.StudentCourseService;
import com.rmr101.campus.service.StudentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/students")
@Api(description = "Operations issued by student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private AmazonWebService amazonWebService;

    @Autowired
    private StudentAssignmentService studentAssignmentService;

//    @GetMapping
//    @ResponseStatus(value = HttpStatus.OK)
//    @ApiOperation(value = "Get all students." ,
//            notes = "Returns an object contains a list of students.")
//    public StudentList getAllStudents(){
//
//        StudentList studentList = new StudentList();
//        studentList.setStudentList(studentService.getAllStudents());
//
//        return studentList;
//    }

    @GetMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Get student details by uuid." ,
            notes = "Returns an object that contain a the student with the specific ID.")
    public StudentGetDetails getStudentDetails(@PathVariable UUID uuid,
                                               @RequestParam(required = false) String detail){
        return studentService.getStudentDetailsByID(uuid,detail);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Update Personal info" ,
            notes = "Returns null.")
    public void updateInfo(@PathVariable UUID uuid,@Validated @RequestBody StudentUpdateRequest request){
        studentService.updateStudent(uuid,request);
    }

    @PutMapping("/{uuid}/password")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Change password" ,
            notes = "Returns null.")
    public void changePassword(@PathVariable UUID uuid,@Validated @RequestBody UserChangePasswordRequest request){
        studentService.changePassword(uuid,request);
    }

    @GetMapping("/assignments/{assignmentId}")
    @ApiOperation(value = "get an assignment by id",notes = "Role: student")
    public StudentAssignmentGetResponse getAssignment(@PathVariable long assignmentId){
        return studentAssignmentService.getAssignment(assignmentId);
    }

    @PutMapping("/assignments/{assignmentId}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "submit an assignment",notes = "Role: student")
    public StudentAssignmentS3Url submitAssignment(@Validated @RequestBody StudentAssignmentStudentPutRequest request,
                                                   @PathVariable long assignmentId){

        studentAssignmentService.submitAssignment(assignmentId, request);

        //this url is actually the objectKey.

        return amazonWebService.preSignedUploadedUrl(request.getAttachmentUrl());
    }

    //course enrollment
    @PostMapping("/{uuid}/courses")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation( value = "Enroll a course")
    public StudentCoursePostResponse enrollCourse(@Validated @RequestBody StudentCoursePostRequest request){
        return studentCourseService.addCourse(request);
    }
}