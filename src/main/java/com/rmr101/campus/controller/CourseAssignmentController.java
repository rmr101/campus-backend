package com.rmr101.campus.controller;

import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetResponse;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentPostRequest;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentPostResponse;
import com.rmr101.campus.service.CourseAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses/assignments")
public class CourseAssignmentController {

    @Autowired
    public CourseAssignmentService courseAssignmentService;

    @GetMapping("{id}")
    public CourseAssignmentGetResponse getCourseAssignmentById(@PathVariable long id){
        return courseAssignmentService.getCourseAssignmentById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CourseAssignmentPostResponse publishAssignment(@RequestBody CourseAssignmentPostRequest request){
        return courseAssignmentService.addAssignment(request);
    }
}
