package com.rmr101.campus.controller;

import com.rmr101.campus.dto.teachercourse.TeacherCoursePostRequest;
import com.rmr101.campus.dto.teachercourse.TeacherCoursePostResponse;
import com.rmr101.campus.service.TeacherCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherCourseController {
    @Autowired
    private TeacherCourseService teacherCourseService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation( value = "Add a teacher to selected course")
    public TeacherCoursePostResponse enrollCourse(@RequestBody TeacherCoursePostRequest request){
        return teacherCourseService.addCourse(request);
    }
}
