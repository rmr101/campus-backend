package com.rmr101.campus.repository;

import com.rmr101.campus.entity.Teacher;
import com.rmr101.campus.entity.TeacherCourse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;


    @Test
    public void addTeacherCourse(){
        Teacher teacher = new Teacher();
        String id = "2d0ffb3f-1366-4a6f-90ad-177a6ecd1085";
        teacher.setUuid(UUID.fromString(id));
        teacher.setName("ALice Bob");

        TeacherCourse course1 = new TeacherCourse();
        course1.setTeacher(teacher);
        TeacherCourse course2 = new TeacherCourse();
        course2.setTeacher(teacher);

        teacher.getCourses().add(course1);
        teacher.getCourses().add(course2);

        teacherRepository.save(teacher);
    }

    @Test
    public void updateTeacherCourse(){
        Teacher teacher = new Teacher();
        String id = "3028da81-b4d5-45fd-ba72-4dd1b84e5ce0";
        teacher.setUuid(UUID.fromString(id));
        teacher.setName("ALice Bob update");


        TeacherCourse course1 = new TeacherCourse();
        course1.setTeacher(teacher);
        course1.setId(2);

        teacher.getCourses().add(course1);
        teacherRepository.save(teacher);
    }
}
