package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.StudentCourseDto;
import com.rmr101.campus.entity.StudentCourse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = java.util.UUID.class)
public interface StudentCourseMapper {
    @Mappings({
            @Mapping( target = "student.uuid", expression = "java(UUID.fromString(studentCourseDto.getStudentUuid()))"),
            @Mapping( target = "course.id", source = "studentCourseDto.courseId")
    })
    public StudentCourse toStudentCourse(StudentCourseDto studentCourseDto);

//    public StudentCourseDto toStudentCourseDto(StudentCourse studentCourse);
}
