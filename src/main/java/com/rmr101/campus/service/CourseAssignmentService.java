package com.rmr101.campus.service;

import com.rmr101.campus.dto.CourseAssignmentDto;
import com.rmr101.campus.dto.CourseAssignmentList;
import com.rmr101.campus.dto.CourseList;
import com.rmr101.campus.dto.SubjectDetails;
import com.rmr101.campus.entity.CourseAssignment;
import com.rmr101.campus.entity.Subject;
import com.rmr101.campus.mapper.CourseAssignmentMapper;
import com.rmr101.campus.repository.CourseAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseAssignmentService {

    @Autowired
    private CourseAssignmentRepository courseAssignmentRepository;

    @Autowired
    private CourseAssignmentMapper courseAssignmentMapper;

    public CourseAssignmentDto getCourseAssignmentById(long id) {
        Optional<CourseAssignment> optionalCourse = courseAssignmentRepository.findById(id);

        if(optionalCourse.isPresent()){
            CourseAssignment courseAssignment = optionalCourse.get();
            return courseAssignmentMapper.toCourseAssignmentDto(courseAssignment);
        }
        return null;
    }

    public CourseAssignmentDto addAssignmentCourse(CourseAssignmentDto courseAssignmentDto) {
        CourseAssignment courseAssignment = courseAssignmentMapper.toCourseAssignment(courseAssignmentDto);

        courseAssignmentRepository.save(courseAssignment);
        courseAssignmentDto.setId(courseAssignment.getId());
        return courseAssignmentDto;
    }
}
