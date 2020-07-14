package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.subject.SubjectDto;
import com.rmr101.campus.dto.subject.SubjectPostRequest;
import com.rmr101.campus.dto.subject.SubjectPostResponse;
import com.rmr101.campus.entity.Subject;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    public SubjectDto toSubjectDto(Subject subject);
    public List<SubjectDto> toSubjectDto(List<Subject> subjectList);
    public Subject toSubject(SubjectDto subjectDto);
    public Subject subjectPostRequestToSubject(SubjectPostRequest subjectPostRequest);
    public SubjectPostResponse subjectToSubjectPostResponse (Subject subject);

}
