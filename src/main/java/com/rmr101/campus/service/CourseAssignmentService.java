package com.rmr101.campus.service;

import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetResponse;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentPostRequest;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentPostResponse;
import com.rmr101.campus.entity.*;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.CourseAssignmentMapper;
import com.rmr101.campus.repository.CourseAssignmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseAssignmentService {

    @Autowired
    private CourseAssignmentRepository courseAssignmentRepository;

    @Autowired
    private CourseAssignmentMapper courseAssignmentMapper;

    @Autowired
    private TeacherCourseService teacherCourseService;

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private StudentAssignmentService studentAssignmentService;

    public CourseAssignmentGetResponse getCourseAssignmentById(long id) {
        return courseAssignmentMapper.courseAssignmentToCourseAssignmentGetResponse(this.validateId(id));
    }

    public CourseAssignmentPostResponse addAssignment(CourseAssignmentPostRequest request) {
        //validate and get teacherCourse
        TeacherCourse teacherCourse = teacherCourseService.getTeacherCourseById(request.getTeacherCourseId());

        CourseAssignment assignment = courseAssignmentMapper.courseAssignmentPostRequesttoCourseAssignment(request);
        assignment.setCourse(teacherCourse.getCourse());
        assignment.setCourseName(teacherCourse.getCourse().getName());
        assignment.setTeacher(teacherCourse.getTeacher());
        assignment.setPublisher(teacherCourse.getTeacher().getName());

        courseAssignmentRepository.save(assignment);
        log.debug("Succeed in saving an assignment in course_assignment table");

        //todo:send a message to add assignment to each student related to the course
        //todo:here is an sync implementation
        List<StudentCourse> studentList = studentCourseService.getListByCourse(teacherCourse.getCourse().getId());
        log.debug("Succeed in getting student list");

        studentList.forEach((studentCourse) ->
                studentAssignmentService.addAssignment(
                        studentCourse.getStudent().getUuid(),
                        assignment.getId()
                ));
        log.debug("Succeed in saving an assignment in student_assignment table for every enrolled student");
        return courseAssignmentMapper.courseAssignmentToCourseAssignmentPostResponse(assignment);
    }

    protected CourseAssignment validateId( long id){
        return courseAssignmentRepository.findById(id).orElseThrow(()-> new InvalidIdException("The course doesn't have this assignment"));
    }
}
