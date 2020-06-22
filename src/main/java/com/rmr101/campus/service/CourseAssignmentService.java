package com.rmr101.campus.service;

import com.rmr101.campus.dto.CourseAssignmentDto;
import com.rmr101.campus.entity.CourseAssignment;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.CourseAssignmentMapper;
import com.rmr101.campus.repository.CourseAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseAssignmentService {

    @Autowired
    private CourseAssignmentRepository courseAssignmentRepository;

    @Autowired
    private CourseAssignmentMapper courseAssignmentMapper;

    public CourseAssignmentDto getCourseAssignmentById(long id) {
        CourseAssignment courseAssignment = courseAssignmentRepository.findById(id).orElseThrow(() -> new InvalidIdException());
        return courseAssignmentMapper.toCourseAssignmentDto(courseAssignment);
    }

    public CourseAssignmentDto addAssignmentCourse(CourseAssignmentDto courseAssignmentDto) {
        CourseAssignment courseAssignment = courseAssignmentMapper.toCourseAssignment(courseAssignmentDto);

        courseAssignmentRepository.save(courseAssignment);
        courseAssignmentDto.setId(courseAssignment.getId());
        return courseAssignmentDto;
    }
}
