package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.teacher.TeacherDto;
import com.rmr101.campus.dto.teacher.TeacherPostResponse;
import com.rmr101.campus.dto.teacher.TeacherPostRequest;
import com.rmr101.campus.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mappings({
            @Mapping(target = "uuid", expression = "java(teacher.getUuid().toString())")
        })
    TeacherPostResponse teacherToTeacherPostResponse(Teacher teacher);

    Teacher teacherPostRequestToTeacher(TeacherPostRequest teacherPostRequest);

    TeacherDto teacherToTeacherDto(Teacher teacher);

}
