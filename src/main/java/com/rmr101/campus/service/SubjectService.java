package com.rmr101.campus.service;

import com.rmr101.campus.dto.subject.*;
import com.rmr101.campus.entity.Subject;
import com.rmr101.campus.exception.InvalidIdException;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.mapper.SubjectMapper;
import com.rmr101.campus.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private CourseMapper courseMapper;

    public SubjectList getAllSubject() {
        SubjectList subjectList = new SubjectList();
        subjectList.setSubjectList(new ArrayList<SubjectGetResponse>());
        subjectRepository.findAll()
                .forEach(po -> subjectList.getSubjectList().add(subjectMapper.toSubjectGetResponse(po)));
        return subjectList;
    }

    public SubjectGetDetails getSubjectDetailsById(long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new InvalidIdException("Subject id doesn't exist."));

        SubjectGetDetails subjectDetails = new SubjectGetDetails();
        subjectDetails.setSubjectGetResponse(subjectMapper.toSubjectGetResponse(subject));
        subjectDetails.setCourseList(courseMapper.courseToCourseGetResponse(subject.getCourses()));

        return subjectDetails;
    }
    public Subject validateId(long id){
        return subjectRepository.findById(id).orElseThrow(() -> new InvalidIdException("Subject id doesn't exist."));
    }

    public SubjectPostResponse addSubject(SubjectPostRequest request){
        Subject subject = subjectMapper.subjectPostRequestToSubject(request);
        subject.setSubjectCode(subject.getSubjectCode().toUpperCase());
        subjectRepository.save(subject);
        return subjectMapper.subjectToSubjectPostResponse(subject);
    }
}

