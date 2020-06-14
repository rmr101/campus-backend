package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.CourseDetails;
import com.rmr101.campus.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    public CourseDetails toCourseDetails(Course course);
    public Course toCourse(CourseDetails courseDetials);
}
