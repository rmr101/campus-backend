package com.rmr101.campus.controller;

import com.rmr101.campus.dto.CourseDto;
import com.rmr101.campus.service.CourseAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses/assignments")
public class CourseAssignmentController {

    @Autowired
    public CourseAssignmentService courseAssignmentService;

    @GetMapping("{id}")
    public CourseDto getCourseById(@PathVariable int id){
        return courseAssignmentService.getCourseAssignmentById(id);
    }
}
