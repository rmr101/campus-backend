package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.teacher.TeacherGetResponse;
import com.rmr101.campus.dto.teacher.TeacherPostResponse;
import com.rmr101.campus.dto.teacher.TeacherPostRequest;
import com.rmr101.campus.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherPostResponse teacherToTeacherPostResponse(Teacher teacher);

    Teacher teacherPostRequestToTeacher(TeacherPostRequest teacherPostRequest);

    @Mapping(target = "name", expression = "java( teacher.getFirstName()+ ' ' + teacher.getLastName() )")
    TeacherGetResponse teacherToTeacherGetResponse(Teacher teacher);

}
