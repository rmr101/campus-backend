package com.rmr101.campus.service;

import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetDetails;
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
    private CourseService courseService;

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private StudentAssignmentService studentAssignmentService;

    public CourseAssignmentGetDetails getAssignmentDetails(long assignmentId) {
        CourseAssignment assignment = courseAssignmentRepository.findById(assignmentId).orElseThrow(
                () -> new InvalidIdException("Assignment id doesn't exist."));

        CourseAssignmentGetDetails assignmentDetails = new CourseAssignmentGetDetails();
        assignmentDetails.setAssignment(courseAssignmentMapper.courseAssignmentToCourseAssignmentGetResponse(assignment));
        assignmentDetails.setStudentAssignmentList(
                studentAssignmentService.getAssignmentListByAssignment(assignmentId));

        return assignmentDetails;
    }

    public CourseAssignmentPostResponse addAssignment(CourseAssignmentPostRequest request, long courseId) {
        //validate and get course
        Course course = courseService.validateId(courseId);

        CourseAssignment assignment = courseAssignmentMapper.courseAssignmentPostRequestToCourseAssignment(request);
        assignment.setCourse(course);
        assignment.setCourseName(course.getName());

        courseAssignmentRepository.save(assignment);
        log.debug("Succeed in saving an assignment in course_assignment table");


        //todo:send a message to add assignment to each student related to the course
        //todo:here is an sync implementation

        log.debug("About to save an assignment for every enrolled student");
        course.getStudents().forEach((studentCourse) ->
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
