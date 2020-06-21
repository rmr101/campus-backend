package com.rmr101.campus.service;

import com.rmr101.campus.dto.*;
import com.rmr101.campus.entity.Course;
import com.rmr101.campus.entity.Subject;
import com.rmr101.campus.mapper.CourseMapper;
import com.rmr101.campus.mapper.SubjectMapper;
import com.rmr101.campus.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Subject> optionalSubject = subjectRepository.findById(id);

        if(optionalSubject.isPresent()){
            Subject subject = optionalSubject.get();
            return subjectMapper.toSubjectDto(subject);
        }
        return null;
    }

    public SubjectList getAllSubject() {
        SubjectList subjectList = new SubjectList();
        subjectList.setSubjectList(new ArrayList<SubjectDto>());
        subjectRepository.findAll()
                .forEach(po -> subjectList.getSubjectList().add(subjectMapper.toSubjectDto(po)));
        return subjectList;
    }

    public SubjectDetails getSubjectDetailsById(long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);

        if(optionalSubject.isPresent()){
            SubjectDetails subjectDetails = new SubjectDetails();
            Subject subject = optionalSubject.get();
            subjectDetails.setSubjectDto(subjectMapper.toSubjectDto(subject));
            subjectDetails.setCourseList(courseMapper.toCourseDto(subject.getCourses()));
            return subjectDetails;
        }
        return null;
    }

}

