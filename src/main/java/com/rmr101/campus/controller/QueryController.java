package com.rmr101.campus.controller;

import com.rmr101.campus.dto.course.CourseList;
import com.rmr101.campus.dto.student.StudentList;
import com.rmr101.campus.dto.teacher.TeacherList;
import com.rmr101.campus.service.CourseService;
import com.rmr101.campus.service.StudentService;
import com.rmr101.campus.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/query")
public class QueryController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Query courses by name/code")
    public CourseList findCoursesBy(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) String code){
        CourseList courseList = new CourseList();
        courseList.setCourseList(courseService.findCoursesBy(name,code));
        return courseList;
    }

    @GetMapping("/students")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Query student by name/campusId")
    public StudentList findStudentBy(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String campusId){
        StudentList studentList = new StudentList();
        studentList.setStudentList(studentService.findStudentBy(name,campusId));
        return studentList;
    }

    @GetMapping("/teachers")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Query teacher by name/campusId")
    public TeacherList findTeacherBy(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String campusId){
        TeacherList teacherList = new TeacherList();
        teacherList.setTeacherList(teacherService.findTeacherBy(name,campusId));
        return teacherList;
    }

}

