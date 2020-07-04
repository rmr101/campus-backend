package com.rmr101.campus.controller;

import com.rmr101.campus.dto.course.CourseList;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentStudentPutRequest;
import com.rmr101.campus.dto.teacher.TeacherDetails;
import com.rmr101.campus.dto.teacher.TeacherPostRequest;
import com.rmr101.campus.dto.teacher.TeacherPostResponse;
import com.rmr101.campus.dto.user.UserChangePasswordRequest;
import com.rmr101.campus.service.TeacherCourseService;
import com.rmr101.campus.service.UserService;
import io.swagger.annotations.*;
import com.rmr101.campus.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherCourseService teacherCourseService;

    @GetMapping("{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    public TeacherDetails getTeacherDetails(@PathVariable UUID uuid){
        return teacherService.getTeacherDetails(uuid);
    }

    @GetMapping("/{uuid}/courses")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation( value = "Get list of courses for a selected teacher base on uuid")
    public CourseList getMyCourses(@PathVariable UUID uuid){
        CourseList courseList = new CourseList();
      //  courseList.setCourseList(studentCourseService.getCoursesByStudent(uuid));
        return courseList;
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Change password" ,
            notes = "Returns null.")
    public void changePassword(@PathVariable UUID uuid,@RequestBody UserChangePasswordRequest request){
        teacherService.changePassword(uuid,request);
    }

}
