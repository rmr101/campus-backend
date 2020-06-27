package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.studentcourse.StudentCoursePostRequest;
import com.rmr101.campus.dto.studentcourse.StudentCoursePostResponse;
import com.rmr101.campus.entity.StudentCourse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StudentCourseMapper {

    public StudentCoursePostResponse studentCourseToStudentCoursePostResponse(StudentCourse studentCourse);

    @Mappings({
            @Mapping( target = "student.uuid", source = "studentCoursePostRequest.studentUuid"),
            @Mapping( target = "course.id", source = "studentCoursePostRequest.courseId")
    })
    public StudentCourse studentCoursePostRequestToStudentCourse(StudentCoursePostRequest studentCoursePostRequest);

}
