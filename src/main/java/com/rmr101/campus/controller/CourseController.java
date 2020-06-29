package com.rmr101.campus.controller;

import com.rmr101.campus.dto.course.*;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetResponse;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentPostRequest;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentPostResponse;
import com.rmr101.campus.dto.teachercourse.TeacherCoursePostRequest;
import com.rmr101.campus.dto.teachercourse.TeacherCoursePostResponse;
import com.rmr101.campus.entity.Teacher;
import com.rmr101.campus.entity.TeacherCourse;
import com.rmr101.campus.service.CourseAssignmentService;
import com.rmr101.campus.service.CourseService;
import com.rmr101.campus.service.TeacherCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    public CourseAssignmentService courseAssignmentService;

    @Autowired
    private TeacherCourseService teacherCourseService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Get all courses, returns an object contains a list of courses")
    public CourseList getAllCourses(){
        CourseList courseList = new CourseList();
        courseList.setCourseList(courseService.getAllCourses());
        return courseList;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a new course ----Role:admin")
    public CoursePostResponse addCourse(@RequestBody CoursePostRequest request){
        return courseService.addCourse(request);
    }

    @GetMapping("{courseId}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Get the course and a list of assignments related to the course given by id")
    public CourseDetails getCourseDetailsById(@PathVariable long id){
        return courseService.getCourseDetailsById(id);
    }

    @PostMapping("{courseId}/teachers")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation( value = "Add a teacher to selected course ----Role:admin")
    public TeacherCoursePostResponse addTeacherToCourse(@RequestBody TeacherCoursePostRequest request){
        return teacherCourseService.addCourse(request);
    }

    @PostMapping("{courseId}/assignments")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "publish an assignment ----Role:teacher")
    public CourseAssignmentPostResponse publishAssignment(@RequestBody CourseAssignmentPostRequest request,
                                                          @PathVariable long courseId){
        return courseAssignmentService.addAssignment(request);
    }

    @GetMapping("{courseId}/assignments/{assignmentId}")
    public CourseAssignmentGetResponse getCourseAssignmentById(@PathVariable long courseId,
                                                               @PathVariable long assignmentId){
        return courseAssignmentService.getCourseAssignmentById(assignmentId);
    }
}
