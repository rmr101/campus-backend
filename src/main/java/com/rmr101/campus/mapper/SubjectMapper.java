package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.SubjectDto;
import com.rmr101.campus.dto.SubjectDto;
import com.rmr101.campus.entity.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    public SubjectDto toSubjectDto(Subject subject);
    public Subject toSubject(SubjectDto subjectDto);

}
