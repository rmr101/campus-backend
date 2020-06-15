package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.CourseDto;
import com.rmr101.campus.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    public CourseDto toCourseDto(Course course);
    public Course toCourse(CourseDto courseDto);
}
