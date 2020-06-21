package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.CourseAssignmentDto;
import com.rmr101.campus.entity.CourseAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseAssignmentMapper {

    @Mappings({
            @Mapping(source = "course.id", target = "courseId")
    })
    public CourseAssignmentDto toCourseAssignmentDto(CourseAssignment courseAssignment);

    public List<CourseAssignmentDto> toCourseAssignmentDto(List<CourseAssignment> courseAssignmentList);

    @Mappings({
            @Mapping(source = "courseId", target = "course.id")
    })
    public CourseAssignment toCourseAssignment(CourseAssignmentDto courseAssignmentDto);
}
