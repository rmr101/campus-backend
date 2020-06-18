package com.rmr101.campus.repository;

import com.rmr101.campus.entity.Course;
import com.rmr101.campus.entity.Subject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
public class SubjectRepositoryTest {
    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void insertSubject(){
        Subject subject = new Subject();
        subject.setId(3);
        subject.setName("Math");
        subject.setIntroduction("Computer Science");

        subjectRepository.save(subject);
    }

}
