package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.courseassignment.CourseAssignmentGetResponse;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentPostRequest;
import com.rmr101.campus.dto.courseassignment.CourseAssignmentPostResponse;
import com.rmr101.campus.entity.CourseAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseAssignmentMapper {

    public CourseAssignmentGetResponse courseAssignmentToCourseAssignmentGetResponse(CourseAssignment courseAssignment);

    public CourseAssignmentPostResponse courseAssignmentToCourseAssignmentPostResponse(CourseAssignment courseAssignment);

    public CourseAssignment courseAssignmentPostRequesttoCourseAssignment(CourseAssignmentPostRequest courseAssignmentPostRequest);


//    public List<CourseAssignmentDto> toCourseAssignmentDto(List<CourseAssignment> courseAssignmentList);

}
