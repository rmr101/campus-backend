package com.rmr101.campus.controller;

import com.rmr101.campus.dto.courseassignment.CourseAssignmentDto;
import com.rmr101.campus.service.CourseAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses/assignments")
public class CourseAssignmentController {

    @Autowired
    public CourseAssignmentService courseAssignmentService;

    @GetMapping("{id}")
    public CourseAssignmentDto getCourseAssignmentById(@PathVariable long id){
        return courseAssignmentService.getCourseAssignmentById(id);
    }

    @PostMapping
    public CourseAssignmentDto addCourseAssignment(@RequestBody CourseAssignmentDto courseAssignmentDto){
        return courseAssignmentService.addAssignmentCourse(courseAssignmentDto);
    }

}
