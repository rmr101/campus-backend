package com.rmr101.campus.controller;

import com.rmr101.campus.dto.studentcourse.StudentCourseDto;
import com.rmr101.campus.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students/courses")
public class StudentCourseController {
    @Autowired
    private StudentCourseService studentCourseService;

//    @PostMapping
//    public StudentCourseDto courseEnrollment(@RequestBody StudentCourseDto studentCourseDto){
//        return studentCourseService.addStudentCourse(studentCourseDto);
//    }
}