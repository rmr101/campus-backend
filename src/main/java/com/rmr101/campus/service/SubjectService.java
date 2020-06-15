package com.rmr101.campus.service;

import com.rmr101.campus.dto.SubjectDto;
import com.rmr101.campus.dto.SubjectDetails;
import com.rmr101.campus.dto.SubjectList;
import com.rmr101.campus.entity.Subject;
import com.rmr101.campus.mapper.SubjectMapper;
import com.rmr101.campus.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private CourseService courseService;

//    public SubjectDetails addSubject(SubjectDetails subjectDetials) {
//    }

    public SubjectDto getSubjectById(int id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);

        if(optionalSubject.isPresent()){
            Subject subject = optionalSubject.get();
            return subjectMapper.toSubjectDto(subject);
        }
        return null;
    }

    public SubjectList getAllSubject() {
        SubjectList subjectDetailsList = new SubjectList();
        subjectRepository.findAll()
                .forEach(po -> subjectDetailsList.getSubjectList().add(subjectMapper.toSubjectDto(po)));
        return subjectDetailsList;
    }

    public SubjectDetails getCourseListBySubjectId(int id) {

        Optional<Subject> optionalSubject = subjectRepository.findById(id);

        if(optionalSubject.isPresent()){
            SubjectDetails subjectDetails = new SubjectDetails();
            Subject subject = optionalSubject.get();
            subjectDetails.setSubjectDto(subjectMapper.toSubjectDto(subject));
            subjectDetails.setCourseList(courseService.getCourseBySubjectId(id).getCourseList());
            return subjectDetails;
        }
        return null;
    }
}

