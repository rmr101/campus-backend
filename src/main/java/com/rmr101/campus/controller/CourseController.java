package com.rmr101.campus.controller;

import com.rmr101.campus.dto.CourseDetails;
import com.rmr101.campus.dto.CourseDetailsList;
import com.rmr101.campus.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public CourseDetailsList getCourseList(){
        return courseService.getCourseList();
    }

    @PostMapping
    public CourseDetails addCourse(@RequestBody CourseDetails courseDetials){
        return courseService.addCourse(courseDetials);
    }

    @GetMapping("{id}")
    public CourseDetails getCourse(@PathVariable int id){
        return courseService.getCourse(id);
    }

}
