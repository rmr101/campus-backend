package com.rmr101.campus.controller;

import com.rmr101.campus.dto.studentcourse.StudentCoursePostRequest;
import com.rmr101.campus.dto.studentcourse.StudentCoursePostResponse;
import com.rmr101.campus.service.StudentCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students/courses")
public class StudentCourseController {
    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation( value = "Add a selected course id for a selected student base on uuid")
    public StudentCoursePostResponse enrollCourse(@RequestBody StudentCoursePostRequest request){
        return studentCourseService.addCourse(request);
    }
}