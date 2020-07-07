package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.course.CourseGetResponse;
import com.rmr101.campus.dto.course.CoursePostRequest;
import com.rmr101.campus.dto.course.CoursePostResponse;
import com.rmr101.campus.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

//    @Mappings({
//            @Mapping(source = "subjectId", target = "subject.id")
//    })
    public Course coursePostRequestToCourse(CoursePostRequest coursePostRequest);

    public CoursePostResponse courseToCoursePostResponse(Course course);

    public CourseGetResponse courseToCourseGetResponse(Course course);

    public List<CourseGetResponse> courseToCourseGetResponse(List<Course> courseList);
}