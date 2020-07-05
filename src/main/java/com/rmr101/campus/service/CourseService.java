package com.rmr101.campus.service;

import com.rmr101.campus.dto.course.*;
import com.rmr101.campus.dto.teacher.TeacherDto;
import com.rmr101.campus.entity.Course;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.CourseAssignmentMapper;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.mapper.TeacherMapper;
import com.rmr101.campus.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseAssignmentMapper courseAssignmentMapper;

    public List<CourseGetResponse> getAllCourses(){
        List<CourseGetResponse> courseList= new ArrayList<CourseGetResponse>();
        courseRepository.findAll()
                .forEach(course -> courseList.add(courseMapper.courseToCourseGetResponse(course)));
        return courseList;
    }

    public CoursePostResponse getCourseById(long id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new InvalidIdException("The course id doesn't exist."));
        return courseMapper.courseToCoursePostResponse(course);
    }

    public CourseDetails getCourseDetailsById(long id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new InvalidIdException("The course id doesn't exist."));

        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setCourse(courseMapper.courseToCourseGetResponse(course));
//        courseDetails.setAssignmentList(courseAssignmentMapper.toCourseAssignmentDto(course.getAssignments()));
        List<TeacherDto> teacherList = new ArrayList<TeacherDto>();
        course.getTeachers().stream()
                .forEach( courseTeacher -> {
                    TeacherDto teacherDto= teacherMapper.teacherToTeacherDto(courseTeacher.getTeacher());
                    teacherList.add(teacherDto);
                });
        courseDetails.setTeachers(teacherList);

        return courseDetails;
    }

//    public CourseList getCourseByName(String name){
//        CourseList courseList = new CourseList();
//        courseRepository.findByName(name)
//                .stream()
//                .forEach(po -> courseList.getCourseList().add(courseMapper.toCourseDto(po)));
//
//        return courseList;
//    }
//
//    public CourseList getCourseBySubjectId(int id){
//        CourseList courseList = new CourseList();
//        courseRepository.findBySubjectId(id)
//                .stream()
//                .forEach(po -> courseList.getCourseList().add(courseMapper.toCourseDto(po)));
//
//        return courseList;
//    }

    //add a course
    public CoursePostResponse addCourse(CoursePostRequest request) {
        //validate subjectId???
        Course course = courseMapper.coursePostRequestToCourse(request);
        courseRepository.save(course);
        return courseMapper.courseToCoursePostResponse(course);
    }

    protected Course validateId(long courseId){
        return courseRepository.findById(courseId).orElseThrow(() -> new InvalidIdException("The course id doesn't exist."));
    }
}
