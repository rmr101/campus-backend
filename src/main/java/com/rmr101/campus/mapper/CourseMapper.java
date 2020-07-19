package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.course.CourseGetResponse;
import com.rmr101.campus.dto.course.CoursePostRequest;
import com.rmr101.campus.dto.course.CoursePostResponse;
import com.rmr101.campus.dto.course.CoursePutRequest;
import com.rmr101.campus.entity.Course;
import org.mapstruct.*;

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

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateCourseFromPutRequest(CoursePutRequest coursePutRequest, @MappingTarget Course entity);
}