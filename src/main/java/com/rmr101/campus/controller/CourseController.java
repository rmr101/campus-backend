package com.rmr101.campus.controller;

import com.rmr101.campus.dto.CourseDto;
import com.rmr101.campus.dto.CourseList;
import com.rmr101.campus.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public CourseList getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping
    public CourseDto addCourse(@RequestBody CourseDto courseDto){
        return courseService.addCourse(courseDto);
    }

    @GetMapping("{id}")
    public CourseDto getCourseById(@PathVariable int id){
        return courseService.getCourseById(id);
    }

}
