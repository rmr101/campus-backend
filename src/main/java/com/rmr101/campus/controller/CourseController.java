package com.rmr101.campus.controller;

import com.rmr101.campus.dto.CourseDetails;
import com.rmr101.campus.dto.CourseDto;
import com.rmr101.campus.dto.CourseList;
import com.rmr101.campus.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    @ApiOperation(value = "Get all courses, returns an object contains a list of courses")
    public CourseList getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get the course and a list of assignments related to the course given by id")
    public CourseDetails getCourseDetailsById(@PathVariable long id){
        return courseService.getCourseDetailsById(id);
    }

    @PostMapping
    public CourseDto addCourse(@RequestBody CourseDto courseDto){
        return courseService.addCourse(courseDto);
    }


}
