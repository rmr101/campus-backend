package com.rmr101.campus.service;

import com.rmr101.campus.dto.StudentCourseDto;
import com.rmr101.campus.entity.StudentCourse;
import com.rmr101.campus.mapper.StudentCourseMapper;
import com.rmr101.campus.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {
    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    public StudentCourseDto addStudentCourse(StudentCourseDto studentCourseDto) {
        StudentCourse course = studentCourseMapper.toStudentCourse(studentCourseDto);
        course.setId(0);
        studentCourseRepository.save(course);
        studentCourseDto.setId(course.getId());
        System.out.println(course.getId());
        return studentCourseDto;
    }
}
