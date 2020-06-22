package com.rmr101.campus.service;

import com.rmr101.campus.dto.*;
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


    public SubjectDto addSubject(SubjectDto subjectDto) {
        Subject subjectPo = subjectMapper.toSubject(subjectDto);

        subjectRepository.save(subjectPo);

        System.out.println(subjectPo);
        subjectDto.setId(subjectPo.getId());
        return subjectDto;
    }

    public SubjectDto getSubjectById(long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new InvalidIdException());
        return subjectMapper.toSubjectDto(subject);
    }

    public SubjectList getAllSubject() {
        SubjectList subjectList = new SubjectList();
        subjectList.setSubjectList(new ArrayList<SubjectDto>());
        subjectRepository.findAll()
                .forEach(po -> subjectList.getSubjectList().add(subjectMapper.toSubjectDto(po)));
        return subjectList;
    }

    public SubjectDetails getSubjectDetailsById(long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new InvalidIdException());

        SubjectDetails subjectDetails = new SubjectDetails();
        subjectDetails.setSubjectDto(subjectMapper.toSubjectDto(subject));
        subjectDetails.setCourseList(courseMapper.toCourseDto(subject.getCourses()));
        return subjectDetails;
    }

}

