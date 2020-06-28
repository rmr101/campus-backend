package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.teachercourse.TeacherCoursePostRequest;
import com.rmr101.campus.dto.teachercourse.TeacherCoursePostResponse;
import com.rmr101.campus.entity.TeacherCourse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TeacherCourseMapper {
    public TeacherCoursePostResponse teacherCourseToTeacherCoursePostResponse(TeacherCourse teacherCourse);

    @Mappings({
            @Mapping( target = "teacher.uuid", source = "teacherCoursePostRequest.teacherUuid"),
            @Mapping( target = "course.id", source = "teacherCoursePostRequest.courseId")
    })
    public TeacherCourse teacherCoursePostRequestToTeacherCourse(TeacherCoursePostRequest teacherCoursePostRequest);
}
