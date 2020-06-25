package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.course.CourseDto;
import com.rmr101.campus.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mappings({
            @Mapping(source = "subject.id", target = "subjectId")
    })
    public CourseDto toCourseDto(Course course);

    @Mappings({
            @Mapping(source = "subjectId", target = "subject.id")
    })
    public Course toCourse(CourseDto courseDto);

    public List<CourseDto> toCourseDto(List<Course> courseList);
}
