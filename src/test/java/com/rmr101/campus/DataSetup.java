package com.rmr101.campus;

import com.rmr101.campus.entity.*;
import com.rmr101.campus.repository.StudentRepository;
import com.rmr101.campus.repository.SubjectRepository;
import com.rmr101.campus.repository.TeacherRepository;
import com.rmr101.campus.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class DataSetup {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void dataSetup(){
        Subject subject1 = new Subject();
        subject1.setName("Computer Science");
        subject1.setIntroduction("Computer Science and it");

        Course course1 = new Course();
        course1.setName("Web Development");
        course1.setIntroduction("HTML5 CSS3");
        course1.setSubject(subject1);
        subject1.getCourses().add(course1);

        Course course2 = new Course();
        course2.setName("Java");
        course2.setIntroduction("Java is the best language in the word!");
        course2.setSubject(subject1);
        subject1.getCourses().add(course2);

        Subject subject2 = new Subject();
        subject2.setName("English");
        subject2.setIntroduction("English language");

        Subject subject3 = new Subject();
        subject3.setName("Math");
        subject3.setIntroduction("Modern Math");

        subjectRepository.save(subject1);
        subjectRepository.save(subject2);
        subjectRepository.save(subject3);

        //user1
        User user1 = new User();
        user1.setRole("teacher");
        userRepository.save(user1);

        Teacher teacher1 = new Teacher();
        teacher1.setUuid(user1.getUuid());
        teacher1.setName("ALice Bob");

        TeacherCourse teachercourse1 = new TeacherCourse();
        teachercourse1.setTeacher(teacher1);
        teachercourse1.setCourse(course1);
        teacher1.getCourses().add(teachercourse1);

        TeacherCourse teachercourse2 = new TeacherCourse();
        teachercourse2.setTeacher(teacher1);
        teachercourse2.setCourse(course1);
        teacher1.getCourses().add(teachercourse2);
        teacherRepository.save(teacher1);

        //teacher2
        User user2 = new User();
        user2.setRole("teacher");
        userRepository.save(user2);

        Teacher teacher2 = new Teacher();
        teacher2.setUuid(user2.getUuid());
        teacher2.setName("Joan Doe");
        teacherRepository.save(teacher2);

        //add student1
        User user3 = new User();
        user3.setRole("student");
        userRepository.save(user3);

        Student student1 = new Student();
        student1.setUuid(user3.getUuid());
        student1.setName("Joan Doe");
        studentRepository.save(student1);

        //add student2
        User user4 = new User();
        user4.setRole("student");
        userRepository.save(user4);

        Student student2 = new Student();
        student2.setUuid(user4.getUuid());
        student2.setName("ALex D");
        studentRepository.save(student2);
        System.out.println(user4.getUuid());

    }
}
