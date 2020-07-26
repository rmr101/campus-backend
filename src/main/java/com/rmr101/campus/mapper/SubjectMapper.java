package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.subject.SubjectGetResponse;
import com.rmr101.campus.dto.subject.SubjectPostRequest;
import com.rmr101.campus.dto.subject.SubjectPostResponse;
import com.rmr101.campus.entity.Subject;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    public SubjectGetResponse toSubjectGetResponse(Subject subject);
    public List<SubjectGetResponse> toSubjectDto(List<Subject> subjectList);
    public Subject toSubject(SubjectGetResponse subjectGetResponse);
    public Subject subjectPostRequestToSubject(SubjectPostRequest subjectPostRequest);
    public SubjectPostResponse subjectToSubjectPostResponse (Subject subject);

}
