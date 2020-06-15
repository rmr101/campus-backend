package com.rmr101.campus.service;

import com.rmr101.campus.dto.CourseDto;
import com.rmr101.campus.dto.CourseList;
import com.rmr101.campus.dto.SubjectList;
import com.rmr101.campus.entity.Course;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    public CourseList getAllCourses(){
        CourseList courseList = new CourseList();
        courseRepository.findAll()
                .forEach(po -> courseList.getCourseList().add(courseMapper.toCourseDto(po)));
        return courseList;
    }

    public CourseDto getCourseById(int id){
        Optional<Course> optionalCourse = courseRepository.findById(id);

        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            return courseMapper.toCourseDto(course);
        }
        return null;
    }

    public CourseList getCourseByName(String name){
        CourseList courseList = new CourseList();
        courseRepository.findByName(name)
                .stream()
                .forEach(po -> courseList.getCourseList().add(courseMapper.toCourseDto(po)));

        return courseList;
    }

    public CourseList getCourseBySubjectId(int id){
        CourseList courseList = new CourseList();
        courseRepository.findBySubjectId(id)
                .stream()
                .forEach(po -> courseList.getCourseList().add(courseMapper.toCourseDto(po)));

        return courseList;
    }

    public CourseDto addCourse(CourseDto courseDto) {
        Course coursePo = courseMapper.toCourse(courseDto);

        courseRepository.save(coursePo);
        System.out.println(coursePo);
        courseDto.setId(coursePo.getId());
        return courseDto;
    }

}
