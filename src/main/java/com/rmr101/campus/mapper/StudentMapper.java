package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.student.*;
import com.rmr101.campus.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

//  @Mapping(target = "uuid", expression = "java(student.getUuid().toString())")
//  StudentGetDto studentToStudentGetDto(Student student);

    StudentGetResponse studentToStudentGetResponse(Student student);

    Student studentPostRequestToStudent(StudentPostRequest studentPostRequest);
    StudentPostResponse studentToStudentPostResponse(Student student);

    Student studentPutRequestToStudent(StudentPutRequest studentPutRequest);

}