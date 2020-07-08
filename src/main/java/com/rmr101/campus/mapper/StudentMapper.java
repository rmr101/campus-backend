package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.student.*;
import com.rmr101.campus.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "name", expression = "java( student.getFirstName()+ ' ' + student.getLastName() )")
    public StudentGetResponse studentToStudentGetResponse(Student student);

    public List<StudentGetResponse> studentToStudentGetResponse(List<Student> student);

    public Student studentPostRequestToStudent(StudentPostRequest studentPostRequest);
    public StudentPostResponse studentToStudentPostResponse(Student student);

    public Student studentUpdateRequestToStudent(StudentUpdateRequest studentUpdateRequest);

}
