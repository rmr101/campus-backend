package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.TeacherDto;
import com.rmr101.campus.dto.TeacherPutDto;
import com.rmr101.campus.dto.TeacherGetDto;
import com.rmr101.campus.dto.TeacherPostDto;
import com.rmr101.campus.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TeacherMapper {


    @Mappings({
            @Mapping(target = "uuid", expression = "java(teacher.getUuid().toString())")
        })
    TeacherGetDto teacherToTeacherGetDto(Teacher teacher);

    Teacher teacherPostDtoToTeacher(TeacherPostDto teacherPostDto);

    TeacherDto toTeacherDto(Teacher teacher);

}
