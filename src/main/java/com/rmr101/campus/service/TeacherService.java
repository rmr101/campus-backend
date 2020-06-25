package com.rmr101.campus.service;

import com.rmr101.campus.dto.CourseDto;
import com.rmr101.campus.dto.TeacherDetails;
import com.rmr101.campus.dto.TeacherDto;
import com.rmr101.campus.dto.TeacherGetDto;
import com.rmr101.campus.dto.TeacherPostDto;
import com.rmr101.campus.entity.Teacher;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.mapper.TeacherMapper;
import com.rmr101.campus.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    public TeacherDetails getTeacherDetails(String uuid){
        Teacher teacher = teacherRepository.findById(UUID.fromString(uuid)).orElseThrow(() -> new InvalidIdException());
        TeacherDetails teacherDetails = new TeacherDetails();

        //set values
        teacherDetails.setUuid(uuid);
        teacherDetails.setName(teacher.getName());
        List<CourseDto> courseList = new ArrayList<CourseDto>();
        teacher.getCourses().stream()
                .forEach( teacherCourse -> {
                    System.out.println(teacherCourse.getCourse().getName());
                    CourseDto courseDto= courseMapper.toCourseDto(teacherCourse.getCourse());
                    courseList.add(courseDto);
                });
        teacherDetails.setCourseList(courseList);

        return teacherDetails;
    }

    //Post API
    public TeacherGetDto addTeacher(TeacherPostDto teacherPostDto) {
        Teacher teacher =  teacherMapper.teacherPostDtoToTeacher(teacherPostDto);
        teacher.setUuid(UUID.randomUUID());
        return teacherMapper.teacherToTeacherGetDto(teacherRepository.save(teacher));
        }
}
