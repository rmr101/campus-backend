package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.teacher.TeacherGetResponse;
import com.rmr101.campus.dto.teacher.TeacherPostResponse;
import com.rmr101.campus.dto.teacher.TeacherPostRequest;
import com.rmr101.campus.dto.teacher.TeacherUpdateRequest;
import com.rmr101.campus.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    public TeacherPostResponse teacherToTeacherPostResponse(Teacher teacher);

    public Teacher teacherPostRequestToTeacher(TeacherPostRequest teacherPostRequest);
    public Teacher teacherUpdateRequestToTeacher(TeacherUpdateRequest teacherUpdateRequest);

    @Mapping(target = "name", expression = "java( teacher.getFirstName()+ ' ' + teacher.getLastName() )")
    public TeacherGetResponse teacherToTeacherGetResponse(Teacher teacher);

}
