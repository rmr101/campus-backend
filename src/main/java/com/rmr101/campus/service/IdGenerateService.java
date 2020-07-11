package com.rmr101.campus.service;

import com.rmr101.campus.entity.Subject;
import com.rmr101.campus.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class IdGenerateService {

    public String generateCampusId(String role){
        Random rand = new Random();
        String campusId = role.substring(0,1);//get the first letter of role
        for (int i = 0; i < 8; i++){
            campusId += rand.nextInt(10);
        }
        return campusId;
    }

//    public String generateCourseCode(long subjectId, long yearSemester){
//        Subject subject = SubjectRepository.findById(subjectId);
//        String courseCode = subject.getSubjectCode();
//        return
//    }
}
