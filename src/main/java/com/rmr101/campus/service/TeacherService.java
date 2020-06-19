package com.rmr101.campus.service;

import com.rmr101.campus.dto.CourseDto;
import com.rmr101.campus.dto.TeacherDetails;
import com.rmr101.campus.entity.Course;
import com.rmr101.campus.entity.Teacher;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseMapper courseMapper;

    public TeacherDetails getTeacherDetails(String uuid){
        Optional<Teacher> teacherOptional = teacherRepository.findById(UUID.fromString(uuid));
        if(teacherOptional.isPresent()){
            TeacherDetails teacherDetails = new TeacherDetails();
            Teacher teacher = teacherOptional.get();

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
        System.out.println("can't find teacher");
        return null;
    }
}
