package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.TeacherDto;
import com.rmr101.campus.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mappings({
            @Mapping(target = "uuid", expression = "java(teacher.getUuid().toString())")
    })
    public TeacherDto toTeacherDto(Teacher teacher);
}
