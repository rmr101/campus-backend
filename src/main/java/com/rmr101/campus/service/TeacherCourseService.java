package com.rmr101.campus.service;

import com.rmr101.campus.dto.teachercourse.TeacherCoursePostRequest;
import com.rmr101.campus.dto.teachercourse.TeacherCoursePostResponse;
import com.rmr101.campus.entity.Teacher;
import com.rmr101.campus.entity.TeacherCourse;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.exception.RecordAlreadyExistException;
import com.rmr101.campus.mapper.TeacherCourseMapper;
import com.rmr101.campus.repository.TeacherCourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class TeacherCourseService {
    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    public TeacherCoursePostResponse addCourse(TeacherCoursePostRequest request){
        //valid check
        courseService.validateId(request.getCourseId());
        teacherService.validateUuid(request.getTeacherUuid());

        //duplicate check
        if(this.checkDuplicate(request.getTeacherUuid(),request.getCourseId()) != null) {
            log.debug("Course already exist");
            throw new RecordAlreadyExistException();
        }
        log.debug("About to save course : "+ request.getCourseId() +" to teacher: " + request.getTeacherUuid());
        TeacherCourse course = teacherCourseMapper.teacherCoursePostRequestToTeacherCourse(request);
        teacherCourseRepository.save(course);
        return teacherCourseMapper.teacherCourseToTeacherCoursePostResponse(course);
    }

    protected TeacherCourse checkDuplicate(UUID studentUuid, long courseId){
        return teacherCourseRepository.findByTeacherUuidAndCourseId(studentUuid,courseId);
    }

    protected TeacherCourse getTeacherCourseById(long id){
        return teacherCourseRepository.findById(id).orElseThrow(() -> new InvalidIdException("The teacher doesn't teach this course."));
    }
}
