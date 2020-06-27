package com.rmr101.campus.service;

import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetResponse;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentPostRequest;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentPostResponse;
import com.rmr101.campus.entity.CourseAssignment;
import com.rmr101.campus.entity.Teacher;
import com.rmr101.campus.entity.TeacherCourse;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.CourseAssignmentMapper;
import com.rmr101.campus.repository.CourseAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseAssignmentService {

    @Autowired
    private CourseAssignmentRepository courseAssignmentRepository;

    @Autowired
    private CourseAssignmentMapper courseAssignmentMapper;

    @Autowired
    private TeacherCourseService teacherCourseService;

    public CourseAssignmentGetResponse getCourseAssignmentById(long id) {
        CourseAssignment courseAssignment = courseAssignmentRepository.findById(id).orElseThrow(() -> new InvalidIdException());
        return courseAssignmentMapper.courseAssignmentToCourseAssignmentGetResponse(courseAssignment);
    }

    public CourseAssignmentPostResponse addAssignment(CourseAssignmentPostRequest request) {
        //validate and get teacherCourse
        TeacherCourse teacherCourse = teacherCourseService.getTeacherCourseById(request.getTeacherCourseId());

        CourseAssignment assignment = courseAssignmentMapper.courseAssignmentPostRequesttoCourseAssignment(request);
        assignment.setCourse(teacherCourse.getCourse());
        assignment.setTeacher(teacherCourse.getTeacher());
        assignment.setPublisher(teacherCourse.getTeacher().getName());

        courseAssignmentRepository.save(assignment);
        //todo:send a message to add assignment to each student related to the course
        return courseAssignmentMapper.courseAssignmentToCourseAssignmentPostResponse(assignment);
    }
}
