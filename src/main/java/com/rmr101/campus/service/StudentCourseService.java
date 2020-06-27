package com.rmr101.campus.service;

import com.rmr101.campus.dto.studentcourse.StudentCoursePostRequest;
import com.rmr101.campus.dto.studentcourse.StudentCoursePostResponse;
import com.rmr101.campus.entity.StudentCourse;
import com.rmr101.campus.exception.RecordAlreadyExistException;
import com.rmr101.campus.mapper.StudentCourseMapper;
import com.rmr101.campus.repository.StudentCourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class StudentCourseService {
    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    public StudentCoursePostResponse addCourse(StudentCoursePostRequest request) {
        //valid check
        courseService.validateId(request.getCourseId());
        studentService.validateUuid(request.getStudentUuid());

        //duplicate check
        if(this.checkDuplicate(request.getStudentUuid(),request.getCourseId()) != null) {
            log.debug("Course already exist");
            throw new RecordAlreadyExistException();
        }

        log.debug("About to save course : "+ request.getCourseId() +" to student: " + request.getStudentUuid());
        StudentCourse course  = studentCourseMapper.studentCoursePostRequestToStudentCourse(request);
        studentCourseRepository.save(course);
        return studentCourseMapper.studentCourseToStudentCoursePostResponse(course);
    }

    protected StudentCourse checkDuplicate(UUID studentUuid, long courseId){
        return studentCourseRepository.findByStudentUuidAndCourseId(studentUuid,courseId);
    }
}
