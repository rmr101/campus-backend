package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.studentcourse.StudentCourseDto;
import com.rmr101.campus.entity.StudentCourse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StudentCourseMapper {
    @Mappings({
            @Mapping( target = "student.uuid", source = "studentCourseDto.studentUuid"),
            @Mapping( target = "course.id", source = "studentCourseDto.courseId")
    })
    public StudentCourse studentCourseDtoToStudentCourse(StudentCourseDto studentCourseDto);

//    public StudentCourseDto toStudentCourseDto(StudentCourse studentCourse);
}
