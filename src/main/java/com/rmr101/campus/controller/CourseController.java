package com.rmr101.campus.controller;

import com.rmr101.campus.dto.course.*;
import com.rmr101.campus.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Get all courses, returns an object contains a list of courses")
    public CourseList getAllCourses(){
        CourseList courseList = new CourseList();
        courseList.setCourseList(courseService.getAllCourses());
        return courseList;
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Get the course and a list of assignments related to the course given by id")
    public CourseDetails getCourseDetailsById(@PathVariable long id){
        return courseService.getCourseDetailsById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a new course")
    public CoursePostResponse addCourse(@RequestBody CoursePostRequest request){
        return courseService.addCourse(request);
    }


}
