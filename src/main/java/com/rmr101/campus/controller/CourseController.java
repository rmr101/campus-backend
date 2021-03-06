package com.rmr101.campus.controller;

import com.rmr101.campus.dto.course.*;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetDetails;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentPostRequest;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentPostResponse;
import com.rmr101.campus.dto.teachercourse.TeacherCoursePostRequest;
import com.rmr101.campus.dto.teachercourse.TeacherCoursePostResponse;
import com.rmr101.campus.service.CourseAssignmentService;
import com.rmr101.campus.service.CourseService;
import com.rmr101.campus.service.TeacherCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    public CourseAssignmentService courseAssignmentService;

    @Autowired
    private TeacherCourseService teacherCourseService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a new course ----Role:admin")
    public CoursePostResponse addCourse(@Validated @RequestBody CoursePostRequest request){
        return courseService.addCourse(request);
    }

    @PutMapping("/{courseId}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "update course details", notes = "Returns null.")
    public void updateCourse(@PathVariable long courseId,
                             @Validated @RequestBody CoursePutRequest request){
        courseService.updateCourse(courseId, request);
    }

    @GetMapping("{courseId}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Get the course and a list of assignments related to the course given by id",
            notes = "optional parameter detail : students, teachers, assignments and all")
    public CourseGetDetails getCourseDetailsById(@PathVariable long courseId,
                                                 @RequestParam(required = false) String detail){
        return courseService.getCourseDetailsById(courseId,detail);
    }

    @DeleteMapping("{courseId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "close a course (soft delete)", notes = "return null")
    public void deleteCourse(@PathVariable long courseId){
        courseService.deleteCourse(courseId);
    }

    @PostMapping("{courseId}/teachers")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation( value = "Add a teacher to selected course ----Role:admin")
    public TeacherCoursePostResponse addTeacherToCourse(@Validated @RequestBody TeacherCoursePostRequest request){
        return teacherCourseService.addCourse(request);
    }

    @DeleteMapping("{courseId}/teachers/{uuid}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "remove a teacher from a course",
            notes = "return null")
    public void removeTeacherFromCourse(@PathVariable long courseId,
                                    @PathVariable UUID teacherUuid){
        teacherCourseService.deleteTeacherCourse(courseId, teacherUuid);
    }

    @PostMapping("{courseId}/assignments")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "publish an assignment ----Role:teacher")
    public CourseAssignmentPostResponse publishAssignment(@Validated @RequestBody CourseAssignmentPostRequest request,
                                                          @PathVariable long courseId){
        return courseAssignmentService.addAssignment(request, courseId);
    }

    @GetMapping("{courseId}/assignments/{assignmentId}")
    public CourseAssignmentGetDetails getCourseAssignmentById(@PathVariable long assignmentId){
        return courseAssignmentService.getAssignmentDetails(assignmentId);
    }
}
