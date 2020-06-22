package com.rmr101.campus.service;

import com.rmr101.campus.dto.*;
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

    public CourseList getAllCourses(){
        CourseList courseList = new CourseList();
        courseList.setCourseList(new ArrayList<CourseDto>());
        courseRepository.findAll()
                .forEach(po -> courseList.getCourseList().add(courseMapper.toCourseDto(po)));
        return courseList;
    }

    public CourseDto getCourseById(long id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new InvalidIdException());
        return courseMapper.toCourseDto(course);
    }

    public CourseDetails getCourseDetailsById(long id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new InvalidIdException());

        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setCourseDto(courseMapper.toCourseDto(course));
        courseDetails.setAssignmentList(courseAssignmentMapper.toCourseAssignmentDto(course.getAssignments()));
        List<TeacherDto> teacherList = new ArrayList<TeacherDto>();
        course.getTeachers().stream()
                .forEach( courseTeacher -> {
                    TeacherDto teacherDto= teacherMapper.toTeacherDto(courseTeacher.getTeacher());
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

    public CourseDto addCourse(CourseDto courseDto) {
        Course coursePo = courseMapper.toCourse(courseDto);

        courseRepository.save(coursePo);
        System.out.println(coursePo);
        courseDto.setId(coursePo.getId());
        return courseDto;
    }

}
