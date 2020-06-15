package com.rmr101.campus.repository;

import com.rmr101.campus.entity.Course;
import com.rmr101.campus.entity.Subject;
import com.rmr101.campus.repository.CourseRepository;
import com.rmr101.campus.service.SubjectService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void insertCourse(){
        Course course = new Course();
        course.setName("HTML");
        course.setIntroduction("What ever");

        courseRepository.save(course);
    }


    @Test
    public void insertCourseWithSubject(){
        int id = 1;
        Subject subject = subjectRepository.findById(id).get();
        subject.setId(3);

        Course course = new Course();
        course.setId(7);
        course.setName("java");
        course.setIntroduction("What ever");
        course.setSubject(subject);
        courseRepository.save(course);
    }

    @Test
    public void updateCourseWithSubject(){
        Subject subject = new Subject();

        Course course = new Course();
        course.setName("HTML5");
        course.setIntroduction("What ever");
        course.setSubject(subject);

        courseRepository.save(course);
    }

    @Disabled
    @Test
    public void retrieveCourseWithSubject(){

        Course course = courseRepository.findById(31).get();
//        System.out.println(course);
    }

}
